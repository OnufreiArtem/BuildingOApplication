package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Spending;
import com.onufrei.buildingo.service.spending.interfaces.SpendingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * Controller for Spending object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/spendings")
@RestController
public class SpendingRestController {

	private final SpendingService service;

	private SpendingRestController(@Autowired SpendingService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all spendings")
	@GetMapping("/")
	private List<Spending> getAllSpendings() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new spending")
	@PostMapping("/")
	private Spending addSpending(@ApiParam(name = "Spending", value = "The json of spending you want to add. "
			+ "Id, createdAt and modifiedAt dates generate automatically") @RequestBody Spending spending) {
		return service.add(spending);
	}

	@ApiOperation(value = "Returns spending of specified id")
	@GetMapping("/{id}")
	private Spending getSpendingById(@ApiParam(name = "Spending id", value = "The id of spending you want to get") @PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified spending by spending you pass")
	@PutMapping("/")
	private Spending updateSpending(@ApiParam(name = "Spending", value = "The json of customer you want to update. "
			+ "The id of spending you pass must correspond to spending's id you want to update") @RequestBody Spending spending) {
		return service.update(spending);
	}

	@ApiOperation(value = "Deletes the spending with id you specify")
	@DeleteMapping("/{id}")
	private Spending deleteSpending(@ApiParam(name = "Spending id", value = "The id of spending you want to delete") @PathVariable String id) {
		return service.delete(id);
	}
}