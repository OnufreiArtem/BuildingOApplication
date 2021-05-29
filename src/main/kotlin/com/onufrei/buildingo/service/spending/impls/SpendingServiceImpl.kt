package com.onufrei.buildingo.service.spending.impls

import com.onufrei.buildingo.model.Spending
import com.onufrei.buildingo.repository.SpendingRepository
import com.onufrei.buildingo.service.spending.interfaces.SpendingService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SpendingServiceImpl(private val repo: SpendingRepository) : SpendingService {
    override fun findAll(): List<Spending> {
        return repo.findAll()
    }

    override fun findById(id: String): Spending? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Spending): Spending? {
        if(findById(e.id) == null) {
            val spending = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(spending)
            return spending
        }

        return null
    }

    override fun update(e: Spending): Spending? {
        findById(e.id)?.let {
            val spending = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(spending)
            return spending
        } ?: return null
    }

    override fun delete(id: String): Spending? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}