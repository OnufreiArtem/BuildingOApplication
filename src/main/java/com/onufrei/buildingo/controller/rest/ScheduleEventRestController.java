package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.ScheduleEvent;
import com.onufrei.buildingo.service.scheduleEvent.interfaces.ScheduleEventService;
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
 * Controller for ScheduleEvent object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/schedule-events")
@RestController
public class ScheduleEventRestController {

	private final ScheduleEventService service;

	private ScheduleEventRestController(@Autowired ScheduleEventService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<ScheduleEvent> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private ScheduleEvent add(@RequestBody ScheduleEvent spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private ScheduleEvent getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private ScheduleEvent update(@RequestBody ScheduleEvent spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private ScheduleEvent delete(@PathVariable String id) {
		return service.delete(id);
	}
}