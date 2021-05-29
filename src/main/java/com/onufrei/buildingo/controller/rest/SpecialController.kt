package com.onufrei.buildingo.controller.rest

import com.onufrei.buildingo.model.ConstructionManagement
import com.onufrei.buildingo.model.Employee
import com.onufrei.buildingo.model.EmployeeType
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService
import com.onufrei.buildingo.service.contract.interfaces.ContractService
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService
import com.onufrei.buildingo.service.scheduleEvent.interfaces.ScheduleEventService
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.streams.toList


/** Represents object of SpecialController
 * @author Artem Onufrei
 * @version 1
 * @since 27.05.2021
 */

@RequestMapping("/api/v1/specials")
@RestController
class SpecialController(
        private val employeeService: EmployeeService,
        private val scheduleEventService: ScheduleEventService,
        private val managementService: ConstructionManagementService,
        private val contractService: ContractService) {


    data class Numbers(
            @ApiModelProperty(notes = "Number of employees.")
            val employeeNumber: Int,
            @ApiModelProperty(notes = "Number of construction managements")
            val managementNumber: Int,
            @ApiModelProperty(notes = "Number of contracts that are still not finished.")
            val contractsInProgress: Int,
    )

    @ApiOperation(value = "Returns customer of specified id")
    @GetMapping("/main-numbers")
    private fun getNumberOfEmployees() = Numbers(employeeService.countEmployees(), managementService.countManagements(), contractService.countPlotsInProgress())

    @ApiOperation(value = "Returns list of engineer employees from specified plot")
    @GetMapping("/engineers-from-plot/{id}")
    private fun getEngineersFromPlot(@ApiParam(name = "Plot id", value = "The id of the plot") @PathVariable id: String): List<Employee> {
        val brigades = scheduleEventService.findAll().filter { it.building?.plot?.id == id }.mapNotNull { it.brigade }
        return employeeService.findAll().filter { brigades.contains(it.brigade) }.filter { it.specification?.type == EmployeeType.ENGINEER }
    }

    data class ManagementEarnings(
            @ApiModelProperty(notes = "Construction management")
            val management: ConstructionManagement?,
            @ApiModelProperty(notes = "Earnings")
            val earning: Int
    )

    @ApiOperation(value = "Returns earnings from every management")
    @GetMapping("/managements-earnings")
    private fun getManagementsEarnings(): List<ManagementEarnings> {

        return contractService.findAll().filter { it.finished && !it.failed && it.constructionManagement != null }.stream()
                .collect(Collectors.groupingBy({ it.constructionManagement }, Collectors.summingInt { it.price })).entries.stream().filter { it.key != null }
                .map { ManagementEarnings(it.key, it.value) }.toList()

    }

}