package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Schedule;
import com.onufrei.buildingo.service.schedule.interfaces.ScheduleService;
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
 * Controller for Schedule object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/schedules")
@RestController
public class ScheduleRestController {

	private final ScheduleService service;

	private ScheduleRestController(@Autowired ScheduleService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Schedule> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Schedule add(@RequestBody Schedule spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Schedule getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Schedule update(@RequestBody Schedule spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Schedule delete(@PathVariable String id) {
		return service.delete(id);
	}
}