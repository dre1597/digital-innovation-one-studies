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

fun main() {
  val hello: String = "Hello, World!"
  printMessage(hello)
  printMessageWithPrefix(hello)
  printMessageWithPrefix(hello, "Log")
  println(sum(1, 2))
  println(multiply(2, 3))
}
