package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Brigade(
        @ApiModelProperty(notes = "The id of the brigade in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The name of the brigade.")
        var name: String?,
        @ApiModelProperty(notes = "The description of the brigade.")
        var description: String? = "",
        @ApiModelProperty(notes = "The chief of the brigade.")
        var chief: Employee?,
        @ApiModelProperty(notes = "The specification of brigade.")
        @DBRef
        var specification: BrigadeSpecification?,
        @ApiModelProperty(notes = "The state of the brigade. If isActive is true - the brigade is able do their work, otherwise - brigade is not able.")
        var isActive: Boolean?,
        @ApiModelProperty(notes = "The date and time when object was created.")
        val createdAt: LocalDateTime? = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime? = LocalDateTime.now()
) {
    constructor() : this(UUID.randomUUID().toString(), "", null, null, null, false, LocalDateTime.now(), LocalDateTime.now());
}
