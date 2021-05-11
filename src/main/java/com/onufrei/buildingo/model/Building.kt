package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Building(
        val id: String = UUID.randomUUID().toString(),
        var plot: Plot,
        var plan: String,
        var chief: Employee,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)