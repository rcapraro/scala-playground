package oop

object AbstractDataTypes {
  abstract class Animal {
    val creatureType: String

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "canine"

    override def eat: Unit = println("crunch...crunch...crunch")
  }

  trait Carnivore {
    def eat(animal: Animal): Unit

    val preferedMeal: String = "fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "reptile"

    override def eat: Unit = println("croc...croc...croc")

    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  @main
  def testAbstract(): Unit = {
    val dog = new Dog
    val croc = new Crocodile
    croc.eat(dog)
  }
}
