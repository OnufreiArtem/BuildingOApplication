package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Plot(
        val id: String = UUID.randomUUID().toString(),
        var address: String,
        var contract: Contract,
        var chief: Employee,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)