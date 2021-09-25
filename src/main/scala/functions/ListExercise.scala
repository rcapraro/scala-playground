package functions

object ListExercise {

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

    def zipWith[B >: A](list: MyList[B], zipper: (A, A) => B): MyList[B]

    def fold[B >: A](startValue: B, foldFn: A => B): B

    protected[functions] def printElements: String

    override def toString: String = "[" + printElements + "]"
  }

  case object Empty extends MyList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException

    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

    override def filter(predicate: Nothing => Boolean) = Empty

    override def map[B](transformer: Nothing => B) = Empty

    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    override def flatMap[B](transformer: Nothing => MyList[B]) = Empty

    override def foreach(f: Nothing => Unit): Unit = ()

    override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

    override def zipWith[B >: Nothing](list: MyList[B], zipper: (Nothing, Nothing) => B): MyList[B] = ???

    override def fold[B >: Nothing](startValue: B, foldFn: Nothing => B): B = ???

    override def printElements: String = ""
  }

  case class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    override def head: A = h

    override def tail: MyList[A] = t

    override def isEmpty: Boolean = false

    override def filter(predicate: A => Boolean) = {
      if predicate(h) then new Cons(h, t.filter(predicate))
      else t.filter(predicate)
    }

    override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

    override def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    override def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

    override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ tail.flatMap(transformer)

    override def foreach(f: A => Unit): Unit = {
      f(h)
      tail.foreach(f)
    }

    override def sort(compare: (A, A) => Int): MyList[A] = {

      def insert(x: A, sortedList: MyList[A]): MyList[A] =
        if (sortedList.isEmpty) new Cons(x, Empty)
        else if (compare(x, sortedList.head)<=0) new Cons(x, sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))

      val sortedtail = t.sort(compare)
      insert(h, sortedtail)
    }

    override def zipWith[B >: A](list: MyList[B], zipper: (A, A) => B): MyList[B] = ???

    override def fold[B >: A](startValue: B, foldFn: A => B): B = ???

    override def printElements: String =
      if (t.isEmpty) s"$h"
      else s"$h , ${t.printElements}"
  }

  @main def testExerciceFunctions(): Unit = {
    val intList = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))

    println(intList.map(elem => "number " + elem))

    println(intList.filter(_ % 2 == 1))

    println(intList.flatMap(elt => new Cons(elt, new Cons(elt * 10, Empty))))

    val concat = (s1: String, s2: String) => s1 + s2

    val adder = (x: Int) => (y: Int) => x + y

    println(adder(10)(3))

    intList.foreach(println)
  }
}
