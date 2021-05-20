package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.BrigadeSpecification;
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService;
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
 * Represents object of BrigadeSpecificationController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 19.05.2021
 */

@RequestMapping("/brigade-specs")
@Controller
public class BrigadeSpecificationController {

	private final BrigadeSpecificationService brigadeSpecificationService;

	public BrigadeSpecificationController(@Autowired BrigadeSpecificationService brigadeSpecificationService) {
		this.brigadeSpecificationService = brigadeSpecificationService;
	}

	@GetMapping
	private String showAllBrigadeSpecs(Model model) {
		model.addAttribute("specs", brigadeSpecificationService.findAll());
		return "brigadeSpecification/brigade-specs-page";
	}

	@GetMapping("/show/{id}")
	private String showBrigadeSpec(Model model, @PathVariable String id) {
		model.addAttribute("spec", brigadeSpecificationService.findById(id));
		return "brigadeSpecification/show-brigade-spec-page";
	}

	@GetMapping("/add")
	private String showAddBrigadeSpec(Model model) {
		model.addAttribute("spec", new BrigadeSpecification("", "", "", LocalDateTime.now(), LocalDateTime.now()));

		return "brigadeSpecification/add-brigade-spec-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditBrigadeSpec(Model model, @PathVariable String id) {
		model.addAttribute("spec", brigadeSpecificationService.findById(id));

		return "brigadeSpecification/edit-brigade-spec-page";
	}

	@PostMapping("/add")
	private String addBrigadeSpec(Model model, @ModelAttribute BrigadeSpecification spec) {
		BrigadeSpecification nSpec = new BrigadeSpecification(
				"",
				spec.getName(),
				spec.getDescription(),
				LocalDateTime.now(),
				LocalDateTime.now()
		);

		brigadeSpecificationService.add(nSpec);

		return "redirect:/brigade-specs";
	}

	@PostMapping("/edit/{id}")
	private String updateBrigadeSpec(Model model, @ModelAttribute BrigadeSpecification spec, @PathVariable String id) {
		spec.setId(id);
		brigadeSpecificationService.update(spec);
		return "redirect:/brigade-specs";
	}

	@PostMapping("/delete/{id}")
	private String deleteBrigadeSpec(Model model, @PathVariable String id) {
		brigadeSpecificationService.delete(id);
		return "redirect:/brigade-specs";
	}

}
