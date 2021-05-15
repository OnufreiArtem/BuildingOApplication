package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Machinery;
import com.onufrei.buildingo.service.machinery.interfaces.MachineryService;
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
 * Controller for Machinery object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/machineries")
@RestController
public class MachineryRestController {

	private final MachineryService service;

	private MachineryRestController(@Autowired MachineryService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Machinery> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Machinery add(@RequestBody Machinery spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Machinery getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Machinery update(@RequestBody Machinery spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Machinery delete(@PathVariable String id) {
		return service.delete(id);
	}
}