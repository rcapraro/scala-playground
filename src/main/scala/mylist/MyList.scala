package mylist

object MyList {

  abstract class MyList[+A] {
    def head: A

    def tail: MyList[A]

    def isEmpty: Boolean

    def add[B >: A](element: B): MyList[B]

    def filter(predicate: A => Boolean): MyList[A]

    def map[B](transformer: A => B): MyList[B]

    def ++[B >: A](list: MyList[B]): MyList[B]

    def flatMap[B](transformer: A => MyList[B]): MyList[B]

    def foreach(f: A => Unit): Unit

    def sort(compare: (A, A) => Int): MyList[A]

    def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

    def fold[B](start: B)(operator: (B, A) => B): B

    protected[mylist] def printElements: String

    override def toString: String = "[" + printElements + "]"
  }

  case object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)

    override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    override def map[B](transformer: Nothing => B): MyList[Nothing] = Empty

    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

    override def foreach(f: Nothing => Unit): Unit = ()

    override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

    override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
      if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else Empty

    override def fold[B](start: B)(operator: (B, Nothing) => B): B = start

    override def printElements: String = ""
  }

  case class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    override def head: A = h

    override def tail: MyList[A] = t

    override def isEmpty: Boolean = false

    override def filter(predicate: A => Boolean): MyList[A] =
      if predicate(h) then Cons(h, t.filter(predicate))
      else t.filter(predicate)

    override def add[B >: A](element: B): MyList[B] = Cons(element, this)

    override def map[B](transformer: A => B): MyList[B] =
      Cons(transformer(h), t.map(transformer))

    override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

    override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ tail.flatMap(transformer)

    override def foreach(f: A => Unit): Unit =
      f(h)
      tail.foreach(f)


    override def sort(compare: (A, A) => Int): MyList[A] = {

      def insert(x: A, sortedList: MyList[A]): MyList[A] =
        if (sortedList.isEmpty) Cons(x, Empty)
        else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
        else Cons(sortedList.head, insert(x, sortedList.tail))

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
      if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      else Cons(zip(h, list.head), tail.zipWith(list.tail, zip))

    override def fold[B](start: B)(operator: (B, A) => B): B =
      t.fold(operator(start, h))(operator)

    override def printElements: String =
      if (t.isEmpty) s"$h"
      else s"$h , ${t.printElements}"
  }

  @main def testMyList(): Unit = {
    val intList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Empty)))))
    val charList = Cons("a", Cons("b", Cons("c", Cons("d", Cons("e", Empty)))))

    println(intList.map(elem => "number " + elem))

    println(intList.filter(_ % 2 == 1))

    println(intList.flatMap(elt => Cons(elt, Cons(elt * 10, Empty))))

    intList.foreach(println)

    println(intList.sort(_ - _))

    println(intList.zipWith(charList, (i, s) => s + i))

    println(intList.fold(0)(_ + _))

    for {
      n <- intList
      c <- charList
    } println(s"$c-$n")
  }
}
