package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Customer(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var type: CustomerType,
        var contactName: String,
        var contactSurname: String,
        var contactPhoneNumber: String?,
        var contactEMail: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)