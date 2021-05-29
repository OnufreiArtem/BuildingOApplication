package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Machinery
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class MachineryForm(
        @ApiModelProperty(notes = "The id of the machinery in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The model of the machinery.")
        var model: String,
        @ApiModelProperty(notes = "The machinery number.")
        var number: String,
        @ApiModelProperty(notes = "The description of the machinery.")
        var description: String?,
        @ApiModelProperty(notes = "The information about ways to use this machinery.")
        var usage: String,
        @ApiModelProperty(notes = "The year when the machinery was crafted.")
        var year: Int,
        @ApiModelProperty(notes = "The storage where the machinery is situated.")
        var machineryStorage: String,
        @ApiModelProperty(notes = "Identifies if machinery is in usage now.")
        var inUsage: Boolean = false,
        @ApiModelProperty(notes = "Identifies if machinery can be used.")
        var active: Boolean = true,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toMachineryEntity(machineryStorageService: MachineryStorageService) = Machinery(
            id,
            model,
            number,
            description,
            usage,
            year,
            machineryStorageService.findById(machineryStorage),
            inUsage,
            active,
            createdAt,
            modifiedAt
    )
}