package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Request(
        val id: String = UUID.randomUUID().toString(),
        var title: String,
        var description: String,
        var type: RequestType,
        var isSatisfied: Boolean,
        var building: Building,
        var brigadeThatAsked: Brigade,
        var constructionManagement: ConstructionManagement,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)