open class Dog {
  open fun sayHello() {
    println("wow wow!")
  }
}

class Yorkshire : Dog() {
  override fun sayHello() {
    println("wif wif!")
  }
}

open class Lion(val name: String, val origin: String) {
  fun sayHello() {
    println("$name, the lion from $origin says: graoh!")
  }
}

class Asiatic(name: String) : Lion(name = name, origin = "India")


fun main() {
  val dog: Dog = Yorkshire()
  dog.sayHello()

  val lion: Lion = Asiatic("Rufo")
  lion.sayHello()
}
