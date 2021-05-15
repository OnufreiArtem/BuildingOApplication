package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.MachineryStorage;
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService;
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
 * Controller for MachineryStorage object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/machinery-storages")
@RestController
public class MachineryStorageRestController {

	private final MachineryStorageService service;

	private MachineryStorageRestController(@Autowired MachineryStorageService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<MachineryStorage> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private MachineryStorage add(@RequestBody MachineryStorage spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private MachineryStorage getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private MachineryStorage update(@RequestBody MachineryStorage spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private MachineryStorage delete(@PathVariable String id) {
		return service.delete(id);
	}
}