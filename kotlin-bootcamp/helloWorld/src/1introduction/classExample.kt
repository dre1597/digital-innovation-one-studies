package `1introduction`

class Customer

class Contact(val id: Int, var email: String)

fun main() {
  val customer = Customer()
  val contact = Contact(1, "a@a.com")

  println(customer)
  println(contact.id)
  println(contact.email)
  contact.email = "b@b.com"
  println(contact.id)
  println(contact.email)
}
