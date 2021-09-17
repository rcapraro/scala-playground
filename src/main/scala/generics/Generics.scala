package generics

object Generics {

  class MyList[+A] {
    def add[B >:A](element: B): MyList[B] = ???
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

  class Cage[A <: Animal](animal: A)

  @main
  def testGenerics(): Unit = {
    val listOfIntegers = new MyList[Int]
    val listOfStrings = new MyList[String]
    // val emptyListOfIntegers = MyList.empty[Int]

    val animal: Animal = new Cat
    val covariantAnimalList: CovariantList[Animal] = new CovariantList[Cat]

    val catList = new MyList[Cat]
    val animalList = catList.add(new Dog) //get transformed into a MyList[Animal]

    // val invariantAnimalList: InvariantList[Animal] = new InvariantList[Dog]  --- impossible

    val catTrainer: Trainer[Cat] = new Trainer[Animal]

    //can accept only subclasses of Animal
    val cage = new Cage(new Dog)
  }

}
