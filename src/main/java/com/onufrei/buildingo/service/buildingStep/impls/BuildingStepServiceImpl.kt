package com.onufrei.buildingo.service.buildingStep.impls

import com.onufrei.buildingo.model.BuildingStep
import com.onufrei.buildingo.repository.BuildingStepRepository
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService
import java.time.LocalDateTime
import java.util.*

class BuildingStepServiceImpl(private val repo: BuildingStepRepository) : BuildingStepService {
    override fun findAll(): List<BuildingStep> {
        return repo.findAll()
    }

    override fun findById(id: String): BuildingStep? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: BuildingStep): BuildingStep? {
        if(findById(e.id) == null) {
            val buildingStep = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(buildingStep)
            return buildingStep
        }

        return null
    }

    override fun update(e: BuildingStep): BuildingStep? {
        findById(e.id)?.let {
            val buildingStep = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(buildingStep)
            return buildingStep
        } ?: return null
    }

    override fun delete(id: String): BuildingStep? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}