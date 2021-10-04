package tuplesandmaps

import scala.annotation.tailrec

object MapExercise {

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unFriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)
    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unFriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)

    unfriended - person
  }

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  @main
  def testMapExercise(): Unit = {

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Bob"), "Mary")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unFriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
    println(remove(friend(network, "Bob", "Mary"), "Bob"))

    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")
    println(testNet)
    println(nFriends(testNet, "Bob"))
    println(mostFriends(testNet))
    println(nPeopleWithNoFriends(testNet))
    println(socialConnection(testNet, "Mary", "Jim"))
  }

}
