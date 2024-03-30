package `1introduction`

fun printAll(vararg messages: String) {
  for (m in messages) println(m)
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {
  for (m in messages) println(prefix + m)
}


fun log(vararg entries: String) {
  printAll(*entries)
}

fun main() {
  printAll("Hello", "Hallo", "Salut", "Hola", "你好")

  printAllWithPrefix(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefix = "Greeting: "
  )

  log("Hello", "Hallo", "Salut", "Hola", "你好")
}
