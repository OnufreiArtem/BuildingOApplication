package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.BuildingStep
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

data class BuildingStepForm(
        @ApiModelProperty(notes = "The id of the building step in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the building step.")
        var name: String,
        @ApiModelProperty(notes = "The description of the building step.")
        var description: String,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toBuildingStepEntity() = BuildingStep(id, name, description, createdAt, modifiedAt)
}