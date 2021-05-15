package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Spending;
import com.onufrei.buildingo.service.spending.interfaces.SpendingService;
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
 * Controller for Spending object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/spendings")
@RestController
public class SpendingRestController {

	private final SpendingService service;

	private SpendingRestController(@Autowired SpendingService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Spending> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Spending add(@RequestBody Spending spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Spending getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Spending update(@RequestBody Spending spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Spending delete(@PathVariable String id) {
		return service.delete(id);
	}
}