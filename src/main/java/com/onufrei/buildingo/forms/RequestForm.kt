package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Request
import com.onufrei.buildingo.model.RequestType
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService
import com.onufrei.buildingo.service.building.interfaces.BuildingService
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class RequestForm(
        @ApiModelProperty(notes = "The id of the request in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The title of the request.")
        var title: String,
        @ApiModelProperty(notes = "The description of the request.")
        var description: String?,
        @ApiModelProperty(notes = "The type of the request.")
        var type: RequestType?,
        @ApiModelProperty(notes = "Indicates if request was implemented.")
        var satisfied: Boolean,
        @ApiModelProperty(notes = "The building for which the request was called.")
        var building: String,
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        @ApiModelProperty(notes = "The date, when request was raised.")
        var date: LocalDate?,
        @ApiModelProperty(notes = "The brigade that called the request.")
        var brigadeThatAsked: String,
        @ApiModelProperty(notes = "The construction management that gets the request.")
        var constructionManagement: String,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toRequestEntity(buildingService: BuildingService, brigadeService: BrigadeService, managementService: ConstructionManagementService) = Request(
            id,
            title,
            description,
            type,
            satisfied,
            buildingService.findById(building),
            date,
            brigadeService.findById(brigadeThatAsked),
            managementService.findById(constructionManagement),
            createdAt,
            modifiedAt
    )
}