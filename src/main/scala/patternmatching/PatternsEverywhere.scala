package patternmatching

object PatternsEverywhere {

  @main
  def testEverywhere(): Unit = {

    val result = try {

    } catch {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "something else"
    }
    //catch are actually MATCHES !

    val list = List(1, 2, 3, 4)
    val evenOnes = for {
      x <- list if x % 2 == 0
    } yield 10 * x

    println(evenOnes)
    //generators are also base on PATTERN MATCHING !

    val tuples = List((1, 2), (3, 4))
    val filterTuples = for {
      (first, second) <- tuples
    } yield first * second

    println(filterTuples)

    val tuple = (1, 2, 3)
    val (a, b, c) = tuple
    //multiple value definition based on pattern matching

    val head :: tail = List(1, 2, 3, 4)
    println(head)
    println(tail)

    val mappedList = list.map {
      case v if v % 2 == 0 => "%d is even".format(v)
      case 1 => "the one"
      case _ => "something else"
    }
    // partial function literal
    println(mappedList)
  }

}
