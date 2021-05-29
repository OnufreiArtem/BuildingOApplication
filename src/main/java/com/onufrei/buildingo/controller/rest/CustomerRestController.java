package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.CustomerForm;
import com.onufrei.buildingo.model.Customer;
import com.onufrei.buildingo.service.customer.interfaces.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kotlin.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin
@RequestMapping("api/v1/customers")
@RestController
public class CustomerRestController {

	private final CustomerService service;

	private CustomerRestController(@Autowired CustomerService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all customers")
	@GetMapping("/")
	private List<Customer> getAllCustomers() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new customer")
	@PostMapping("/")
	private Customer addCustomer(
			@ApiParam(name = "Customer", value = "The json of customer you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody CustomerForm customerForm) {
		return service.add(customerForm.toCustomerEntity());
	}

	@ApiOperation(value = "Returns customer of specified id")
	@GetMapping("/{id}")
	private Customer getCustomerById(
			@ApiParam(name = "Customer id", value = "The id of customer you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified customer by customer you pass")
	@PutMapping("/")
	private Customer updateCustomer(
			@ApiParam(name = "Customer", value = "The json of customer you want to update. "
					+ "The id of customer you pass must correspond to customer's id you want to update")
			@RequestBody CustomerForm customerForm) {
		return service.update(customerForm.toCustomerEntity());
	}

	@ApiOperation(value = "Returns all customers id and contacts")
	@GetMapping("/contacts")
	private List<Pair<String, String>> getBuildingsMainInfo() {
		return service.getCustomerContactText();
	}

	@ApiOperation(value = "Deletes the customer with id you specify")
	@DeleteMapping("/{id}")
	private Customer deleteCustomer(
			@ApiParam(name = "Customer id", value = "The id of customer you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}