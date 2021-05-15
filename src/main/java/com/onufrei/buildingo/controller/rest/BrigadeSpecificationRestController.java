package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Brigade;
import com.onufrei.buildingo.model.BrigadeSpecification;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService;
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
 * Controller for BrigadeSpecification object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/brigade-specs")
@RestController
public class BrigadeSpecificationRestController {

	private final BrigadeSpecificationService service;

	private BrigadeSpecificationRestController(@Autowired BrigadeSpecificationService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<BrigadeSpecification> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private BrigadeSpecification add(@RequestBody BrigadeSpecification spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private BrigadeSpecification getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private BrigadeSpecification update(@RequestBody BrigadeSpecification spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private BrigadeSpecification delete(@PathVariable String id) {
		return service.delete(id);
	}
}