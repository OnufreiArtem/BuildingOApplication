package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Spending(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var description: String,
        var count: Int,
        var price: Float,
        var estimate: Estimate,
        var requestDate: LocalDate,
        var satisfiedDate: LocalDate,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)