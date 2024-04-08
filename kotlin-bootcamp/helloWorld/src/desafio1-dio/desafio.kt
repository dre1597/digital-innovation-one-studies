package `desafio1-dio`

enum class Level { BASIC, INTERMEDIATE, ADVANCED, EXPERT }

class Student(var name: String, val level: Level = Level.BASIC)

data class EducationalContent(var name: String, val duration: Int = 60)

data class Training(val name: String, var content: List<EducationalContent>) {

  val subscribers = mutableListOf<Student>()

  fun subscribe(student: Student) {
    subscribers.add(student)
    for (content in content) {
      println("${student.name} subscribed to ${content.name}")
    }
  }
}

fun main() {
  val course1 = EducationalContent("Course 1", 120)
  val course2 = EducationalContent("Course 2", 120)

  val training = Training("DIO", listOf(course1, course2))
  val student = Student("Alex", Level.BASIC)
  training.subscribe(student)

  for (student in training.subscribers) {
    println(student.name)
  }
}
