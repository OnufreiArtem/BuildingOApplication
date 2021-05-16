package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.EmployeeSpecification;
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Represents object of EmployeeSpecificationRestController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 11.05.2021
 */

@RequestMapping("api/v1/employee-specs")
@RestController
public class EmployeeSpecificationRestController {

    EmployeeSpecificationService service;

    private EmployeeSpecificationRestController(@Autowired EmployeeSpecificationService service) {
        this.service = service;
    }

    @ApiOperation(value = "Returns list of all employee specifications")
    @GetMapping("/")
    private List<EmployeeSpecification> getAllEmployeeSpecification() {
        return service.findAll();
    }

    @ApiOperation(value = "Adds new employee specification")
    @PostMapping("/")
    private EmployeeSpecification addEmployeeSpecification(
            @ApiParam(name = "Employee specification", value = "The json of employee specification you want to add. "
                    + "Id, createdAt and modifiedAt dates generate automatically")
            @RequestBody EmployeeSpecification employeeSpecification) {
        return service.add(employeeSpecification);
    }

    @ApiOperation(value = "Returns employee specification of specified id")
    @GetMapping("/{id}")
    private EmployeeSpecification getEmployeeSpecificationById(
            @ApiParam(name = "Employee specification id", value = "The id of employee specification you want to get")
            @PathVariable String id) {
        return service.findById(id);
    }

    @ApiOperation(value = "Updates specified employee specification by customer you pass")
    @PutMapping("/")
    private EmployeeSpecification updateEmployeeSpecification(
            @ApiParam(name = "Employee specification", value = "The json of employee specification you want to update. "
                    + "The id of employee specification you pass must correspond to employee specification's id you want to update")
            @RequestBody EmployeeSpecification employeeSpecification) {
        return service.update(employeeSpecification);
    }

    @ApiOperation(value = "Deletes the employee specification with id you specify")
    @DeleteMapping("/{id}")
    private EmployeeSpecification deleteEmployeeSpecification(
            @ApiParam(name = "Employee specification id", value = "The id of employee specification you want to delete")
            @PathVariable String id) {
        return service.delete(id);
    }

}
