package com.onufrei.buildingo.service.scheduleEvent.impls

import com.onufrei.buildingo.model.ScheduleEvent
import com.onufrei.buildingo.repository.ScheduleEventRepository
import com.onufrei.buildingo.service.scheduleEvent.interfaces.ScheduleEventService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class ScheduleEventServiceImpl(private val repo: ScheduleEventRepository) : ScheduleEventService {
    override fun findAll(): List<ScheduleEvent> {
        return repo.findAll()
    }

    override fun findById(id: String): ScheduleEvent? {
        return repo.findById(id).orElse(null)
    }

    override fun add(e: ScheduleEvent): ScheduleEvent? {
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

    override fun update(e: ScheduleEvent): ScheduleEvent? {
        findById(e.id)?.let {
            val spec = e.apply {
                modifiedAt = LocalDateTime.now()
            }
            repo.save(spec)
            return spec
        } ?: return null
    }

    override fun delete(id: String): ScheduleEvent? {
        findById(id)?.let {
            repo.delete(it)
            return it
        } ?: return null
    }
}