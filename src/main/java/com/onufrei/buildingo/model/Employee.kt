package com.onufrei.buildingo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Employee(
        @Id
        val id: String = UUID.randomUUID().toString(),
        var name: String,
        var surname: String,
        var dateOfBirth: LocalDate,
        var salary: Int,
        var phoneNumber: String?,
        var email: String,
        var hiredDate: LocalDate,
        var firedDate: LocalDate?,
        var isActive: Boolean,
        var specification: EmployeeSpecification,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)