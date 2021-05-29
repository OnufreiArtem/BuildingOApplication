package com.onufrei.buildingo.forms

import com.onufrei.buildingo.model.Contract
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService
import com.onufrei.buildingo.service.customer.interfaces.CustomerService
import com.onufrei.buildingo.service.plot.interfaces.PlotService
import io.swagger.annotations.ApiModelProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class ContractForm(
        @ApiModelProperty(notes = "The id of the contract in UUID format.")
        var id: String = UUID.randomUUID().toString(),
        @ApiModelProperty(notes = "The customer of the project.")
        var customer: String,
        @ApiModelProperty(notes = "The construction management that implements the order.")
        var constructionManagement: String,
        @ApiModelProperty(notes = "The price of the order.")
        var price: Int,
        @ApiModelProperty(notes = "The plot, where the work will be done.")
        var plot: String,
        @ApiModelProperty(notes = "The date when contract was signed.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var signedDate: LocalDate? = null,
        @ApiModelProperty(notes = "The date when the order must launch.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var projectStartDate: LocalDate? = null,
        @ApiModelProperty(notes = "The date when order must be finished.")
        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        var projectFinishedDate: LocalDate? = null,
        @ApiModelProperty(notes = "Shows if the order is implemented.")
        var finished: Boolean = false,
        @ApiModelProperty(notes = "Shows if the order is failed.")
        var failed: Boolean = false,
        @ApiModelProperty(notes = "The date and time when object was created.")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @ApiModelProperty(notes = "The date and time when object was lastly modified.")
        var modifiedAt: LocalDateTime = LocalDateTime.now()
) {
    fun toContractEntity(customerService: CustomerService, constructionManagementService: ConstructionManagementService, plotService: PlotService) : Contract {
        return Contract(
                id,
                customerService.findById(customer),
                constructionManagementService.findById(constructionManagement),
                price,
                plotService.findById(plot),
                signedDate,
                projectStartDate,
                projectFinishedDate,
                finished,
                failed,
                createdAt,
                modifiedAt
        )
    }
}