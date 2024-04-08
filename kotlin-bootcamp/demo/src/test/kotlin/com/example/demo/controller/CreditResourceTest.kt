package com.example.demo.controller

import com.example.demo.dto.request.CreditDto
import com.example.demo.dto.request.CustomerDto
import com.example.demo.repository.CreditRepository
import com.example.demo.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CreditResourceTest {
    @Autowired private lateinit var creditRepository: CreditRepository
    @Autowired private lateinit var customerRepository: CustomerRepository

    @Autowired private lateinit var mockMvc: MockMvc

    @Autowired private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/credits"
    }

    private var customerId: Long? = null

    @BeforeEach
    fun setup() {
        creditRepository.deleteAll()
        customerRepository.deleteAll()
        val customer = customerRepository.save(builderCustomerDto().toEntity())
        customerId = customer.id
    }

    @AfterEach
    fun tearDown() {
        creditRepository.deleteAll()
        customerRepository.deleteAll()
    }

    @Test
    fun `should create a credit and return 201 status`() {
        val creditDto = builderCreditDto()
        println(creditDto)
        val valueAsString = objectMapper.writeValueAsString(creditDto)

        val result = mockMvc.perform(
            post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(status().isCreated)
            .andReturn()

        val responseBody = result.response.contentAsString
        val creditCode = extractCreditCode(responseBody)
        val expectedMessage = "Credit $creditCode - Customer any_email@email.com saved!"
        assertThat(creditCode).isNotNull()
        assertThat(isValidUUID(creditCode)).isTrue()
        assertThat(responseBody).matches(expectedMessage)
    }

    private fun extractCreditCode(responseBody: String): String {
        val regex = Regex("Credit ([-\\w]+)")
        val matchResult = regex.find(responseBody)
        return matchResult?.groups?.get(1)?.value ?: ""
    }

    private fun isValidUUID(uuid: String): Boolean {
        return try {
            UUID.fromString(uuid)
            true
        } catch (ex: IllegalArgumentException) {
            false
        }
    }

    private fun builderCreditDto(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstOfInstallment: LocalDate = LocalDate.now().plusMonths(1),
        numberOfInstallments: Int = 5,
        customerId: Long = this.customerId!!
    ): CreditDto = CreditDto(
        creditValue = creditValue,
        dayFirstOfInstallment = dayFirstOfInstallment,
        numberOfInstallments = numberOfInstallments,
        customerId = customerId
    )

    private fun builderCustomerDto(
        firstName: String = "any_first_name",
        lastName: String = "any_last_name",
        cpf: String = "28475934625",
        email: String = "any_email@email.com",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        password: String = "any_password",
        zipCode: String = "any_zip_code",
        street: String = "any_street",
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        zipCode = zipCode,
        street = street
    )
}
