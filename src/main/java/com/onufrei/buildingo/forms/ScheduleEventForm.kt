package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.ScheduleEvent
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService
import com.onufrei.buildingo.service.building.interfaces.BuildingService
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

class ScheduleEventForm(
        @ApiModelProperty(notes = "The id of the schedule event in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the event.")
        var name: String,
        @ApiModelProperty(notes = "The description of the event.")
        var description: String?,
        @ApiModelProperty(notes = "The target building.")
        var building: String,
        @ApiModelProperty(notes = "The building pace of the event.")
        var buildingStep: String,
        @ApiModelProperty(notes = "The brigades that participate in the event.")
        var brigade: String,
        @ApiModelProperty(notes = "The date and time when work starts.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        var beginning: LocalDateTime?,
        @ApiModelProperty(notes = "The date and time when work ends.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        var ending: LocalDateTime?,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toScheduleEventEntity(buildingService: BuildingService, buildingStepService: BuildingStepService, brigadeService: BrigadeService) = ScheduleEvent(
            id,
            name,
            description,
            buildingService.findById(building),
            buildingStepService.findById(buildingStep),
            brigadeService.findById(brigade),
            beginning,
            ending,
            createdAt,
            modifiedAt
    )
}

