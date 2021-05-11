package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class ConstructionManagement(
        val id: String = UUID.randomUUID().toString(),
        var chief: Employee,
        var name: String,
        var description: String = "",
        var address: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()

)