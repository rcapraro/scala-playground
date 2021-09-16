package generics

object Generics {

  class MyList[A] {

  }

  object MyList {
    def empty[A]: MyList[A] = ???
  }

  class MyMap[Key, Value]

  trait Truc[A]

  //variance problems
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1 - yes MyList[Cat] extends MyList[Animal] - covariance
  class CovariantList[+A]

  // 2 - NO - invariance
  class InvariantList[A]

  // Hell No - contravariance
  class Trainer[-A]

  @main
  def testGenerics(): Unit = {
    val listOfIntegers = new MyList[Int]
    val listOfStrings = new MyList[String]
    val emptyListOfIntegers = MyList.empty[Int]

    val animal: Animal = new Cat
    val covariantAnimalList: CovariantList[Animal] = new CovariantList[Cat]
    // animalList.add(new Dog) ? HARD QUESTION

    // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Dog]  --- impossible

    val catTrainer: Trainer[Cat] = new Trainer[Animal]
  }

}
