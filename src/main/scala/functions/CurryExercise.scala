package functions

object CurryExercise {

  def toCurry[A](f: (A, A) => A): A => A => A =
    x => y => f(x, y)

  def fromCurry[A](f: A => A => A): (A, A) => A =
    (x, y) => f(x)(y)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  @main
  def testCurryExercise(): Unit = {
    val curriedAdd = toCurry[Int](_ + _)

    println(curriedAdd(3)(4))

    println(fromCurry(curriedAdd)(5, 6))

    println(compose[Int, Int, Int](_ * 3, _ + 5)(2))
    println(andThen[Int, Int, Int](_ * 4, _ + 5)(2))
  }

}
