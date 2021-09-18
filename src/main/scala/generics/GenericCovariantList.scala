package generics

abstract class GenericCovariantList[+A] {
  def head: A

  def tail: GenericCovariantList[A]

  def isEmpty: Boolean

  def add[B >: A ](element: B): GenericCovariantList[B]

  protected[generics] def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object Empty extends GenericCovariantList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericCovariantList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): GenericCovariantList[B] = new Cons(element, Empty)

  override def printElements: String = ""
}

class Cons[+A] (h: A, t: GenericCovariantList[A]) extends GenericCovariantList[A] {
  override def head: A = h

  override def tail: GenericCovariantList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): GenericCovariantList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) s"$h"
    else s"$h , ${t.printElements}"
}

@main
def testList(): Unit = {
  val listOfIntegers: GenericCovariantList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString: GenericCovariantList[String] = new Cons("Hello", new Cons("Scala", new Cons("!", Empty)))
  println(listOfIntegers.toString)
  println(listOfString.toString)
}
