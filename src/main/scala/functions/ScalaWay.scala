package functions

object ScalaWay {

  //anonymous function or lambda
  val doubler = (x: Int) => x * 2

  //equivalent
  val doubler2: Int => Int = x => x * 2

  // multipler parameters
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  //no params
  val justDoSomething: () => Int = () => 3

  //curly braces
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _

  @main def testLambdas(): Unit = {
    println(justDoSomething)
    println(justDoSomething())
  }
}
