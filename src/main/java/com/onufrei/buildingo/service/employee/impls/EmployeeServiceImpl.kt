package com.onufrei.buildingo.service.employee.impls

import com.onufrei.buildingo.model.Employee
import com.onufrei.buildingo.repository.EmployeeRepository
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class EmployeeServiceImpl(private val repo: EmployeeRepository) : EmployeeService {

    override fun findAll(): List<Employee> {
        return repo.findAll()
    }

    override fun findById(id: String): Employee? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Employee): Employee? {
        if(findById(e.id) == null) {
            val emp = e.copy (
                id = UUID.randomUUID().toString(),
                createdAt = LocalDateTime.now()
            )
            repo.save(emp)
            return emp
        }

        return null
    }

    override fun update(e: Employee): Employee? {
        findById(e.id)?.let {
            val emp = e.copy (
                    modifiedAt = LocalDateTime.now()
            )
            repo.save(emp)
            return emp
        } ?: return null
    }

    override fun delete(id: String): Employee? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}