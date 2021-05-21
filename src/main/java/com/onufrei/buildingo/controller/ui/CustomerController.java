package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Customer;
import com.onufrei.buildingo.model.CustomerType;
import com.onufrei.buildingo.service.customer.interfaces.CustomerService;
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
 * Represents object of CustomerController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/customers")
@Controller
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(@Autowired CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	private String showAllCustomers(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "customer/customers-page";
	}

	@GetMapping("/show/{id}")
	private String showCustomer(Model model, @PathVariable String id) {
		model.addAttribute("customer", customerService.findById(id));
		return "customer/show-customer-page";
	}

	@GetMapping("/add")
	private String showAddCustomer(Model model) {
		model.addAttribute("customer", new Customer("", null, "", "", "", "", "",
				LocalDateTime.now(), LocalDateTime.now()));
		return "customer/add-customer-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditCustomer(Model model, @PathVariable String id) {
		model.addAttribute("customer", customerService.findById(id));

		return "customer/edit-customer-page";
	}

	@PostMapping("/add")
	private String addCustomer(Model model, @ModelAttribute Customer customer, @RequestParam("string_type") String stringType) {
		CustomerType type = null;
		try {
			type = CustomerType.valueOf(stringType);
		} catch (IllegalArgumentException ignored) {
		}
		customer.setType(type);

		customerService.add(customer);

		return "redirect:/customers";
	}

	@PostMapping("/edit/{id}")
	private String updateCustomer(Model model, @ModelAttribute Customer customer, @RequestParam("string_type") String stringType, @PathVariable String id) {
		customer.setId(id);
		CustomerType type = null;
		try {
			type = CustomerType.valueOf(stringType);
		} catch (IllegalArgumentException ignored) {
		}
		customer.setType(type);
		customerService.update(customer);
		return "redirect:/customers";
	}

	@PostMapping("/delete/{id}")
	private String deleteCustomer(Model model, @PathVariable String id) {
		customerService.delete(id);
		return "redirect:/customers";
	}

}
