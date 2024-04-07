package com.example.demo.service

import com.example.demo.entity.Credit
import com.example.demo.entity.Customer
import com.example.demo.exception.BusinessException
import com.example.demo.repository.CreditRepository
import com.example.demo.service.impl.CreditService
import com.example.demo.service.impl.CustomerService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.unmockkAll
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*


@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK
    lateinit var creditRepository: CreditRepository

    @MockK
    lateinit var customerService: CustomerService

    @InjectMockKs
    lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create credit `() {
        val credit = buildCredit()
        val customerId = 1L

        every { customerService.findById(customerId) } returns credit.customer!!
        every { creditRepository.save(credit) } returns credit

        val actual = this.creditService.save(credit)

        verify(exactly = 1) { customerService.findById(customerId) }
        verify(exactly = 1) { creditRepository.save(credit) }

        assertThat(actual).isNotNull
        assertThat(actual).isSameAs(credit)
    }

    @Test
    fun `should not create credit when invalid day first installment`() {
        val invalidDayFirstInstallment = LocalDate.now().plusMonths(5)
        val credit = buildCredit(dayFirstInstallment = invalidDayFirstInstallment)

        every { creditRepository.save(credit) } answers { credit }

        assertThatThrownBy { creditService.save(credit) }
            .isInstanceOf(BusinessException::class.java)
            .hasMessage("First installment must be at least 3 months in the future")
        verify(exactly = 0) { creditRepository.save(any()) }
    }

    @Test
    fun `should return list of credits for a customer`() {
        val customerId = 1L
        val expectedCredits = listOf(buildCredit(), buildCredit(), buildCredit())

        every { creditRepository.findAllByCustomerId(customerId) } returns expectedCredits

        val actual = creditService.findAllByCustomer(customerId)

        assertThat(actual).isNotNull
        assertThat(actual).isNotEmpty
        assertThat(actual).isSameAs(expectedCredits)

        verify(exactly = 1) { creditRepository.findAllByCustomerId(customerId) }
    }

    @Test
    fun `should return credit for a valid customer and credit code`() {
        val customerId = 1L
        val creditCode = UUID.randomUUID()
        val credit = buildCredit(customer = Customer(id = customerId))

        every { creditRepository.findByCreditCode(creditCode) } returns credit

        val actual = creditService.findByCreditCode(customerId, creditCode)

        assertThat(actual).isNotNull
        assertThat(actual).isSameAs(credit)

        verify(exactly = 1) { creditRepository.findByCreditCode(creditCode) }
    }

    @Test
    fun `should throw BusinessException for invalid credit code`() {
        val customerId = 1L
        val invalidCreditCode = UUID.randomUUID()

        every { creditRepository.findByCreditCode(invalidCreditCode) } returns null

        assertThatThrownBy { creditService.findByCreditCode(customerId, invalidCreditCode) }
            .isInstanceOf(BusinessException::class.java)
            .hasMessage("Credit code $invalidCreditCode not found")
        verify(exactly = 1) { creditRepository.findByCreditCode(invalidCreditCode) }
    }

    @Test
    fun `should throw IllegalArgumentException for different customer ID`() {
        val customerId = 1L
        val creditCode = UUID.randomUUID()
        val credit = buildCredit(customer = Customer(id = 2L))

        every { creditRepository.findByCreditCode(creditCode) } returns credit

        assertThatThrownBy { creditService.findByCreditCode(customerId, creditCode) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Contact admin")
        verify { creditRepository.findByCreditCode(creditCode) }
    }

    companion object {
        private fun buildCredit(
            creditValue: BigDecimal = BigDecimal.valueOf(100.0),
            dayFirstInstallment: LocalDate = LocalDate.now().plusMonths(2L),
            numberOfInstallments: Int = 15,
            customer: Customer = CustomerServiceTest.buildCustomer()
        ): Credit = Credit(
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )
    }
}
