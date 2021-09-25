package functions

object CurryExercise {

  def toCurry[A](f: (A, A) => A): A => A => A = (x: A) => (y: A) => f(x, y)

  def fromCurry[A](f: A => A => A): (A, A) => A = (x: A, y: A) => f(x)(y)

  def compose[A](f : A => A, g: A => A) = (x: A) => f(g(x))

  def andThen[A](f : A => A, g: A => A) = (x: A) => g(f(x))

  @main
  def testCurryExercise(): Unit = {
    val curriedAdd = toCurry[Int]((x, y) => x+y)

    println(curriedAdd(3)(4))

    println(fromCurry(curriedAdd)(5, 6))

    println(compose[Int](x => x * 3, x => x + 5)(2))
    println(andThen[Int](x => x * 4, x => x + 5)(2))
  }

}
