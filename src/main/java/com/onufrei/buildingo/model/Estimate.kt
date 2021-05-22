package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Estimate(
        @ApiModelProperty(notes="The id of the estimate in UUID format.")
        @Id
        var id: String = UUID.randomUUID().toString(),
        @DBRef
        @ApiModelProperty(notes="The plot that is linked to estimate.")
        var plot: Plot?,
        @ApiModelProperty(notes="The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)