package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.ConstructionManagement
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

data class ConstructionManagementForm(
        @ApiModelProperty(notes = "The id of the construction management in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The chief of the construction management.")
        var chief: String,
        @ApiModelProperty(notes = "The name of the construction management.")
        var name: String,
        @ApiModelProperty(notes = "The description of the construction management.")
        var description: String = "",
        @ApiModelProperty(notes = "The address of the construction management.")
        var address: String,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()

) {
    fun toManagementEntity(employeeService: EmployeeService) = ConstructionManagement(
            id,
            employeeService.findById(chief),
            name,
            description,
            address,
            createdAt,
            modifiedAt
    )
}