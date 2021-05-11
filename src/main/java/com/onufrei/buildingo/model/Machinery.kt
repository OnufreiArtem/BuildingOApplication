package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Machinery(
        val id: String = UUID.randomUUID().toString(),
        var model: String,
        var description: String,
        var usage: String,
        var year: Int,
        var machineryStorage: MachineryStorage,
        var inUsage: Boolean = false,
        var isActive: Boolean = true,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)