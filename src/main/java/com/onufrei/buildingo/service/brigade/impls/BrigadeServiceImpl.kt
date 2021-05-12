package com.onufrei.buildingo.service.brigade.impls

import com.onufrei.buildingo.model.Brigade
import com.onufrei.buildingo.repository.BrigadeRepository
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BrigadeServiceImpl(private val repo: BrigadeRepository) : BrigadeService{
    override fun findAll(): List<Brigade> {
        return repo.findAll()
    }

    override fun findById(id: String): Brigade? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Brigade): Brigade? {
        if(findById(e.id) == null) {
            val brg = e.copy (
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(brg)
            return brg
        }

        return null
    }

    override fun update(e: Brigade): Brigade? {
        findById(e.id)?.let {
            val brg = e.copy (
                    modifiedAt = LocalDateTime.now()
            )
            repo.save(brg)
            return brg
        } ?: return null
    }

    override fun delete(id: String): Brigade? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}