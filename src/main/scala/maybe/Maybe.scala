package maybe

object Maybe {

  abstract class Maybe[+A] {
    def filter(predicate: A => Boolean): Maybe[A]

    def map[B](transformer: A => B): Maybe[B]

    def flatMap[B](transformer: A => Maybe[B]): Maybe[B]
  }

  case object MaybeNot extends Maybe[Nothing] {

    override def filter(predicate: Nothing => Boolean) = MaybeNot

    override def map[B](transformer: Nothing => B) = MaybeNot

    override def flatMap[B](transformer: Nothing => Maybe[B]) = MaybeNot
  }

  case class Just[+A](elt: A) extends Maybe[A] {

    override def filter(predicate: A => Boolean) =
      if (predicate(elt)) this
      else MaybeNot

    override def map[B](transformer: A => B) =
      Just(transformer(elt))

    override def flatMap[B](transformer: A => Maybe[B]) =
      transformer(elt)
  }

  @main
  def testMaybe(): Unit = {
    val just3 = Just(3)
    val just4 = Just(4)
    println(just3.filter(_ % 2 == 0))
    println(just4.filter(_ % 2 == 0))
    println(just3.map(_ * 2))
    println(just3.flatMap(x => Just(x > 2)))
  }

}
