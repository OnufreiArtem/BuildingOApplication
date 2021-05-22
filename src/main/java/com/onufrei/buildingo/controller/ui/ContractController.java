package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Contract;
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService;
import com.onufrei.buildingo.service.contract.interfaces.ContractService;
import com.onufrei.buildingo.service.customer.interfaces.CustomerService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents object of ContractController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/contracts")
@Controller
public class ContractController {

	private final ContractService contractService;
	private final ConstructionManagementService constructionManagementService;
	private final CustomerService customerService;
	private final PlotService plotService;

	public ContractController(ContractService contractService, ConstructionManagementService constructionManagementService, CustomerService customerService,
			PlotService plotService) {
		this.contractService = contractService;
		this.constructionManagementService = constructionManagementService;
		this.customerService = customerService;
		this.plotService = plotService;
	}

	@GetMapping
	private String showAllContracts(Model model) {
		model.addAttribute("contracts", contractService.findAll());
		return "contract/contracts-page";
	}

	@GetMapping("/show/{id}")
	private String showContract(Model model, @PathVariable String id) {
		model.addAttribute("contract", contractService.findById(id));
		return "contract/show-contract-page";
	}

	@GetMapping("/add")
	private String showAddContract(Model model) {
		Contract emptyContract = new Contract("", null, null, 0, null, null,
				null, null, false, false, LocalDateTime.now(), LocalDateTime.now());
		model.addAttribute("contract", emptyContract);
		model.addAttribute("customers", customerService.getCustomerContactText());
		model.addAttribute("managements", constructionManagementService.getAllAddresses());
		model.addAttribute("plots", plotService.getAddressList());

		return "contract/add-contract-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditContract(Model model, @PathVariable String id) {
		model.addAttribute("contract", contractService.findById(id));
		model.addAttribute("customers", customerService.getCustomerContactText());
		model.addAttribute("managements", constructionManagementService.getAllAddresses());
		model.addAttribute("plots", plotService.getAddressList());

		return "contract/edit-contract-page";
	}

	@PostMapping("/add")
	private String addContract(Model model, @ModelAttribute Contract contract, @RequestParam("customer_id") String customerId,
			@RequestParam("plot_id") String plotId, @RequestParam("management_id") String managementId) {
		contract.setCustomer(customerService.findById(customerId));
		contract.setConstructionManagement(constructionManagementService.findById(managementId));
		contract.setPlot(plotService.findById(plotId));
		contractService.add(contract);
		return "redirect:/contracts";
	}

	@PostMapping("/edit/{id}")
	private String updateContract(Model model, @ModelAttribute Contract contract, @PathVariable String id, @RequestParam("customer_id") String customerId,
			@RequestParam("plot_id") String plotId, @RequestParam("management_id") String managementId) {
		contract.setId(id);
		contract.setCustomer(customerService.findById(customerId));
		contract.setConstructionManagement(constructionManagementService.findById(managementId));
		contract.setPlot(plotService.findById(plotId));

		contractService.update(contract);
		return "redirect:/contracts";
	}

	@PostMapping("/delete/{id}")
	private String deleteContract(Model model, @PathVariable String id) {
		contractService.delete(id);
		return "redirect:/contracts";
	}
}
