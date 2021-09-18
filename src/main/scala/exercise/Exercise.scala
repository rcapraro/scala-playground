package exercise

object Exercise {

  trait Predicate[-A] {
    def evaluate(a: A): Boolean
  }

  trait Transformer[-A, B] {
    def transform(a: A): B
  }

  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList[B]

    def filter(predicate: Predicate[A]): MyList[A]

    def map[B](transformer: Transformer[A, B]): MyList[B]

    def ++[B >: A](list: MyList[B]): MyList[B]

    def flatMap[B](transformer: Transformer[A, MyList[B]]): MyList[B]

    protected[exercise] def printElements: String

    override def toString: String = "[" + printElements + "]"
  }

  case object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    override def filter(predicate: Predicate[Nothing]) = Empty

    override def map[B](transformer: Transformer[Nothing, B]) = Empty

    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    override def flatMap[B](transformer: Transformer[Nothing, MyList[B]]) = Empty

    override def printElements: String = ""
  }

  case class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    override def head: A = h

    override def tail: MyList[A] = t

    override def isEmpty: Boolean = false

    override def filter(predicate: Predicate[A]) = {
      if predicate.evaluate(h) then new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    }

    override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    override def map[B](transformer: Transformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))

    override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    override def flatMap[B](transformer: Transformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ tail.flatMap(transformer)


    override def printElements: String =
      if (t.isEmpty) s"$h"
      else s"$h , ${t.printElements}"
  }

  @main def test(): Unit = {
    val intList = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))

    val toNumber = new Transformer[Int, String] {
      override def transform(a: Int): String = s"number ${a.toString}"
    }

    println(intList.map(toNumber))

    val odds = new Predicate[Int] {
      override def evaluate(a: Int): Boolean = a % 2 == 1
    }

    println(intList.filter(odds))

    val toNext = new Transformer[Int, MyList[Int]] {
      override def transform(a: Int): MyList[Int] = new Cons(a, new Cons(a * 10, Empty))
    }

    println(intList.flatMap(toNext))
  }
}
