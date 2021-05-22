package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Brigade;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
import com.onufrei.buildingo.service.brigadeSpecification.interfaces.BrigadeSpecificationService;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
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
 * Represents object of BrigadeController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 18.05.2021
 */

@RequestMapping("/brigades")
@Controller
public class BrigadeController {

	private final BrigadeService brigadeService;
	private final EmployeeService employeeService;
	private final BrigadeSpecificationService brigadeSpecificationService;

	public BrigadeController(@Autowired BrigadeService brigadeService, @Autowired EmployeeService employeeService,
			@Autowired BrigadeSpecificationService brigadeSpecificationService) {
		this.brigadeService = brigadeService;
		this.employeeService = employeeService;
		this.brigadeSpecificationService = brigadeSpecificationService;
	}

	@GetMapping
	private String showAllBrigades(Model model) {
		model.addAttribute("brigades", brigadeService.findAll());
		return "brigade/brigades-page";
	}

	@GetMapping("/show/{id}")
	private String showBrigade(Model model, @PathVariable String id) {
		model.addAttribute("brigade", brigadeService.findById(id));
		return "brigade/show-brigade-page";
	}

	@GetMapping("/add")
	private String showAddBrigade(Model model) {
		model.addAttribute("brigade", new Brigade("", "", "", null, null, false, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("brigade_specs", brigadeSpecificationService.getIdNamePairs());
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "brigade/add-brigade-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditBrigade(Model model, @PathVariable String id) {
		model.addAttribute("brigade", brigadeService.findById(id));
		model.addAttribute("brigade_specs", brigadeSpecificationService.getIdNamePairs());
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "brigade/edit-brigade-page";
	}

	@PostMapping("/add")
	private String addBrigade(Model model, @ModelAttribute Brigade brigade, @RequestParam("brigade_spec_id") String brigadeSpecId,
			@RequestParam("employee_id") String employeeId ) {
		brigade.setChief(employeeService.findById(employeeId));
		brigade.setSpecification(brigadeSpecificationService.findById(brigadeSpecId));

		brigadeService.add(brigade);

		return "redirect:/brigades";
	}

	@PostMapping("/edit/{id}")
	private String updateBrigade(Model model, @ModelAttribute Brigade brigade, @RequestParam("brigade_spec_id") String brigadeSpecId,
			@RequestParam("employee_id") String employeeId, @PathVariable String id) {
		brigade.setId(id);
		brigade.setChief(employeeService.findById(employeeId));
		brigade.setSpecification(brigadeSpecificationService.findById(brigadeSpecId));
		brigadeService.update(brigade);
		return "redirect:/brigades";
	}

	@PostMapping("/delete/{id}")
	private String deleteBrigade(Model model, @PathVariable String id) {
		brigadeService.delete(id);
		return "redirect:/brigades";
	}
}
