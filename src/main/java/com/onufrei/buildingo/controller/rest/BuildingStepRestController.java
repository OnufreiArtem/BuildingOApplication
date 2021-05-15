package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.BuildingStep;
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService;
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
 * Controller for BuildingStep object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/building-steps")
@RestController
public class BuildingStepRestController {

	private final BuildingStepService service;

	private BuildingStepRestController(@Autowired BuildingStepService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<BuildingStep> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private BuildingStep add(@RequestBody BuildingStep spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private BuildingStep getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private BuildingStep update(@RequestBody BuildingStep spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private BuildingStep delete(@PathVariable String id) {
		return service.delete(id);
	}
}