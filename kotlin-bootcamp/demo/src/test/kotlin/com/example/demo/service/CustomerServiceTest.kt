package com.example.demo.service

import com.example.demo.entity.Address
import com.example.demo.entity.Customer
import com.example.demo.exception.BusinessException
import com.example.demo.repository.CustomerRepository
import com.example.demo.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        val fakeCustomer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        val actual = customerService.save(fakeCustomer)

        assertThat(actual).isNotNull
        assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        val fakeId = Random().nextLong()
        val fakeCustomer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        val actual: Customer = customerService.findById(fakeId)

        assertThat(actual).isNotNull
        assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        val fakeId = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`() {

        val fakeId = Random().nextLong()
        val fakeCustomer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs

        customerService.delete(fakeId)

        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "any_first_name",
            lastName: String = "any_last_name",
            cpf: String = "28475934625",
            email: String = "any_email@gmail.com",
            password: String = "any_password",
            zipCode: String = "any_zip_code",
            street: String = "any_street",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
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
            id = id
        )
    }
}
