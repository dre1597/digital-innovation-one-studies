package `1introduction`

fun printMessage(message: String): Unit {
  println(message)
}

fun printMessageWithPrefix(message: String, prefix: String = "Info") {
  println("[$prefix] $message")
}

fun sum(x: Int, y: Int): Int {
  return x + y
}

fun multiply(x: Int, y: Int) = x * y

class Person(val name: String) {
  private val likedPeople = mutableListOf<Person>()
  infix fun likes(other: Person) {
    likedPeople.add(other)
  }
}

fun main() {
  val hello: String = "Hello, World!"
  printMessage(hello)
  printMessageWithPrefix(hello)
  printMessageWithPrefix(hello, "Log")
  println(sum(1, 2))
  println(multiply(2, 3))

//  infix fun Int.times(str: String) = str.repeat(this)
//  println(2 times "Bye ")

  val pair = "Ferrari" to "Katrina"
  println(pair)

  infix fun String.onto(other: String) = Pair(this, other)
  val myPair = "McLaren" onto "Lucas"
  println(myPair)

  val sophia = Person("Sophia")
  val claudia = Person("Claudia")
  sophia likes claudia

  operator fun Int.times(str: String) = str.repeat(this)
  println(2 * "Bye ")

  operator fun String.get(range: IntRange) = substring(range)
  val str = "Always forgive your enemies; nothing annoys them so much."
  println(str[0..14])

  fun printAll(vararg messages: String) {
    for (m in messages) println(m)
  }
  printAll("Hello", "Hallo", "Salut", "Hola", "你好")

  fun printAllWithPrefix(vararg messages: String, prefix: String) {
    for (m in messages) println(prefix + m)
  }
  printAllWithPrefix(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefix = "Greeting: "
  )

  fun log(vararg entries: String) {
    printAll(*entries)
  }
  log("Hello", "Hallo", "Salut", "Hola", "你好")
}
