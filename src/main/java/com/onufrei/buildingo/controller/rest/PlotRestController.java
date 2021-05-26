package com.onufrei.buildingo.controller.rest;

import com.onufrei.buildingo.forms.PlotForm;
import com.onufrei.buildingo.model.Plot;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
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
 * Controller for Plot object
 *
 * @author Artem Onufrei
 * @version 1
 * @since 15.05.2021
 */

@CrossOrigin
@RequestMapping("api/v1/plots")
@RestController
public class PlotRestController {

	private final PlotService service;
	private final EmployeeService employeeService;

	private PlotRestController(@Autowired PlotService service, @Autowired EmployeeService employeeService) {
		this.service = service;
		this.employeeService = employeeService;
	}

	@ApiOperation(value = "Returns list of all plots")
	@GetMapping("/")
	private List<Plot> getAllPlots() {
		return service.findAll();
	}

	@ApiOperation(value = "Adds new plot")
	@PostMapping("/")
	private Plot addPlot(
			@ApiParam(name = "Plot", value = "The json of plot you want to add. "
					+ "Id, createdAt and modifiedAt dates generate automatically")
			@RequestBody PlotForm plotForm) {
		return service.add(plotForm.toPlotEntity(employeeService));
	}

	@ApiOperation(value = "Returns plot of specified id")
	@GetMapping("/{id}")
	private Plot getPlotById(
			@ApiParam(name = "Plot id", value = "The id of plot you want to get")
			@PathVariable String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Updates specified plot by plot you pass")
	@PutMapping("/")
	private Plot updatePlot(
			@ApiParam(name = "Plot", value = "The json of plot you want to update. "
					+ "The id of plot you pass must correspond to plot's id you want to update")
			@RequestBody PlotForm plotForm) {
		return service.update(plotForm.toPlotEntity(employeeService));
	}

	@ApiOperation(value = "Deletes the plot with id you specify")
	@DeleteMapping("/{id}")
	private Plot deletePlot(
			@ApiParam(name = "Plot id", value = "The id of plot you want to delete")
			@PathVariable String id) {
		return service.delete(id);
	}
}