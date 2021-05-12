package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Brigade(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var description: String = "",
        var chief: Employee,
        var specification: EmployeeSpecification,
        var isActive: Boolean,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)
