package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.MachineryStorage;
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService;
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

	@ApiOperation(value = "Returns list of all machinery storages")
	@GetMapping("/")
	private List<MachineryStorage> getAllMachineryStorages() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new customer")
	@PostMapping("/")
	private MachineryStorage addMachineryStorage(
			@ApiParam(name = "Machinery storage", value = "The json of machinery storage you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody MachineryStorage machineryStorage) {
		return service.add(machineryStorage);
	}

	@ApiOperation(value = "Returns machinery storage of specified id")
	@GetMapping("/{id}")
	private MachineryStorage getMachineryStorageById(
			@ApiParam(name = "Machinery storage id", value = "The id of machinery storage you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified customer by machinery storage you pass")
	@PutMapping("/")
	private MachineryStorage updateMachineryStorage(
			@ApiParam(name = "Machinery storage", value = "The json of machinery storage you want to update. "
					+ "The id of machinery storage you pass must correspond to machinery storage's id you want to update")
			@RequestBody MachineryStorage machineryStorage) {
		return service.update(machineryStorage);
	}

	@ApiOperation(value = "Deletes the machinery storage with id you specify")
	@DeleteMapping("/{id}")
	private MachineryStorage deleteMachineryStorage(
			@ApiParam(name = "Machinery storage id", value = "The id of machinery storage you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}