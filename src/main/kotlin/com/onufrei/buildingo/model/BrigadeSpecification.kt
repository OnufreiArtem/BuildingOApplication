package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class BrigadeSpecification(
        @ApiModelProperty(notes="The id of the brigade specification in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The name of brigade specification.")
        var name: String,
        @ApiModelProperty(notes="The description of brigade specification.")
        var description: String?,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)
