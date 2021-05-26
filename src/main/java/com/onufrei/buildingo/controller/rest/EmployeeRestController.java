package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.EmployeeForm;
import com.onufrei.buildingo.model.Employee;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kotlin.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * Controller for Employee object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/employees")
@RestController
public class EmployeeRestController {

	private final EmployeeService service;
	private final EmployeeSpecificationService specificationService;

	private EmployeeRestController(@Autowired EmployeeService service, @Autowired EmployeeSpecificationService specificationService) {
		this.service = service;
		this.specificationService = specificationService;
	}

	@ApiOperation(value = "Returns list of all employees")
	@GetMapping("/")
	private List<Employee> getAllEmployees() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new employee")
	@PostMapping("/")
	private Employee addEmployee(
			@ApiParam(name = "Employee", value = "The json of employee you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody EmployeeForm employeeForm) {
		return service.add(employeeForm.toEmployeeEntity(specificationService));
	}

	@ApiOperation(value = "Returns employee of specified id")
	@GetMapping("/{id}")
	private Employee getEmployeeById(
			@ApiParam(name = "Employee id", value = "The id of employee you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Returns pair if employee id and name")
	@GetMapping("/names")
	private List<Pair<String, String>> getEmployeeNames() {
		return service.getIdNamePairs();
	}

	@ApiOperation(value = "Updates specified employee by customer you pass")
	@PutMapping("/")
	private Employee updateEmployee(
			@ApiParam(name = "Employee", value = "The json of employee you want to update. "
					+ "The id of employee you pass must correspond to employee's id you want to update")
			@RequestBody EmployeeForm employeeForm) {
		return service.update(employeeForm.toEmployeeEntity(specificationService));
	}

	@ApiOperation(value = "Deletes the employee with id you specify")
	@DeleteMapping("/{id}")
	private Employee deleteEmployee(
			@ApiParam(name = "Employee id", value = "The id of employee you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}