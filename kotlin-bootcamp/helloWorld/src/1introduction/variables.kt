package `1introduction`

fun someCondition(): Boolean {
  return true
}

fun main() {
  var a: String = "initial"
  println(a)
  val b: Int = 1
  val c = 3

  var e: Int

  //  println(e) // generates an error
  val d: Int = if (someCondition()) {
    1
  } else {
    2
  }

  println(d)
}
