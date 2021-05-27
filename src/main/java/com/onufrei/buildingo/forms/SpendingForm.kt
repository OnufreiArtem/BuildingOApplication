package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Spending
import com.onufrei.buildingo.service.building.interfaces.BuildingService
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class SpendingForm(
        @ApiModelProperty(notes = "The id of the schedule event in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the spending.")
        var name: String,
        @ApiModelProperty(notes = "The description of the spending.")
        var description: String?,
        @ApiModelProperty(notes = "The number for units.")
        var count: Int,
        @ApiModelProperty(notes = "The price for unit.")
        var price: Float,
        @ApiModelProperty(notes = "The target building.")
        var building: String,
        @ApiModelProperty(notes = "The date when resource was requested.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var requestDate: LocalDate?,
        @ApiModelProperty(notes = "The date when resource was got.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var satisfiedDate: LocalDate?,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toSpendingEntity(buildingService: BuildingService) = Spending(
            id,
            name,
            description,
            count,
            price,
            buildingService.findById(building),
            requestDate,
            satisfiedDate,
            createdAt,
            modifiedAt
    )
}