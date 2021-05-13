package com.onufrei.buildingo.model

import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Customer(
        @ApiModelProperty(notes="The id of the customer in UUID format.")
        @Id
        val id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes="The type of the customer.")
        var type: CustomerType,
        @ApiModelProperty(notes="The name of the person to communicate with.")
        var contactName: String,
        @ApiModelProperty(notes="The surname of the person to communicate with.")
        var contactSurname: String,
        @ApiModelProperty(notes="The phone number of the person to communicate with.")
        var contactPhoneNumber: String?,
        @ApiModelProperty(notes="The e-mail of the person to communicate with.")
        var contactEMail: String,
        @ApiModelProperty(notes="The date and time when object was created.")
        val createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes="The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
)