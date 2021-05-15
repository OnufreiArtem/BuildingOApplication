package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Request;
import com.onufrei.buildingo.service.request.interfaces.RequestService;
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
 * Controller for Request object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/requests")
@RestController
public class RequestRestController {

	private final RequestService service;

	private RequestRestController(@Autowired RequestService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Request> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Request add(@RequestBody Request spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Request getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Request update(@RequestBody Request spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Request delete(@PathVariable String id) {
		return service.delete(id);
	}
}