package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class EmployeeSpecification(
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var description: String = "",
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)