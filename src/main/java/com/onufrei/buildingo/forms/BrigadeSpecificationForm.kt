package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.BrigadeSpecification
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class BrigadeSpecificationForm(
        @ApiModelProperty(notes = "The id of the brigade specification in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of brigade specification.")
        var name: String,
        @ApiModelProperty(notes = "The description of brigade specification.")
        var description: String = "",
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toBrigadeSpecificationEntity(): BrigadeSpecification {
        return BrigadeSpecification(
                id,
                name,
                description,
                createdAt,
                modifiedAt
        )

    }
}
