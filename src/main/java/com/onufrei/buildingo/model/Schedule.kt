package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Schedule(
        val id: String = UUID.randomUUID().toString(),
        var targetBuilding: Building,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)