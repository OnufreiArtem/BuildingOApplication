package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Spending;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
import com.onufrei.buildingo.service.spending.interfaces.SpendingService;
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
 * Represents object of SpendingController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/spendings")
@Controller
public class SpendingController {

	private final SpendingService spendingService;
	private final BuildingService buildingService;

	public SpendingController(@Autowired SpendingService spendingService, @Autowired BuildingService estimateService) {
		this.spendingService = spendingService;
		this.buildingService = estimateService;
	}

	@GetMapping
	private String showAllSpendings(Model model) {
		model.addAttribute("spendings", spendingService.findAll());
		return "spending/spendings-page";
	}

	@GetMapping("/show/{id}")
	private String showSpending(Model model, @PathVariable String id) {
		model.addAttribute("spending", spendingService.findById(id));
		return "spending/show-spending-page";
	}

	@GetMapping("/add")
	private String showAddSpending(Model model) {
		model.addAttribute("spending", new Spending("", "", "", 0, .0f, null, null,
				null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("buildings", buildingService.getMainInfo());

		return "spending/add-spending-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditSpending(Model model, @PathVariable String id) {
		model.addAttribute("spending", spendingService.findById(id));
		model.addAttribute("buildings", buildingService.getMainInfo());

		return "spending/edit-spending-page";
	}

	@PostMapping("/add")
	private String addSpending(Model model, @ModelAttribute Spending spending, @RequestParam("building_id") String buildingId) {
		spending.setBuilding(buildingService.findById(buildingId));
		spendingService.add(spending);

		return "redirect:/spendings";
	}

	@PostMapping("/edit/{id}")
	private String updateSpending(Model model, @ModelAttribute Spending spending, @PathVariable String id, @RequestParam("building_id") String buildingId) {
		spending.setId(id);
		spending.setBuilding(buildingService.findById(buildingId));
		spendingService.update(spending);
		return "redirect:/spendings";
	}

	@PostMapping("/delete/{id}")
	private String deleteSpending(Model model, @PathVariable String id) {
		spendingService.delete(id);
		return "redirect:/spendings";
	}

}
