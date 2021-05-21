package com.onufrei.buildingo.service.machineryStorage.impls

import com.onufrei.buildingo.model.MachineryStorage
import com.onufrei.buildingo.repository.MachineryStorageRepository
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.streams.toList

@Service
class MachineryStorageServiceImpl(private val repo: MachineryStorageRepository) : MachineryStorageService {
    override fun getMainInfo(): List<Pair<String, String>> {
        return findAll().stream().map { Pair(it.id, it.address) }.toList()
    }

    override fun findAll(): List<MachineryStorage> {
        return repo.findAll()
    }

    override fun findById(id: String): MachineryStorage? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: MachineryStorage): MachineryStorage? {
        if(findById(e.id) == null) {
            val storage = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(storage);
            return storage
        }

        return null
    }

    override fun update(e: MachineryStorage): MachineryStorage? {
        findById(e.id)?.let {
            val storage = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(storage)
            return storage
        } ?: return null
    }

    override fun delete(id: String): MachineryStorage? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}