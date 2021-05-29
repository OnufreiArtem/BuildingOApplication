package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.BuildingStepForm;
import com.onufrei.buildingo.model.BuildingStep;
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService;
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
 * Controller for BuildingStep object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/building-steps")
@RestController
public class BuildingStepRestController {

	private final BuildingStepService service;

	private BuildingStepRestController(@Autowired BuildingStepService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all building steps")
	@GetMapping("/")
	private List<BuildingStep> getAllBuildingSteps() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new building step")
	@PostMapping("/")
	private BuildingStep addBuildingStep(
			@ApiParam(name = "Building Step", value = "The json of brigade specification you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody BuildingStepForm buildingStepForm) {
		return service.add(buildingStepForm.toBuildingStepEntity());
	}

	@ApiOperation(value = "Returns building step of specified id")
	@GetMapping("/{id}")
	private BuildingStep getBuildingStepById(
			@ApiParam(name = "Brigade Step id", value = "The id of building step you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Returns all steps names")
	@GetMapping("/names")
	private List<Pair<String, String>> getAllStepNames() {
		return service.allStepNames();
	}

	@ApiOperation(value = "Updates specified building step by building step you pass")
	@PutMapping("/")
	private BuildingStep updateBuildingStep(
			@ApiParam(name = "Brigade Step", value = "The json of building step you want to update. "
					+ "The id of building step you pass must correspond to building step's id you want to update")
			@RequestBody BuildingStepForm buildingStepForm) {
		return service.update(buildingStepForm.toBuildingStepEntity());
	}

	@ApiOperation(value = "Deletes the building step with id you specify")
	@DeleteMapping("/{id}")
	private BuildingStep deleteBuildingStep(
			@ApiParam(name = "Building step id", value = "The id of building step you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}