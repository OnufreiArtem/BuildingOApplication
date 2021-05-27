package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Spending(
        @ApiModelProperty(notes="The id of the schedule event in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The name of the spending.")
        var name: String,
        @ApiModelProperty(notes="The description of the spending.")
        var description: String?,
        @ApiModelProperty(notes="The number for units.")
        var count: Int,
        @ApiModelProperty(notes="The price for unit.")
        var price: Float,
        @ApiModelProperty(notes="The target building.")
        @DBRef
        var building: Building?,
        @ApiModelProperty(notes="The date when resource was requested.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var requestDate: LocalDate?,
        @ApiModelProperty(notes="The date when resource was got.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var satisfiedDate: LocalDate?,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)