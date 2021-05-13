package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Spending(
        @ApiModelProperty(notes="The id of the schedule event in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The name of the spending.")
        var name: String,
        @ApiModelProperty(notes="The description of the spending.")
        var description: String,
        @ApiModelProperty(notes="The number for units.")
        var count: Int,
        @ApiModelProperty(notes="The price for unit.")
        var price: Float,
        @ApiModelProperty(notes="The target estimate.")
        @DBRef(lazy = true)
        var estimate: Estimate,
        @ApiModelProperty(notes="The date when resource was requested.")
        var requestDate: LocalDate,
        @ApiModelProperty(notes="The date when resource was got.")
        var satisfiedDate: LocalDate,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)