package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Plot
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class PlotForm(
        @ApiModelProperty(notes = "The id of the plot in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The address of the plot.")
        var address: String = "",
        @ApiModelProperty(notes = "The chief of the plot.")
        var chief: String,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toPlotEntity(employeeService: EmployeeService) = Plot(
            id,
            address,
            employeeService.findById(chief),
            createdAt,
            modifiedAt
    )
}