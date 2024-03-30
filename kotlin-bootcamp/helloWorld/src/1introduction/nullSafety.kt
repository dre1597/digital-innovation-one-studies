package `1introduction`

fun main() {
  var neverNull: String = "this can't be null"
//    neverNull = null

  var canBeNull: String? = "this can be null"
  canBeNull = null

  var inferredNonNull = "Hello"
//  inferredNonNull = null

  strLength(neverNull)

  println(describeString(neverNull))
  println(describeString(canBeNull))
  println(describeString(inferredNonNull))
}

fun strLength(notNull: String): Int {
  return notNull.length
}

fun describeString(maybeString: String?): String {
  if (maybeString != null && maybeString.length > 0) {
    return "String of length ${maybeString.length}"
  }

  return "Empty or null string"
}
