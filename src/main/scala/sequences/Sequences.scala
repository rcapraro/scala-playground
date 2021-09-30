package sequences

import java.util.Random

object Sequences {

  val seq = Seq(1, 3, 4, 2)

  val aRange = 1 to 10

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime
      collection.updated(r.nextInt(maxCapacity), r.nextInt)
      System.nanoTime - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  @main
  def testSequences() =

    //sequences
    println(seq)
    println(seq.reverse)
    println(seq(2))
    println(seq ++ Seq(5, 6))
    println(seq.sorted)

    println

    //ranges
    aRange.foreach(print)
    (1 to 10).foreach(x => print("#"))

    println

    //lists
    val aList = List(1, 2, 3)
    val prepended = 42 :: aList
    val newList = 42 +: aList :+ 89
    println(newList)

    val apples5 = List.fill(5)("apple")
    println(apples5)

    println(aList.mkString("-"))

    //arrays
    val numbers = Array(1, 2, 3, 4)
    val threeElements = Array.ofDim[String](3)
    println(threeElements.mkString(", "))

    numbers(2) = 0
    println(numbers.mkString(", "))

    //arrays and seq
    val numbersSeq: Seq[Int] = numbers.toIndexedSeq //no implicit conversion
    println(numbersSeq)

    //vectors
    val vector: Vector[Int] = Vector(1, 2, 3)

    //vectors vs lists
    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    println(getWriteTime(numbersList))
    println(getWriteTime(numbersVector))

}
