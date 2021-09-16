package oop

object PersonObjects {

  object Person {
    val N_EYES = 2

    def canFly: Boolean = false

    //factory method
    def apply(mother: Person, father: Person): Person = new Person("child")
  }

  //companion class
  class Person(val name: String) {

  }

  @main
  def objects() =
    println(Person.N_EYES)
    println(Person.canFly)
    val richard = Person
    val richard2 = Person
    println(richard == richard2) //singleton instance : true

    var richardP = new Person("Richard")
    var richardP2 = new Person("Richard")
    println(richardP == richardP2) // instance: false


    val mary = new Person("Mary")
    val john = new Person("John")
    val child = Person(mary, john)
}
