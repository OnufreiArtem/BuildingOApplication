package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.BuildingStep;
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * Represents object of BuildingStepController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 19.05.2021
 */

@RequestMapping("/building-steps")
@Controller
public class BuildingStepController {
	private final BuildingStepService service;

	public BuildingStepController(@Autowired BuildingStepService service) {
		this.service = service;
	}

	@GetMapping
	private String showAllBuildingSteps(Model model) {
		model.addAttribute("steps", service.findAll());
		return "buildingStep/building-steps-page";
	}

	@GetMapping("/show/{id}")
	private String showBuildingStep(Model model, @PathVariable String id) {
		model.addAttribute("step", service.findById(id));
		return "buildingStep/show-building-step-page";
	}

	@GetMapping("/add")
	private String showAddBuildingStep(Model model) {
		model.addAttribute("step", new BuildingStep("", "", "", LocalDateTime.now(), LocalDateTime.now()));

		return "buildingStep/add-building-step-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditBuildingStep(Model model, @PathVariable String id) {
		model.addAttribute("step", service.findById(id));

		return "buildingStep/edit-building-step-page";
	}

	@PostMapping("/add")
	private String addBuildingStep(Model model, @ModelAttribute BuildingStep step) {
		BuildingStep nStep = new BuildingStep(
				"",
				step.getName(),
				step.getDescription(),
				LocalDateTime.now(),
				LocalDateTime.now()
		);

		service.add(nStep);

		return "redirect:/building-steps";
	}

	@PostMapping("/edit/{id}")
	private String updateBuildingStep(Model model, @ModelAttribute BuildingStep step, @PathVariable String id) {

		service.update(step);
		return "redirect:/building-steps";
	}

	@PostMapping("/delete/{id}")
	private String deleteBuildingStep(Model model, @PathVariable String id) {
		service.delete(id);
		return "redirect:/building-steps";
	}
}
