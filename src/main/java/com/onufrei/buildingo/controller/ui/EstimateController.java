package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Estimate;
import com.onufrei.buildingo.model.Plot;
import com.onufrei.buildingo.service.estimate.interfaces.EstimateService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Represents object of EstimateController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/estimates")
@Controller
public class EstimateController {

	private final EstimateService estimateService;
	private final PlotService plotService;

	public EstimateController(@Autowired EstimateService estimateService, @Autowired PlotService plotService) {
		this.estimateService = estimateService;
		this.plotService = plotService;
	}

	@GetMapping
	private String showAllEstimates(Model model) {
		model.addAttribute("estimates", estimateService.findAll());
		return "estimate/estimates-page";
	}

	@GetMapping("/show/{id}")
	private String showEstimate(Model model, @PathVariable String id) {
		model.addAttribute("estimate", estimateService.findById(id));
		return "estimate/show-estimate-page";
	}

	@GetMapping("/add")
	private String showAddEstimate(Model model) {
		model.addAttribute("estimate", new Estimate("", null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("plots", plotService.getAddressList());

		return "estimate/add-estimate-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditEstimate(Model model, @PathVariable String id) {
		model.addAttribute("estimate", estimateService.findById(id));
		model.addAttribute("plots", plotService.getAddressList());

		return "estimate/edit-estimate-page";
	}

	@PostMapping("/add")
	private String addEstimate(Model model, @ModelAttribute Estimate estimate, @RequestParam("estimate_plot") String plotId) {
		estimate.setPlot(plotService.findById(plotId));
		estimateService.add(estimate);

		return "redirect:/estimates";
	}

	@PostMapping("/edit/{id}")
	private String updateEstimate(Model model, @ModelAttribute Estimate estimate, @PathVariable String id, @RequestParam("estimate_plot") String plotId) {
		estimate.setId(id);
		estimate.setPlot(plotService.findById(plotId));
		estimateService.update(estimate);
		return "redirect:/estimates";
	}

	@PostMapping("/delete/{id}")
	private String deleteEstimate(Model model, @PathVariable String id) {
		estimateService.delete(id);
		return "redirect:/estimates";
	}
}
