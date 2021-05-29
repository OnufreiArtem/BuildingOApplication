package com.onufrei.buildingo.service.machinery.impls

import com.onufrei.buildingo.model.Machinery
import com.onufrei.buildingo.repository.MachineryRepository
import com.onufrei.buildingo.service.machinery.interfaces.MachineryService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class MachineryServiceImpl(private val repo: MachineryRepository) : MachineryService {

    override fun findAll(): List<Machinery> {
        return repo.findAll()
    }

    override fun findById(id: String): Machinery? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Machinery): Machinery? {
        if(findById(e.id) == null) {
            val machinery = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(machinery)
            return machinery
        }

        return null
    }

    override fun update(e: Machinery): Machinery? {
        findById(e.id)?.let {
            val machinery = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(machinery)
            return machinery
        } ?: return null
    }

    override fun delete(id: String): Machinery? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}