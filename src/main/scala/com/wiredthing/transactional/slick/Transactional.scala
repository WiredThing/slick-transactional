package com.wiredthing.transactional.slick

import scala.slick.session._

trait Transactional[+A] {
  def atomic: (Session => A)

  import Transactional._

  def map[B](f: A => B): Transactional[B] = transactional[B] { f compose atomic }

  def flatMap[B](f: A => Transactional[B]): Transactional[B] = transactional { r => f(atomic(r)).atomic(r) }

  def exec(implicit session: Session): A = atomic(session)
}

object Transactional {
  def transactional[A](body: Session => A) = new Transactional[A] { def atomic = body }
  def transactionalO[A](body: Session => Option[A]) = ???
}

