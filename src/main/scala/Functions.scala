import scala.annotation.tailrec
import scala.math.pow

def greeting(name: String, age: Int) = println(s"Hello $name, you are $age years old!")

def factorial(n: Int): BigInt =
  @tailrec
  def helper(n: Int, acc: BigInt = 1): BigInt =
    if (n <= 1) acc
    else helper(n - 1, n * acc)

  helper(n)

def fibonacci(n: Int): BigInt =
  @tailrec
  def helper(n: Int, acc1: BigInt = 0, acc2: BigInt = 1): BigInt =
    if (n <= 2) acc1 + acc2
    else helper(n - 1, acc2, acc2 + acc1)

  helper(n)

def isPrime(n: Int): Boolean =
  @tailrec
  def helper(n: Int, i: Int = 2): Boolean =
    if (n <= 2) n == 2
    else if (n % i == 0) false
    else if (pow(i, 2) > n) true
    else helper(n, i + 1)

  helper(n)

def concatenateStringNTimes(s: String, n: Int): String =
  @tailrec
  def helper(n: Int, acc: String): String =
    if (n == 0) acc
    else helper(n - 1, s + acc)

  helper(n, "")

def formatBigIntResult(name: String, n: Int, f: Int => BigInt) =
  val message = "The %s of %d is %d."
  println(message.format(name, n, f(n)))

def formatBooleanResult(condition: String, n: Int, f: Int => Boolean) =
  val message = "is %d %s ? %s"
  println(message.format(n, condition, f(n)))

@main
def main() =
  greeting("richard", 46)
  formatBigIntResult("factorial", 1000, factorial)
  formatBigIntResult("fibonacci", 10, fibonacci)
  formatBooleanResult("a prime number", 188, isPrime)
  formatBooleanResult("a prime number", 239, isPrime)
  println(concatenateStringNTimes("foobar", 5))

