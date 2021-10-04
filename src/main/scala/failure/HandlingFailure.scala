package failure

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure {

  @main
  def handleFailure(): Unit = {
    val aSuccess = Success(3)
    val aFailure = Failure(new RuntimeException("Failure!"))

    def unsafeMethod(): String = throw new RuntimeException("Failure!")

    val potentialFailure = Try(unsafeMethod())
    println(potentialFailure)

    // syntax sugar
    val anotherPotentialfailure = Try {
      //code that might throw exception
    }

    // utilities
    println(potentialFailure.isSuccess)

    def backupMethod(): String = "A valid result"

    val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
    println(fallbackTry)

    //if you design an API
    def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

    def betterBackupMethod(): Try[String] = Success("A valid result")

    val betterFallback = betterBackupMethod().orElse(betterBackupMethod())
    println(betterFallback)

    //map, flatmap and filter
    println(aSuccess.map(_ * 2)) //Success(6)
    println(aSuccess.flatMap(x => Success(x * 10))) //Success(30)
    println(aSuccess.filter(_ > 10)) //Failure(NoSuchElementException("Predicate does not hold for 3"))

    val hostname = "localhost"
    val port = "8080"

    def renderHTML(page: String) = println(page)

    class Connection {
      def get(url: String): String =
        val random = new Random(System.nanoTime())
        if (random.nextBoolean()) "<html>...</html>"
        else throw new RuntimeException("Connection interrupted")

      def getSafe(url: String): Try[String] = Try(get(url))
    }

    object HttpService {
      val random = new Random(System.nanoTime())

      def getConnection(host: String, port: String): Connection = {
        if (random.nextBoolean()) new Connection
        else throw new RuntimeException("Someone else took the port")
      }

      def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
    }

    val possibleConnection = HttpService.getSafeConnection(hostname, port)
      .flatMap(connection => connection.getSafe("/home"))
      .foreach(renderHTML)

    val betterConnection = for {
      connection <- HttpService.getSafeConnection(hostname, port)
      content <- connection.getSafe("/home")
    } renderHTML(content)

  }

}
