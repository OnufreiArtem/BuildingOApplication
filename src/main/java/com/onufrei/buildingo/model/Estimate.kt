package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Estimate(
        val id: String = UUID.randomUUID().toString(),
        var plot: Plot,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)