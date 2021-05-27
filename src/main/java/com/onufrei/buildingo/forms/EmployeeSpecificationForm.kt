package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.EmployeeSpecification
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class EmployeeSpecificationForm(
        @ApiModelProperty(notes = "The id of the employee specification in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the employee specification.")
        var name: String,
        @ApiModelProperty(notes = "The description of the employee specification.")
        var description: String?,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toEmployeeSpecificationEntity() = EmployeeSpecification(
            id,
            name,
            description,
            createdAt,
            modifiedAt
    )
}