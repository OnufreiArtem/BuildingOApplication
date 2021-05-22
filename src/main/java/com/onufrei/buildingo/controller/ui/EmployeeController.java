package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Employee;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.employeeSpecification.interfaces.EmployeeSpecificationService;
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
 * Represents object of EmployeeController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/employees")
@Controller
public class EmployeeController {

	private final EmployeeService employeeService;
	private final EmployeeSpecificationService specificationService;

	public EmployeeController(@Autowired EmployeeService employeeService, @Autowired EmployeeSpecificationService specificationService) {
		this.employeeService = employeeService;
		this.specificationService = specificationService;
	}

	@GetMapping
	private String showAllEmployees(Model model) {
		model.addAttribute("employees", employeeService.findAll());

		return "employee/employees-page";
	}

	@GetMapping("/show/{id}")
	private String showEmployee(Model model, @PathVariable String id) {
		model.addAttribute("employee", employeeService.findById(id));
		return "employee/show-employee-page";
	}

	@GetMapping("/add")
	private String showAddEmployee(Model model) {
		model.addAttribute("employee", new Employee("", "", "", null, 0, "", "", null, null,
				false, null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("specifications", specificationService.getListOfSpecificationNames());

		return "employee/add-employee-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditEmployee(Model model, @PathVariable String id) {
		model.addAttribute("employee", employeeService.findById(id));
		model.addAttribute("specifications", specificationService.getListOfSpecificationNames());

		return "employee/edit-employee-page";
	}

	@PostMapping("/add")
	private String addEmployee(Model model, @ModelAttribute Employee employee, @RequestParam("specification_id") String specificationId) {
		employee.setSpecification(specificationService.findById(specificationId));
		employeeService.add(employee);

		return "redirect:/employees";
	}

	@PostMapping("/edit/{id}")
	private String updateEmployee(Model model, @ModelAttribute Employee employee, @PathVariable String id, @RequestParam("specification_id") String specificationId) {
		employee.setId(id);
		employee.setSpecification(specificationService.findById(specificationId));

		employeeService.update(employee);
		return "redirect:/employees";
	}

	@PostMapping("/delete/{id}")
	private String deleteEmployee(Model model, @PathVariable String id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}

}
