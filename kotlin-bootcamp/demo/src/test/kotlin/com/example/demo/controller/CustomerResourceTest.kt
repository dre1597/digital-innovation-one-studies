package com.example.demo.controller

import com.example.demo.dto.request.CustomerDto
import com.example.demo.dto.request.CustomerUpdateDto
import com.example.demo.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CustomerResourceTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @AfterEach
    fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should create a customer and return 201 status`() {
        val customerDto = builderCustomerDto()
        val valueAsString = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(
            post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.firstName").value("any_first_name"))
            .andExpect(jsonPath("$.lastName").value("any_last_name"))
            .andExpect(jsonPath("$.cpf").value("28475934625"))
            .andExpect(jsonPath("$.email").value("any_email@email.com"))
            .andExpect(jsonPath("$.income").value("1000.0"))
            .andExpect(jsonPath("$.zipCode").value("any_zip_code"))
            .andExpect(jsonPath("$.street").value("any_street"))
            .andExpect(jsonPath("$.id").value(1))
            .andDo(print())
    }

    @Test
    fun `should not save a customer with same CPF and return 409 status`() {
        customerRepository.save(builderCustomerDto().toEntity())
        val customerDto = builderCustomerDto()
        val valueAsString = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(
            post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(status().isConflict)
            .andExpect(jsonPath("$.title").value("Conflict! Consult the documentation"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(409))
            .andExpect(
                jsonPath("$.exception")
                    .value("class org.springframework.dao.DataIntegrityViolationException")
            )
            .andExpect(jsonPath("$.details[*]").isNotEmpty)
            .andDo(print())
    }

    @Test
    fun `should not save a customer with empty firstName and return 400 status`() {
        val customerDto = builderCustomerDto(firstName = "")
        val valueAsString = objectMapper.writeValueAsString(customerDto)

        mockMvc.perform(
            post(URL)
                .content(valueAsString)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(
                jsonPath("$.exception")
                    .value("class org.springframework.web.bind.MethodArgumentNotValidException")
            )
            .andExpect(jsonPath("$.details[*]").isNotEmpty)
            .andDo(print())
    }

    @Test
    fun `should find customer by id and return 200 status`() {
        val customer = customerRepository.save(builderCustomerDto().toEntity())

        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("any_first_name"))
            .andExpect(jsonPath("$.lastName").value("any_last_name"))
            .andExpect(jsonPath("$.cpf").value("28475934625"))
            .andExpect(jsonPath("$.email").value("any_email@email.com"))
            .andExpect(jsonPath("$.income").value("1000.0"))
            .andExpect(jsonPath("$.zipCode").value("any_zip_code"))
            .andExpect(jsonPath("$.street").value("any_street"))
            .andDo(print())
    }

    @Test
    fun `should not find customer with invalid id and return 400 status`() {
        val invalidId = 2L

        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$invalidId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(
                jsonPath("$.exception")
                    .value("class com.example.demo.exception.BusinessException")
            )
            .andExpect(jsonPath("$.details[*]").isNotEmpty)
            .andDo(print())
    }

    @Test
    fun `should delete customer by id and return 204 status`() {
        val customer = customerRepository.save(builderCustomerDto().toEntity())

        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${customer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
            .andDo(print())
    }

    @Test
    fun `should not delete customer by id and return 400 status`() {
        val invalidId = Random().nextLong()

        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${invalidId}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(
                jsonPath("$.exception")
                    .value("class com.example.demo.exception.BusinessException")
            )
            .andExpect(jsonPath("$.details[*]").isNotEmpty)
            .andDo(print())
    }

    @Test
    fun `should update a customer and return 200 status`() {
        val customer = customerRepository.save(builderCustomerDto().toEntity())
        val customerUpdateDto = builderCustomerUpdateDto()
        val valueAsString = objectMapper.writeValueAsString(customerUpdateDto)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?customerId=${customer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.firstName").value("updated_first_name"))
            .andExpect(jsonPath("$.lastName").value("updated_last_name"))
            .andExpect(jsonPath("$.cpf").value("28475934625"))
            .andExpect(jsonPath("$.email").value("any_email@email.com"))
            .andExpect(jsonPath("$.income").value("5000.0"))
            .andExpect(jsonPath("$.zipCode").value("updated_zip_code"))
            .andExpect(jsonPath("$.street").value("updated_street"))
            .andDo(print())
    }

    @Test
    fun `should not update a customer with invalid id and return 400 status`() {
        val invalidId = Random().nextLong()
        val customerUpdateDto = builderCustomerUpdateDto()
        val valueAsString = objectMapper.writeValueAsString(customerUpdateDto)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?customerId=$invalidId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.title").value("Bad Request! Consult the documentation"))
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(
                jsonPath("$.exception")
                    .value("class com.example.demo.exception.BusinessException")
            )
            .andExpect(jsonPath("$.details[*]").isNotEmpty)
            .andDo(print())
    }

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

    private fun builderCustomerUpdateDto(
        firstName: String = "updated_first_name",
        lastName: String = "updated_last_name",
        income: BigDecimal = BigDecimal.valueOf(5000.0),
        zipCode: String = "updated_zip_code",
        street: String = "updated_street"
    ): CustomerUpdateDto = CustomerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        income = income,
        zipCode = zipCode,
        street = street
    )
}
