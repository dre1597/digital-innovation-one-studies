package com.example.demo.controller

import com.example.demo.dto.request.CustomerDto
import com.example.demo.dto.request.CustomerUpdateDto
import com.example.demo.dto.response.CustomerView
import com.example.demo.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid dto: CustomerDto): ResponseEntity<CustomerView> {
        val savedCustomer = this.customerService.save(dto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(savedCustomer))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        this.customerService.delete(id)
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam("customerId") id: Long,
        @RequestBody @Valid dto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(id)
        val customerToUpdate = dto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customerUpdated))
    }
}
