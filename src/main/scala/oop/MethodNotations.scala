package oop

import scala.language.postfixOps

object MethodNotations {

  class Person(val name: String, val age: Int = 0) {
    def +(nickName: String) = new Person(name + " " + nickName, this.age)

    def unary_+ = new Person(this.name, age + 1)

    def learns(skills: String) = println(s"${this.name} learns $skills")

    def learnScala = this.learns("Scala")

    def apply(times: Int) = println(s"${this.name} was registered $times times!")
  }

  @main
  def testPerson() = {
    var p = new Person("Richard", 46)
    p = p + "the best"
    println(p.name)
    p = +p
    println(p.age)
    p learnScala;
    p(2)
  }
}
