package oop

abstract class IntList {
  def head: Int

  def tail: IntList

  def isEmpty: Boolean

  def add(element: Int): IntList

  protected[oop] def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends IntList {
  override def head: Int = throw new NoSuchElementException

  override def tail: IntList = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add(element: Int): IntList = new Cons(element, Empty)

  override def printElements: String = ""
}

class Cons(h: Int, t: IntList) extends IntList {
  override def head: Int = h

  override def tail: IntList = t

  override def isEmpty: Boolean = false

  override def add(element: Int): IntList = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h , ${t.printElements}"
}

@main
def testList(): Unit = {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.head)
  println(list.tail.head)

  println(list.add(4).head)

  println(list.toString)
}