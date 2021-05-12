package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class ScheduleEvent(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var description: String,
        var participants: List<Brigade>,
        var beginning: LocalDateTime,
        var ending: LocalDateTime,
        var schedule: Schedule,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)
