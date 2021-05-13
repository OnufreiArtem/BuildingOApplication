package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Machinery(
        @ApiModelProperty(notes="The id of the machinery in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The model of the machinery.")
        var model: String,
        @ApiModelProperty(notes="The machinery number.")
        var number: String,
        @ApiModelProperty(notes="The description of the machinery.")
        var description: String,
        @ApiModelProperty(notes="The information about ways to use this machinery.")
        var usage: String,
        @ApiModelProperty(notes="The year when the machinery was crafted.")
        var year: Int,
        @ApiModelProperty(notes="The storage where the machinery is situated.")
        var machineryStorage: MachineryStorage,
        @ApiModelProperty(notes="Identifies if machinery is in usage now.")
        var inUsage: Boolean = false,
        @ApiModelProperty(notes="Identifies if machinery can be used.")
        var isActive: Boolean = true,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)