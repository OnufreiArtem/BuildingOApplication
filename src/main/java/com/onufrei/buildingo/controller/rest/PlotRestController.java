package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Plot;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
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
 * Controller for Plot object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/plots")
@RestController
public class PlotRestController {

	private final PlotService service;

	private PlotRestController(@Autowired PlotService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Plot> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Plot add(@RequestBody Plot spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Plot getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Plot update(@RequestBody Plot spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Plot delete(@PathVariable String id) {
		return service.delete(id);
	}
}