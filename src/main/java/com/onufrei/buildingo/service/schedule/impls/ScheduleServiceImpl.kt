package com.onufrei.buildingo.service.schedule.impls

import com.onufrei.buildingo.model.Schedule
import com.onufrei.buildingo.repository.ScheduleRepository
import com.onufrei.buildingo.service.schedule.interfaces.ScheduleService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ScheduleServiceImpl(private val repo: ScheduleRepository) : ScheduleService {

    override fun findAll(): List<Schedule> {
        return repo.findAll()
    }

    override fun findById(id: String): Schedule? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: Schedule): Schedule? {
        if(findById(e.id) == null) {
            val spec = e.copy(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now()
            )
            repo.save(spec)
            return spec
        }

        return null
    }

    override fun update(e: Schedule): Schedule? {
        findById(e.id)?.let {
            val spec = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(spec)
            return spec
        } ?: return null
    }

    override fun delete(id: String): Schedule? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}