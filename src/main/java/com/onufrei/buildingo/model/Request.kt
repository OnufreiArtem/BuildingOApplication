package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Request(
        @Id
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