package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Estimate;
import com.onufrei.buildingo.service.estimate.interfaces.EstimateService;
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
 * Controller for Estimate object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/estimates")
@RestController
public class EstimateRestController {

	private final EstimateService service;

	private EstimateRestController(@Autowired EstimateService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all estimates")
	@GetMapping("/")
	private List<Estimate> getAllEstimates() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new estimate")
	@PostMapping("/")
	private Estimate addEstimate(
			@ApiParam(name = "Estimate", value = "The json of estimate you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody Estimate spec) {
		return service.add(spec);
	}

	@ApiOperation(value = "Returns estimate of specified id")
	@GetMapping("/{id}")
	private Estimate getEstimateById(
			@ApiParam(name = "Estimate id", value = "The id of estimate you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified estimate by estimate you pass")
	@PutMapping("/")
	private Estimate updateEstimate(
			@ApiParam(name = "Estimate", value = "The json of estimate you want to update. "
					+ "The id of estimate you pass must correspond to estimate's id you want to update")
			@RequestBody Estimate spec) {
		return service.update(spec);
	}

	@ApiOperation(value = "Deletes the estimate with id you specify")
	@DeleteMapping("/{id}")
	private Estimate deleteEstimate(
			@ApiParam(name = "Estimate id", value = "The id of estimate you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}