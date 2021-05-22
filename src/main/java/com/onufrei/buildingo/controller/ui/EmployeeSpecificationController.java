package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.EmployeeSpecification;
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService;
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
 * Represents object of EmployeeSpecificationController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/employee-specs")
@Controller
public class EmployeeSpecificationController {

	private final EmployeeSpecificationService employeeSpecificationService;

	public EmployeeSpecificationController(@Autowired EmployeeSpecificationService employeeSpecificationService) {
		this.employeeSpecificationService = employeeSpecificationService;
	}

	@GetMapping
	private String showAllEmployeeSpecs(Model model) {
		model.addAttribute("specs", employeeSpecificationService.findAll());
		return "employeeSpecification/employee-specs-page";
	}

	@GetMapping("/show/{id}")
	private String showEmployeeSpec(Model model, @PathVariable String id) {
		model.addAttribute("spec", employeeSpecificationService.findById(id));
		return "employeeSpecification/show-employee-spec-page";
	}

	@GetMapping("/add")
	private String showAddEmployeeSpec(Model model) {
		model.addAttribute("spec", new EmployeeSpecification("", "", "", LocalDateTime.now(), LocalDateTime.now()));

		return "employeeSpecification/add-employee-spec-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditEmployeeSpec(Model model, @PathVariable String id) {
		model.addAttribute("spec", employeeSpecificationService.findById(id));

		return "employeeSpecification/edit-employee-spec-page";
	}

	@PostMapping("/add")
	private String addEmployeeSpec(Model model, @ModelAttribute EmployeeSpecification spec) {
		EmployeeSpecification nSpec = new EmployeeSpecification(
				"",
				spec.getName(),
				spec.getDescription(),
				LocalDateTime.now(),
				LocalDateTime.now()
		);

		employeeSpecificationService.add(nSpec);

		return "redirect:/employee-specs";
	}

	@PostMapping("/edit/{id}")
	private String updateEmployeeSpec(Model model, @ModelAttribute EmployeeSpecification spec, @PathVariable String id) {
		spec.setId(id);
		employeeSpecificationService.update(spec);
		return "redirect:/employee-specs";
	}

	@PostMapping("/delete/{id}")
	private String deleteEmployeeSpec(Model model, @PathVariable String id) {
		employeeSpecificationService.delete(id);
		return "redirect:/employee-specs";
	}
}
