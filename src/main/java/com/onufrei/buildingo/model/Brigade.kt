package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Brigade(
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var description: String = "",
        var chief: Employee,
        var specification: EmployeeSpecification,
        var isActive: Boolean,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)
