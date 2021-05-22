package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Schedule;
import com.onufrei.buildingo.service.schedule.interfaces.ScheduleService;
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

	@ApiOperation(value = "Returns list of all schedules")
	@GetMapping("/")
	private List<Schedule> getAllSchedules() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new schedule")
	@PostMapping("/")
	private Schedule addSchedule(
			@ApiParam(name = "Schedule", value = "The json of schedule you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody Schedule schedule) {
		return service.add(schedule);
	}

	@ApiOperation(value = "Returns schedule of specified id")
	@GetMapping("/{id}")
	private Schedule getByIdSchedule(
			@ApiParam(name = "Schedule id", value = "The id of schedule you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified schedule by schedule you pass")
	@PutMapping("/")
	private Schedule updateSchedule(
			@ApiParam(name = "Schedule", value = "The json of schedule you want to update. "
					+ "The id of schedule you pass must correspond to schedule's id you want to update")
			@RequestBody Schedule schedule) {
		return service.update(schedule);
	}

	@ApiOperation(value = "Deletes the schedule with id you specify")
	@DeleteMapping("/{id}")
	private Schedule deleteSchedule(
			@ApiParam(name = "Schedule id", value = "The id of schedule you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}