package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Building(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var plot: Plot,
        var plan: String,
        var chief: Employee,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)