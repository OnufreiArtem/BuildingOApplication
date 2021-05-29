package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Brigade
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

class BrigadeForm(
        @ApiModelProperty(notes = "The id of the brigade in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the brigade.")
        var name: String,
        @ApiModelProperty(notes = "The description of the brigade.")
        var description: String?,
        @ApiModelProperty(notes = "The chief of the brigade.")
        var chief: String,
        @ApiModelProperty(notes = "The specification of brigade.")
        var specification: String,
        @ApiModelProperty(notes = "The state of the brigade. If isActive is true - the brigade is able do their work, otherwise - brigade is not able.")
        var active: Boolean = false,
        @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toBrigadeEntity(employeeService: EmployeeService, brigadeSpecificationService: BrigadeSpecificationService): Brigade {
        return Brigade(
                id,
                name,
                description,
                employeeService.findById(this.chief),
                brigadeSpecificationService.findById(this.specification),
                active,
                createdAt,
                modifiedAt
        )
    }
}
