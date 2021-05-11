package com.onufrei.buildingo.service.employeeSpecification.impls

import com.onufrei.buildingo.model.EmployeeSpecification
import com.onufrei.buildingo.repository.EmployeeSpecificationRepository
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService
import java.time.LocalDateTime
import java.util.*

class EmployeeSpecificationServiceImpls(private val repo: EmployeeSpecificationRepository) : EmployeeSpecificationService {

    override fun findAll() : List<EmployeeSpecification> {
        return repo.findAll()
    }

    override fun findById(id: String): EmployeeSpecification? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: EmployeeSpecification): EmployeeSpecification? {
        if(findById(e.id) == null) {
            val spec = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(spec);
            return spec
        }

        return null
    }

    override fun update(e: EmployeeSpecification): EmployeeSpecification? {
        findById(e.id)?.let {
            val spec = e.copy(
                    modifiedAt = LocalDateTime.now()
            )
            repo.save(spec);
            return spec;
        } ?: return null
    }

    override fun delete(id: String): EmployeeSpecification? {
        findById(id)?.let {
            repo.delete(it)
            return it;
        } ?: return null;

    }
}