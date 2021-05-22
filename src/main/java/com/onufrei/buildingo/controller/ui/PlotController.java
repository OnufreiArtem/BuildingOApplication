package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Plot;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Represents object of PlotController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/plots")
@Controller
public class PlotController {

	private final PlotService plotService;
	private final EmployeeService employeeService;

	public PlotController(PlotService plotService, EmployeeService employeeService) {
		this.plotService = plotService;
		this.employeeService = employeeService;
	}

	@GetMapping
	private String showAllPlots(Model model) {
		model.addAttribute("plots", plotService.findAll());
		return "plot/plots-page";
	}

	@GetMapping("/show/{id}")
	private String showPlot(Model model, @PathVariable String id) {
		model.addAttribute("plot", plotService.findById(id));
		return "plot/show-plot-page";
	}

	@GetMapping("/add")
	private String showAddPlot(Model model) {
		model.addAttribute("plot", new Plot("", "", null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "plot/add-plot-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditPlot(Model model, @PathVariable String id) {
		model.addAttribute("plot", plotService.findById(id));
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "plot/edit-plot-page";
	}

	@PostMapping("/add")
	private String addPlot(Model model, @ModelAttribute Plot plot, @RequestParam("employee_id") String employeeId) {
		Plot nPlot = new Plot(
				"",
				plot.getAddress(),
				employeeService.findById(employeeId),
				LocalDateTime.now(),
				LocalDateTime.now()
		);

		plotService.add(nPlot);

		return "redirect:/plots";
	}

	@PostMapping("/edit/{id}")
	private String updatePlot(Model model, @ModelAttribute Plot plot, @PathVariable String id, @RequestParam("employee_id") String employeeId) {
		plot.setId(id);
		plot.setChief(employeeService.findById(employeeId));
		plotService.update(plot);
		return "redirect:/plots";
	}

	@PostMapping("/delete/{id}")
	private String deletePlot(Model model, @PathVariable String id) {
		plotService.delete(id);
		return "redirect:/plots";
	}


}
