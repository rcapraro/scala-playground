package stdlibrary

object StdLibrary {

  @main
  def testStdLibray(): Unit = {
    val list = List(1, 2, 3, 4)
    println(list.head)
    println(list.tail)
    println(list.filter(_ % 2 == 0))
    println(list.flatMap(x => List(x, x * 10)))

    // prints all combination between two lists
    val numbers = List(1, 2, 3, 4)
    val characters = List('a', 'b', 'c', 'd')
    val colors = List("black", "white")
    println(numbers.flatMap(numb => characters.flatMap(char => colors.map(color => s"$char$numb-$color"))))

    //for comprehension - same result
    val forCombination = for {
      numb <- numbers
      char <- characters
      color <- colors
    } yield s"$char$numb-$color"

    println(forCombination)

    val forCombinationEvens = for {
      numb <- numbers if numb % 2 == 0
      char <- characters
      color <- colors
    } yield s"$char$numb-$color"

    println(forCombinationEvens)

    for {
      n <- numbers
    } println(n)

    //syntax overload
    list.map { x => x * 2 }

  }

}
