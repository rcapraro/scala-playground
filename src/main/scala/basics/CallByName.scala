package basics

def callByValue(x: Long): Unit =
  println("by value " + x)
  println("by value " + x)

def callByName(x: => Long): Unit =
  println("by name " + x)
  println("by name " + x)

def infinite(): Int = 1 + infinite()
def printFirst(x: Int, y: => Int) = println(x)

@main
def calls() =
  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  //printFirst(infinite(), 34)  Stack overflow
  printFirst(34, infinite()) //works !

