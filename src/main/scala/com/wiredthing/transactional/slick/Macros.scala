package com.wiredthing.transactional.slick

import language.experimental.macros
import scala.reflect.macros.Context
import scala.slick.session.Session
import scala.collection.mutable.{ ListBuffer, Stack }

object Macros {
  def update[T <: Product](entity: T, params: Any*): Transactional[T] = macro update_impl[T]

  def update_impl[T <: Product](c: Context)(entity: c.Expr[T], params: c.Expr[Any]*): c.Expr[Transactional[T]] = {
    import c.universe._

    params.map { param =>
      param.tree
    }

    reify { Transactional.transactional { session => entity.splice } }
  }
}