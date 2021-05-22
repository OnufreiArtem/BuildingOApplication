package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class ConstructionManagement(
        @ApiModelProperty(notes="The id of the construction management in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The chief of the construction management.")
        @DBRef
        var chief: Employee?,
        @ApiModelProperty(notes="The name of the construction management.")
        var name: String,
        @ApiModelProperty(notes="The description of the construction management.")
        var description: String = "",
        @ApiModelProperty(notes="The address of the construction management.")
        var address: String,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()

)