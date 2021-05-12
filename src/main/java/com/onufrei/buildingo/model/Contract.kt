package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Contract(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var customer: Customer,
        var constructionManagement: ConstructionManagement,
        var price: Int,
        var signedDate: LocalDate,
        var projectStartDate: LocalDate,
        var projectFinishedDate: LocalDate,
        var isFinished: Boolean = false,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)