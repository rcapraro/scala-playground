package patternmatching

import mylist.MyList.{Cons, Empty, MyList}

object AllPatterns {

  @main
  def testAllPatterns(): Unit = {

    //1 - constants
    val x: Any = "Scala"
    val constants = x match {
      case 1 => "a number"
      case "Scala" => "THE Scala"
      case true => "The truth"
      case allpatterns => "A singleton object"
    }

    //2 - wildcards
    val matchAnything = x match {
      case _ =>
    }

    val matchAVariable = x match {
      case something => s"I've found $something"
    }

    //3 - tuples
    val aTuple = (1, 2)
    val matchATuple = aTuple match {
      case (1, 1) =>
      case (something, 2) => s"I've found $something"
    }

    val nestedTuple = (1, (2, 3))
    val matchANestedTuples = nestedTuple match {
      case (_, (2, v)) => s"I've found $v somewhere"
    }

    //4 - case classes - constructor pattern
    val aList: MyList[Int] = Cons(1, Cons(2, Empty))
    val matchAlist = aList match {
      case Empty =>
      case Cons(head, Cons(subhead, subtail)) =>
      case Cons(head, tail) =>
    }

    //5 - standard list patterns
    val aStandardList = List(1, 2, 3, 42)
    val standardListMatching = aStandardList match {
      case List(1, _, _, _) => //extractor - advanced
      case List(1, _*) => //list of arbitraty length - advanced
      case 1 :: List(_) => //infix pattern
      case List(1, 2, 3) :+ 42 => //infix patterm
    }

    //6 - type specifiers
    val unknown: Any = 2
    val unknownMatch = unknown match {
      case list: List[Int] => //explicit type specifier
      case _ =>
    }

    //7 - name binding
    val nameBindingMatch = aList match {
      case notEmptyList@Cons(_, _) => //name binding, use the name later (here)
      case Cons(1, rest@Cons(2, _)) => //name binding inside nested patterns
    }

    //8 - multi patterns
    val multiPattern = aList match {
      case Empty | Cons(0, _) => //compound pattern (multi pattern)
    }

    //9 - if guard
    val secondElementSpecial = aList match {
      case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
    }

    val numbers = List(1, 2, 3)
    val numbersMatch = numbers match {
      case listOfStrings: List[String] => "a list of strings"
      case listOfNumbers: List[Int] => "a list of numbers"
      case _ => ""
    }

    // returns a lit of strings !!!
    // JVM tric question - type erasure !

  }
}
