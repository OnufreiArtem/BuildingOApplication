package com.onufrei.buildingo.service.brigadeSpecification.impls

import com.onufrei.buildingo.model.BrigadeSpecification
import com.onufrei.buildingo.repository.BrigadeSpecificationRepository
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BrigadeSpecificationServiceImpl(private val repo: BrigadeSpecificationRepository) : BrigadeSpecificationService {

    override fun findAll(): List<BrigadeSpecification> {
        return repo.findAll()
    }

    override fun findById(id: String): BrigadeSpecification? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: BrigadeSpecification): BrigadeSpecification? {
        if(findById(e.id) == null) {
            val spec = e.copy (
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(spec)
            return spec
        }

        return null
    }

    override fun update(e: BrigadeSpecification): BrigadeSpecification? {
        findById(e.id)?.let {
            val spec = e.copy (
                    modifiedAt = LocalDateTime.now()
            )
            repo.save(spec)
            return spec
        } ?: return null
    }

    override fun delete(id: String): BrigadeSpecification? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}