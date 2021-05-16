package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Brigade;
import com.onufrei.buildingo.model.BrigadeSpecification;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService;
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
 * Controller for BrigadeSpecification object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@RequestMapping("api/v1/brigade-specs")
@RestController
public class BrigadeSpecificationRestController {

	private final BrigadeSpecificationService service;

	private BrigadeSpecificationRestController(@Autowired BrigadeSpecificationService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all brigade specifications")
	@GetMapping("/")
	private List<BrigadeSpecification> getAllBrigadeSpecifications() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new brigade specification")
	@PostMapping("/")
	private BrigadeSpecification addBrigadeSpecification(
			@ApiParam(name = "Brigade Specification", value = "The json of brigade specification you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody BrigadeSpecification brigadeSpecification) {
		return service.add(brigadeSpecification);
	}

	@ApiOperation(value = "Returns brigade specification of specified id")
	@GetMapping("/{id}")
	private BrigadeSpecification getBrigadeSpecificationById(
			@ApiParam(name = "Brigade Specification id", value = "The id of brigade specification you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified brigade specification by brigade specification you pass")
	@PutMapping("/")
	private BrigadeSpecification updateBrigadeSpecification(
			@ApiParam(name = "Brigade Specification", value = "The json of brigade specification you want to update. "
					+ "The id of brigade specification you pass must correspond to brigade specification's id you want to update")
			@RequestBody BrigadeSpecification brigadeSpecification) {
		return service.update(brigadeSpecification);
	}

	@ApiOperation(value = "Deletes the brigade specification with id you specify")
	@DeleteMapping("/{id}")
	private BrigadeSpecification deleteBrigadeSpecification(
			@ApiParam(name = "Brigade Specification id", value = "The id of brigade specification you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}