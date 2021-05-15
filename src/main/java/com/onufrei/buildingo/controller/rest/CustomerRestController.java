package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Customer;
import com.onufrei.buildingo.service.customer.interfaces.CustomerService;
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
 * Controller for Customer object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/customers")
@RestController
public class CustomerRestController {

	private final CustomerService service;

	private CustomerRestController(@Autowired CustomerService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Customer> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Customer add(@RequestBody Customer spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Customer getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Customer update(@RequestBody Customer spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Customer delete(@PathVariable String id) {
		return service.delete(id);
	}
}