package patternmatching

import scala.util.Random

object Patternmatching {

  @main
  def testpm(): Unit = {

    //swithc on steroids
    val random = new Random()
    val x = random.nextInt(5)

    val description = x match {
      case 1 => "the ONE"
      case 2 => "double or nothing"
      case 3 => "third time is the charm"
      case _ => "something else"
    }

    println(x)
    println(description)

    // 1 - decompose values
    case class Person(name: String, age: Int)
    val bob = Person("Bob", 20)
    val greeting = bob match {
      case Person(n, a) if a < 21 => s"Hi, may name is $n and i can't drink in the US"
      case Person(n, a) => s"Hi, may name is $n and i am $a years old"
      case _ => "I don't know who i am"
    }

    println(greeting)

    // PM on sealed hierarchy
    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal = Dog("Terra Nova")
    animal match {
      case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    }

    trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Prod(e1: Expr, e2: Expr) extends Expr

    def show(e: Expr): String = e match {
        case Number(n) => s"$n"
        case Sum(e1, e2) => show(e1) + " + "+ show(e2)
        case Prod(e1, e2) => {
          def maybeShowParenthesis(exp: Expr) = exp match {
            case Prod(_, _) => show(exp)
            case Number(_) => show(exp)
            case _ => "("+ show(exp) + ")"
          }
          maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
        }
      }

    println(show(Sum(Number(2), Number(3))))
    println(show(Sum(Prod(Number(2), Number(4)), Number(3)))) // 2 * 4 + 3
    println(show(Prod(Sum(Number(3), Number(5)), Number(2)))) // (3 * 5) + 2
  }

}
