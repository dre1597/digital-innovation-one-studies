package com.example.demo.repository

import com.example.demo.entity.Address
import com.example.demo.entity.Credit
import com.example.demo.entity.Customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepository: CreditRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var creditOne: Credit
    private lateinit var creditTwo: Credit

    @BeforeEach fun setup () {
        customer = testEntityManager.persist(buildCustomer())
        creditOne = testEntityManager.persist(buildCredit(customer = customer))
        creditTwo = testEntityManager.persist(buildCredit(customer = customer))
    }

    @Test
    fun `should find credit by credit code`() {
        val creditCodeOne = UUID.fromString("a1b2c3d4-e5f6-a7b8-c9d1-e2f3a4b5c6d7")
        val creditCodeTwo = UUID.fromString("a1b2c3d4-e5f6-a7b8-c9d1-e2f3a4b5c6d8")
        creditOne.creditCode = creditCodeOne
        creditTwo.creditCode = creditCodeTwo

        val fakeCreditOne = creditRepository.findByCreditCode(creditCodeOne)
        val fakeCreditTwo = creditRepository.findByCreditCode(creditCodeTwo)

        assertThat(fakeCreditOne).isNotNull
        assertThat(fakeCreditTwo).isNotNull
        assertThat(fakeCreditOne).isEqualTo(creditOne)
        assertThat(fakeCreditTwo).isEqualTo(creditTwo)
    }

    @Test
    fun `should find all credits by customer id`() {
        val customerId = 1L;
        val creditList = creditRepository.findAllByCustomerId(customerId);

        assertThat(creditList).isNotEmpty()
        assertThat(creditList).hasSize(2)
        assertThat(creditList).contains(creditOne, creditTwo)
    }

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2024, Month.APRIL, 1),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )

    private fun buildCustomer(
        firstName: String = "any_first_name",
        lastName: String = "any_last_name",
        cpf: String = "28475934625",
        email: String = "any_email@gmail.com",
        password: String = "any_password",
        zipCode: String = "any_zip_code",
        street: String = "any_street",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
    )
}
