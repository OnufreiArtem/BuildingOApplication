package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Document
data class Contract(
        @ApiModelProperty(notes="The id of the contract in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The customer of the project.")
        var customer: Customer,
        @ApiModelProperty(notes="The construction management that implements the order.")
        var constructionManagement: ConstructionManagement,
        @ApiModelProperty(notes="The price of the order.")
        var price: Int,
        @ApiModelProperty(notes="The plot, where the work will be done.")
        var plot: Plot,
        @ApiModelProperty(notes="The date when contract was signed.")
        var signedDate: LocalDate,
        @ApiModelProperty(notes="The date when the order must launch.")
        var projectStartDate: LocalDate,
        @ApiModelProperty(notes="The date when order must be finished.")
        var projectFinishedDate: LocalDate,
        @ApiModelProperty(notes="Shows if the order is implemented.")
        var isFinished: Boolean = false,
        @ApiModelProperty(notes="Shows if the order is failed.")
        var failed: Boolean = false,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)