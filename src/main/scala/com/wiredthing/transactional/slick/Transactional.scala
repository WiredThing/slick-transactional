package com.wiredthing.transactional.slick

import scala.slick.session._

trait Transactional[+A] {
  def body: (Session => A)

  import Transactional._

  def map[B](f: A => B): Transactional[B]

  def flatMap[B](f: A => Transactional[B]): Transactional[B]

  def exec(implicit session: Session): A = body(session)
}

case class TX[+A](val body: (Session => A)) extends Transactional[A] {

  def map[B](f: A => B): Transactional[B] = TX[B] { f compose body }

  def flatMap[B](f: A => Transactional[B]): TX[B] = TX { session => f(body(session)).body(session) }
}

object Transactional {
  def transactional[A](body: Session => A) = TX[A](body)

  def update[A](body: Session => A) = TX[A](body)
}

