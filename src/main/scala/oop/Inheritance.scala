package oop

object Inheritance {

  sealed class Animal {
    val creatureType = "wild"

    protected def eat = println("miam..miam...miam")
  }

  class Cat extends Animal {
    def crunch =
      eat
      println("crunch...crunch...crunch")
  }

  class Dog extends Animal {
    override val creatureType = "domestic"

    override def eat =
      super.eat
      println("splotch...splotch...splotch")
  }

  class Bird(override val creatureType: String = "flying") extends Animal

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  @main
  def animalsTest(): Unit = {

    val cat = new Cat
    cat.crunch

    val dog = new Dog
    dog.eat
    println(dog.creatureType)

    val bird = new Bird
    println(bird.creatureType)
  }
}
