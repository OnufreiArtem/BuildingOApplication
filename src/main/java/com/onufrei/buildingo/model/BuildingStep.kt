package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class BuildingStep(
        @ApiModelProperty(notes="The id of the building step in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The name of the building step.")
        var name: String,
        @ApiModelProperty(notes="The description of the building step.")
        var description: String,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)