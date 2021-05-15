package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Building;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
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
 * Controller for Building object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/buildings")
@RestController
public class BuildingRestController {

	private final BuildingService service;

	private BuildingRestController(@Autowired BuildingService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Building> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Building add(@RequestBody Building spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Building getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Building update(@RequestBody Building spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Building delete(@PathVariable String id) {
		return service.delete(id);
	}
}