package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.MachineryStorage
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class MachineryStorageForm(
        @ApiModelProperty(notes = "The id of the machinery storage in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The address of the storage.")
        var address: String,
        @ApiModelProperty(notes = "The name of the storage.")
        var name: String,
        @ApiModelProperty(notes = "The description of the storage.")
        var description: String?,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toMachineryStorageEntity() = MachineryStorage(
            id,
            address,
            name,
            description,
            createdAt,
            modifiedAt
    )
}