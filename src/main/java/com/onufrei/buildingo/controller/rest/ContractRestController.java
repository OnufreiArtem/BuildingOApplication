package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Contract;
import com.onufrei.buildingo.service.contract.interfaces.ContractService;
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
 * Controller for Contract object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/contracts")
@RestController
public class ContractRestController {

	private final ContractService service;

	private ContractRestController(@Autowired ContractService service) {
		this.service = service;
	}

	@GetMapping("/")
	private List<Contract> getAll() {
		return service.findAll();
	}

	@PostMapping("/")
	private Contract add(@RequestBody Contract spec) {
		return service.add(spec);
	}

	@GetMapping("/{id}")
	private Contract getById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("/")
	private Contract update(@RequestBody Contract spec) {
		return service.update(spec);
	}

	@DeleteMapping("/{id}")
	private Contract delete(@PathVariable String id) {
		return service.delete(id);
	}
}