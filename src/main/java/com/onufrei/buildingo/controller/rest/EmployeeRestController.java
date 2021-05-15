package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Employee;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
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
 * Controller for Employee object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/employees")
@RestController
public class EmployeeRestController {

	private final EmployeeService service;

	private EmployeeRestController(@Autowired EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Employee> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Employee add(@RequestBody Employee spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Employee getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Employee update(@RequestBody Employee spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Employee delete(@PathVariable String id) {
		return service.delete(id);
	}
}