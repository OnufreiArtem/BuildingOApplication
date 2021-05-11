package com.onufrei.buildingo.model

import java.time.LocalDateTime
import java.util.*

data class Customer(
        val id: String = UUID.randomUUID().toString(),
        var type: CustomerType,
        var contactName: String,
        var contactSurname: String,
        var contactPhoneNumber: String?,
        var contactEMail: String,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)