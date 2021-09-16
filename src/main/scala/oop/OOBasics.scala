package oop

object OOBasics {

  class Writer(val firstName: String, val lastName: String, val year: Int) {
    def fullName = firstName + " " + lastName
  }

  class Novel(val name: String, val yearOfRelease: Int, val author: Writer) {
    def authorAge: Int = yearOfRelease - author.year

    def isWrittenBy(authorName: String) = this.author.fullName == authorName

    def copy(newYear: Int): Novel = new Novel(this.name, newYear, this.author)
  }

  class Counter(val value: Int) {
    def increment = new Counter(this.value + 1)

    def decrement = new Counter(this.value - 1)

    def increment(amount: Int) = new Counter(this.value + amount)

    def decrement(amount: Int) = new Counter(this.value - amount)
  }

  @main
  def oop() =
    val dickens = new Writer("Charles", "Dickens", 1812)
    val davidCopperfield = new Novel("David Copperfield", 1850, dickens)
    println(davidCopperfield isWrittenBy "Charles Dickens")
}


