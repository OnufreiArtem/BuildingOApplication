package com.onufrei.buildingo.service.building.impls

import com.onufrei.buildingo.model.Building
import com.onufrei.buildingo.repository.BuildingRepository
import com.onufrei.buildingo.service.building.interfaces.BuildingService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class BuildingServiceImpl(private val repo: BuildingRepository) : BuildingService {
    override fun findAll(): List<Building> {
        return repo.findAll()
    }

    override fun findById(id: String): Building? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Building): Building? {
        if(findById(e.id) == null) {
            val building = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(building)
            return building
        }

        return null
    }

    override fun update(e: Building): Building? {
        findById(e.id)?.let {
            val building = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(building)
            return building
        } ?: return null
    }

    override fun delete(id: String): Building? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}