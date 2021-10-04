package options

import scala.util.Random

object Options {

  @main
  def testOptions(): Unit = {
    val myFirstOption = Some(4)
    val noOption: Option[Int] = None

    //work with unsafe APIS
    def unsafeMethod(): String = null

    val result = Some(unsafeMethod()) // wrong !
    val goodResult = Option(unsafeMethod())

    //chained methods
    def backupMethod(): String = "A valid result"

    val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

    //Design unsafe APIS
    def betterUnsafeMethod(): Option[String] = None

    def betterBackupMethod(): Option[String] = Some("A valid result")

    val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

    //functions on options
    println(myFirstOption.isEmpty)
    println(myFirstOption.get) //unsafe

    //map, flatmap, filter
    println(myFirstOption.map(_ * 2)) //Some(8)
    println(myFirstOption.filter(_ > 10)) //None
    println(myFirstOption.flatMap(x => Option(x * 10))) //Option(40)

    //for comprehension
    val config: Map[String, String] = Map(
      //fetched from elsewhere

      "host" -> "176.45.36.1",
      "port" -> "80"
    )

    class Connection {
      def connect = "Connected" // connect to some server
    }

    object Connection {
      val random = new Random(System.nanoTime())

      def apply(host: String, port: String): Option[Connection] =
        if (random.nextBoolean()) Some(new Connection)
        else None
    }

    //try to establish a connection, if so print the connect method
    val status = config.get("host")
      .flatMap(h => config.get("port")
        .flatMap(p => Connection(h, p))
        .map(_.connect))
      .foreach(println)

    val betterStatus = for {
      host <- config get "host"
      port <- config get "port"
      connection <- Connection(host, port)
    } yield connection.connect

    betterStatus.foreach(println)
  }

}
