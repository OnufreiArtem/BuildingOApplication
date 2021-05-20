package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Schedule(
        @ApiModelProperty(notes="The id of the schedule in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @DBRef
        @ApiModelProperty(notes="The target building.")
        var targetBuilding: Building,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)