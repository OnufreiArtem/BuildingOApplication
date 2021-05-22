package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.ScheduleEvent;
import com.onufrei.buildingo.service.scheduleEvent.interfaces.ScheduleEventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

	@ApiOperation(value = "Returns list of all schedule events")
	@GetMapping("/")
	private List<ScheduleEvent> getAllScheduleEvents() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new customer")
	@PostMapping("/")
	private ScheduleEvent addScheduleEvent(
			@ApiParam(name = "Schedule event", value = "The json of schedule event you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody ScheduleEvent scheduleEvent) {
		return service.add(scheduleEvent);
	}

	@ApiOperation(value = "Returns schedule event of specified id")
	@GetMapping("/{id}")
	private ScheduleEvent getScheduleEventById(
			@ApiParam(name = "Schedule event id", value = "The id of schedule event you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified schedule event by schedule event you pass")
	@PutMapping("/")
	private ScheduleEvent updateScheduleEvent(
			@ApiParam(name = "Schedule event", value = "The json of schedule event you want to update. "
					+ "The id of schedule event you pass must correspond to schedule event's id you want to update")
			@RequestBody ScheduleEvent scheduleEvent) {
		return service.update(scheduleEvent);
	}

	@ApiOperation(value = "Deletes the schedule event with id you specify")
	@DeleteMapping("/{id}")
	private ScheduleEvent deleteScheduleEvent(
			@ApiParam(name = "Schedule event id", value = "The id of schedule event you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}