package com.onufrei.buildingo.service.request.impls

import com.onufrei.buildingo.model.Request
import com.onufrei.buildingo.repository.RequestRepository
import com.onufrei.buildingo.service.request.interfaces.RequestService
import java.time.LocalDateTime
import java.util.*

class RequestServiceImpl(private val repo: RequestRepository) : RequestService {

    override fun findAll(): List<Request> {
        return repo.findAll()
    }

    override fun findById(id: String): Request? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Request): Request? {
        if(findById(e.id) == null) {
            val request = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(request)
            return request
        }

        return null
    }

    override fun update(e: Request): Request? {
        findById(e.id)?.let {
            val request = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(request)
            return request
        } ?: return null
    }

    override fun delete(id: String): Request? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}