package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class MachineryStorage(
        val id: String = UUID.randomUUID().toString(),
        var address: String,
        var name: String,
        var description: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)