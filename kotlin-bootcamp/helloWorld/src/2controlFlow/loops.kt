fun eatACake() = println("Eat a Cake")
fun bakeACake() = println("Bake a Cake")

class Animal(val name: String)

class Zoo(val animals: List<Animal>) {

  operator fun iterator(): Iterator<Animal> {
    return animals.iterator()
  }
}

fun main() {
  val cakes = listOf("carrot", "cheese", "chocolate")

  for (cake in cakes) {
    println("Yummy, it's a $cake cake!")
  }

  var cakesEaten = 0
  var cakesBaked = 0

  while (cakesEaten < 5) {
    eatACake()
    cakesEaten++
  }

  do {
    bakeACake()
    cakesBaked++
  } while (cakesBaked < cakesEaten)

  val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))

  for (animal in zoo) {
    println("Watch out, it's a ${animal.name}")
  }
}
