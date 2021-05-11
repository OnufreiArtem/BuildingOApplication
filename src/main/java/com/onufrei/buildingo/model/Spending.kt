package com.onufrei.buildingo.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Spending(
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