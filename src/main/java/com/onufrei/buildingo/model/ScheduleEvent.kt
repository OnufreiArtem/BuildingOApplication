package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class ScheduleEvent(
        @ApiModelProperty(notes="The id of the schedule event in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The name of the event.")
        var name: String,
        @ApiModelProperty(notes="The description of the event.")
        var description: String,
        @ApiModelProperty(notes="The building pace of the event.")
        var buildingStep: BuildingStep,
        @ApiModelProperty(notes="The brigades that participate in the event.")
        var participants: List<Brigade>,
        @ApiModelProperty(notes="The date and time when work starts.")
        var beginning: LocalDateTime,
        @ApiModelProperty(notes="The date and time when work ends.")
        var ending: LocalDateTime,
        @ApiModelProperty(notes="The target schedule.")
        var schedule: Schedule,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)
