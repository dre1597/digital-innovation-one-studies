package com.example.demo.entity

import com.example.demo.enummeration.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Credit(
    var id: Long? = null,
    val creditCode: UUID = UUID.randomUUID(),
    val creditValue: BigDecimal = BigDecimal.ZERO,
    var dayFirstInstallment: LocalDate,
    var numberOfInstallments: Int = 0,
    var status: Status = Status.IN_PROGRESS,
    val customer: Customer? = null,
)
