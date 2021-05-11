package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class ScheduleEvent(
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
