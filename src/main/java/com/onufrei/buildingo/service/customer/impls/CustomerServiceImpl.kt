package com.onufrei.buildingo.service.customer.impls

import com.onufrei.buildingo.model.Customer
import com.onufrei.buildingo.repository.CustomerRepository
import com.onufrei.buildingo.service.customer.interfaces.CustomerService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*


/** Represents object of CustomerServiceImpl
 * @author Artem Onufrei
 * @version 1
 * @since 12.05.2021
 */

@Service
class CustomerServiceImpl(private val repo: CustomerRepository) : CustomerService{
    override fun getCustomerContactText(): List<Pair<String, String>> {
        return repo.findAll().map { Pair(it.id, "${it.name} - ${it.contactName} ${it.contactSurname}") }
    }

    override fun findAll(): List<Customer> {
        return repo.findAll()
    }

    override fun findById(id: String): Customer? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Customer): Customer? {
        if(findById(e.id) == null) {
            val customer = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(customer)
            return customer
        }

        return null
    }

    override fun update(e: Customer): Customer? {
        findById(e.id)?.let {
            val customer = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(customer)
            return customer
        } ?: return null
    }

    override fun delete(id: String): Customer? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}