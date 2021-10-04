package tuplesandmaps

object TuplesAndMaps {

  @main def testTuplesAndMaps(): Unit = {
    //val aTuple = new Tuple2(2, "Hello scala")
    //val aTuple = Tuples2(2, "hello scala")
    val aTuple = (2, "hello world")

    println(aTuple._2)
    val aTuple2 = aTuple.copy(_2 = "goodbye java")
    println(aTuple2.swap)

    val aMap = Map()

    val phoneBook = Map(("Jim", 555), ("Daniel", 789))
    val phoneBook2 = Map("Jim" -> 555, "Daniel" -> 789)
    println(phoneBook)

    println(phoneBook.contains("Jim"))
    println(phoneBook("Jim"))

    val phoneBook3 = phoneBook2.withDefaultValue(-1)
    println(phoneBook3("Mary"))

    val newPairing = "Mary" -> 678
    val newPhoneBoook = phoneBook + newPairing
    println(newPhoneBoook)

    //functional on maps
    println(newPhoneBoook.map(pair => pair._1.toLowerCase() -> pair._2))
    println(newPhoneBoook.view.filterKeys(_.startsWith("J")).toMap)
    println(newPhoneBoook.view.mapValues(_ * 10).toMap)

    //conversion to other collections
    println(newPhoneBoook.toList)
    println(List("Daniel" -> 555).toMap)
    val names = List("Anna", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0)))
  }

}
