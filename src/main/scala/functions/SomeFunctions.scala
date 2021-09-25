package functions

object SomeFunctions {

  val concat: (String, String) => String = new Function2[String, String, String] {
    override def apply(s1: String, s2: String): String = s1 + s2
  }

  val adder: Int => Function[Int, Int] = new Function[Int, Function[Int, Int]] {
    override def apply(x: Int): Function[Int, Int] = new Function[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  @main def testFunctions: Unit =
    println(concat("Hello", " World"))
    println(adder(10)(3))

}
