package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Machinery;
import com.onufrei.buildingo.service.machinery.interfaces.MachineryService;
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

	@ApiOperation(value = "Returns list of all machineries")
	@GetMapping("/")
	private List<Machinery> getAllMachineries() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new machinery")
	@PostMapping("/")
	private Machinery addMachinery(
			@ApiParam(name = "Machinery", value = "The json of machinery you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody Machinery machinery) {
		return service.add(machinery);
	}

	@ApiOperation(value = "Returns machinery of specified id")
	@GetMapping("/{id}")
	private Machinery getMachineryById(
			@ApiParam(name = "Machinery id", value = "The id of machinery you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified machinery by machinery you pass")
	@PutMapping("/")
	private Machinery updateMachinery(
			@ApiParam(name = "Machinery", value = "The json of machinery you want to update. "
					+ "The id of machinery you pass must correspond to machinery's id you want to update")
			@RequestBody Machinery machinery) {
		return service.update(machinery);
	}

	@ApiOperation(value = "Deletes the machinery with id you specify")
	@DeleteMapping("/{id}")
	private Machinery deleteMachinery(
			@ApiParam(name = "Machinery id", value = "The id of machinery you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}