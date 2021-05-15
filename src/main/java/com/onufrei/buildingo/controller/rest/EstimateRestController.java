package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Estimate;
import com.onufrei.buildingo.service.estimate.interfaces.EstimateService;
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
 * Controller for Estimate object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/estimates")
@RestController
public class EstimateRestController {

	private final EstimateService service;

	private EstimateRestController(@Autowired EstimateService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Estimate> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Estimate add(@RequestBody Estimate spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Estimate getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Estimate update(@RequestBody Estimate spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Estimate delete(@PathVariable String id) {
		return service.delete(id);
	}
}