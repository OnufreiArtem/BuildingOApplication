package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Building(
        @ApiModelProperty(notes="The id of the building in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The plot where building it is situated.")
        @DBRef
        var plot: Plot?,
        @ApiModelProperty(notes="The plan of the building.")
        var plan: String,
        @ApiModelProperty(notes="The chief of the building of the building.")
        @DBRef
        var chief: Employee?,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)