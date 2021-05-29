package com.onufrei.buildingo.service.constructionManagement.impls

import com.onufrei.buildingo.model.ConstructionManagement
import com.onufrei.buildingo.repository.ConstructionManagementRepository
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ConstructionManagementServiceImpl(private val repo: ConstructionManagementRepository)
    : ConstructionManagementService {
    override fun getAllAddresses(): List<Pair<String, String>> {
        return findAll().map { Pair(it.id, it.address) }
    }

    override fun countManagements(): Int = findAll().size

    override fun findAll(): List<ConstructionManagement> {
        return repo.findAll()
    }

    override fun findById(id: String): ConstructionManagement? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: ConstructionManagement): ConstructionManagement? {
        if(findById(e.id) == null) {
            val management = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(management)
            return management
        }

        return null
    }

    override fun update(e: ConstructionManagement): ConstructionManagement? {
        findById(e.id)?.let {
            val management = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(management)
            return management
        } ?: return null
    }

    override fun delete(id: String): ConstructionManagement? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }

}