class MutableStack<E>(vararg items: E) {
  private val elements = items.toMutableList()

  fun push(element: E) = elements.add(element)

  fun peek(): E = elements.last()

  fun pop(): E = elements.removeAt(elements.size - 1)

  fun isEmpty(): Boolean = elements.isEmpty()

  fun size(): Int = elements.size

  override fun toString(): String = "MutableStack(${elements.joinToString()})"
}

fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

fun main() {
  val stack = mutableStackOf(0, 1, 2)
  println(stack)
}
