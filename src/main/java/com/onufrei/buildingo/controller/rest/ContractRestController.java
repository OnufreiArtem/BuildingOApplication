package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Contract;
import com.onufrei.buildingo.service.contract.interfaces.ContractService;
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

	@ApiOperation(value = "Returns list of all contract")
	@GetMapping("/")
	private List<Contract> getAllContracts() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new contract")
	@PostMapping("/")
	private Contract addContract(
			@ApiParam(name = "Contract", value = "The json of contract you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody Contract contract) {
		return service.add(contract);
	}

	@ApiOperation(value = "Returns contract of specified id")
	@GetMapping("/{id}")
	private Contract getContractById(
			@ApiParam(name = "Contract id", value = "The id of contract you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified contract by contract you pass")
	@PutMapping("/")
	private Contract updateContract(
			@ApiParam(name = "Contract", value = "The json of contract you want to update. "
					+ "The id of contract you pass must correspond to contract's id you want to update")
			@RequestBody Contract contract) {
		return service.update(contract);
	}

	@ApiOperation(value = "Deletes the contract with id you specify")
	@DeleteMapping("/{id}")
	private Contract deleteContract(
			@ApiParam(name = "Contract id", value = "The id of contract you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}