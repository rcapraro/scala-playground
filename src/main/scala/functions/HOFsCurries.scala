package functions

import scala.annotation.tailrec

object HOFsCurries {

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  def nTimesBetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) x => x
    else x => nTimesBetter(f, n - 1)(f(x))


  //curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y

  //functions with multiple parameters
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  @main
  def testHOF(): Unit = {
    val plusOne = (x: Int) => x + 1
    println(nTimes(plusOne, 10, 1))

    val plus10 = nTimesBetter(plusOne, 10)
    println(plus10(1))

    val add3 = superAdder(3)
    println(add3(4))

    val standardFormat: Double => String = curriedFormatter("%4.2f")
    val preciseFormat: Double => String = curriedFormatter("%4.8f")

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
  }

}
