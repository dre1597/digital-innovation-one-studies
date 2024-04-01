package com.example.demo.entity

data class Customer(
    val id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var cpf: String = "",
    var email: String = "",
    var password: String = "",
    var address: Address = Address(),
    var credits: List<Credit> = mutableListOf()
)
