package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.model.Request;
import com.onufrei.buildingo.service.request.interfaces.RequestService;
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
 * Controller for Request object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/requests")
@RestController
public class RequestRestController {

	private final RequestService service;

	private RequestRestController(@Autowired RequestService service) {
		this.service = service;
	}

	@ApiOperation(value = "Returns list of all requests")
	@GetMapping("/")
	private List<Request> getAllRequests() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new request")
	@PostMapping("/")
	private Request addRequest(
			@ApiParam(name = "Request", value = "The json of request you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody Request request) {
		return service.add(request);
	}

	@ApiOperation(value = "Returns request of specified id")
	@GetMapping("/{id}")
	private Request getRequestById(
			@ApiParam(name = "Request id", value = "The id of request you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified request by request you pass")
	@PutMapping("/")
	private Request updateRequest(
			@ApiParam(name = "Request", value = "The json of request you want to update. "
					+ "The id of request you pass must correspond to request's id you want to update")
			@RequestBody Request request) {
		return service.update(request);
	}

	@ApiOperation(value = "Deletes the request with id you specify")
	@DeleteMapping("/{id}")
	private Request deleteRequest(
			@ApiParam(name = "Request id", value = "The id of request you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}