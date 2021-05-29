package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Building
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import com.onufrei.buildingo.service.plot.interfaces.PlotService
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class BuildingForm(
        @ApiModelProperty(notes = "The id of the building in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The plot where building it is situated.")
        var plot: String="",
        @ApiModelProperty(notes = "The plan of the building.")
        var plan: String,
        @ApiModelProperty(notes = "The chief of the building of the building.")
        var chief: String="",
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toBuildingEntity(plotService: PlotService, employeeService: EmployeeService): Building {
        return Building(
                id,
                plotService.findById(plot),
                plan,
                employeeService.findById(chief),
                createdAt,
                modifiedAt
        )
    }
}