package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.ConstructionManagement;
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService;
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
 * Controller for ConstructorManagement object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/managements")
@RestController
public class ConstructionManagementRestController {

	private final ConstructionManagementService service;

	private ConstructionManagementRestController(@Autowired ConstructionManagementService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<ConstructionManagement> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private ConstructionManagement add(@RequestBody ConstructionManagement spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private ConstructionManagement getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private ConstructionManagement update(@RequestBody ConstructionManagement spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private ConstructionManagement delete(@PathVariable String id) {
		return service.delete(id);
	}
}