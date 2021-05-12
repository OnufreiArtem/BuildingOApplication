package com.onufrei.buildingo.service.estimate.impls

import com.onufrei.buildingo.model.Estimate
import com.onufrei.buildingo.repository.EstimateRepository
import com.onufrei.buildingo.service.estimate.interfaces.EstimateService
import java.time.LocalDateTime
import java.util.*

class EstimateServiceImpl(private val repo: EstimateRepository) : EstimateService {

    override fun findAll(): List<Estimate> {
        return repo.findAll()
    }

    override fun findById(id: String): Estimate? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Estimate): Estimate? {
        if(findById(e.id) == null) {
            val estimate = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(estimate)
            return estimate
        }

        return null
    }

    override fun update(e: Estimate): Estimate? {
        findById(e.id)?.let {
            val estimate = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(estimate)
            return estimate
        } ?: return null
    }

    override fun delete(id: String): Estimate? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}