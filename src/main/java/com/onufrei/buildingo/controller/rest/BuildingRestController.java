package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.BuildingForm;
import com.onufrei.buildingo.model.Building;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * Controller for Building object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/buildings")
@RestController
public class BuildingRestController {

	private final BuildingService service;
	private final PlotService plotService;
	private final EmployeeService employeeService;

	private BuildingRestController(@Autowired BuildingService service, @Autowired PlotService plotService, @Autowired EmployeeService employeeService) {
		this.service = service;
		this.plotService = plotService;
		this.employeeService = employeeService;
	}


	@ApiOperation(value = "Returns list of all buildings")
	@GetMapping("/")
	private List<Building> getAllBuildings() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new building")
	@PostMapping("/")
	private Building addBuilding(
			@ApiParam(name = "Building", value = "The json of building you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody BuildingForm buildingForm) {
		return service.add(buildingForm.toBuildingEntity(plotService, employeeService));
	}

	@ApiOperation(value = "Returns building of specified id")
	@GetMapping("/{id}")
	private Building getBuildingById(
			@ApiParam(name = "Building id", value = "The id of building you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified building by building you pass")
	@PutMapping("/")
	private Building updateBuilding(
			@ApiParam(name = "Building", value = "The json of building you want to update. "
					+ "The id of building you pass must correspond to building's id you want to update")
			@RequestBody BuildingForm buildingForm) {
		return service.update(buildingForm.toBuildingEntity(plotService, employeeService));
	}

	@ApiOperation(value = "Deletes the building with id you specify")
	@DeleteMapping("/{id}")
	private Building deleteBuilding(
			@ApiParam(name = "Building id", value = "The id of building you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}