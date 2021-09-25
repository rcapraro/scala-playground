package basics

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    //add fiels or methods
    def openDocument(): Unit =
      if (this == READ) println("opening document...")
      else println("reading not allowed")
  }

  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4) //100
    case WRITE extends PermissionsWithBits(2) //010
    case EXECUTE extends PermissionsWithBits(1) //001
    case NONE extends PermissionsWithBits(0) //000
  }

  object PermissionsWithBits {
    def fromBit(bits: Int): PermissionsWithBits =
      PermissionsWithBits.NONE
  }

  @main
  def testEnums(): Unit = {
    val somePermissions: Permissions = Permissions.WRITE
    somePermissions.openDocument()
    println(somePermissions.ordinal)
    for (permission <- Permissions.values) {
      println(permission)
    }
    println(Permissions.valueOf("READ") == Permissions.READ)
  }

}
