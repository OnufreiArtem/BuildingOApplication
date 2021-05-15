package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Brigade;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
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
 * Controller for Brigade object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/brigades")
@RestController
public class BrigadeRestController {

	private final BrigadeService service;

	private BrigadeRestController(@Autowired BrigadeService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Brigade> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Brigade add(@RequestBody Brigade spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Brigade getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Brigade update(@RequestBody Brigade spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Brigade delete(@PathVariable String id) {
		return service.delete(id);
	}
}