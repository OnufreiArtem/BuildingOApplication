package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.ConstructionManagementForm;
import com.onufrei.buildingo.model.ConstructionManagement;
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
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
 * Controller for ConstructorManagement object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/managements")
@RestController
public class ConstructionManagementRestController {

	private final ConstructionManagementService service;
	private final EmployeeService employeeService;

	private ConstructionManagementRestController(@Autowired ConstructionManagementService service, @Autowired EmployeeService employeeService) {
		this.service = service;
		this.employeeService = employeeService;
	}

	@ApiOperation(value = "Returns list of all construction managements")
	@GetMapping("/")
	private List<ConstructionManagement> getAllConstructionManagements() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new construction management")
	@PostMapping("/")
	private ConstructionManagement addConstructionManagement(
			@ApiParam(name = "Construction management", value = "The json of construction management you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody ConstructionManagementForm managementForm) {
		return service.add(managementForm.toManagementEntity(employeeService));
	}

	@ApiOperation(value = "Returns construction management of specified id")
	@GetMapping("/{id}")
	private ConstructionManagement getConstructionManagementById(
			@ApiParam(name = "Construction management id", value = "The id of construction management you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Returns all managements addresses")
	@GetMapping("/addresses")
	private List<Pair<String, String>> getAllAddresses() {
		return service.getAllAddresses();
	}

	@ApiOperation(value = "Updates specified construction management by construction management you pass")
	@PutMapping("/")
	private ConstructionManagement updateConstructionManagement(
			@ApiParam(name = "Construction management", value = "The json of construction management you want to update. "
					+ "The id of construction management you pass must correspond to construction management's id you want to update")
			@RequestBody ConstructionManagementForm managementForm) {
		return service.update(managementForm.toManagementEntity(employeeService));
	}

	@ApiOperation(value = "Deletes the construction management with id you specify")
	@DeleteMapping("/{id}")
	private ConstructionManagement deleteConstructionManagement(
			@ApiParam(name = "Construction management id", value = "The id of construction management you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}