package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Customer
import com.onufrei.buildingo.model.CustomerType
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.util.*

class CustomerForm(
        @ApiModelProperty(notes = "The id of the customer in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The type of the customer.")
        var type: CustomerType?,
        @ApiModelProperty(notes = "Name of organization or person which order something")
        var name: String = "",
        @ApiModelProperty(notes = "The name of the person to communicate with.")
        var contactName: String = "",
        @ApiModelProperty(notes = "The surname of the person to communicate with.")
        var contactSurname: String = "",
        @ApiModelProperty(notes = "The phone number of the person to communicate with.")
        var contactPhoneNumber: String = "",
        @ApiModelProperty(notes = "The e-mail of the person to communicate with.")
        var contactEMail: String = "",
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toCustomerEntity() = Customer(
            id,
            type,
            name,
            contactName,
            contactSurname,
            contactPhoneNumber,
            contactEMail,
            createdAt,
            modifiedAt
    )
}