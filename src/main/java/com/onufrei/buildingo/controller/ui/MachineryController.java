package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Machinery;
import com.onufrei.buildingo.service.machinery.interfaces.MachineryService;
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService;
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
 * Represents object of MachineryController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/machineries")
@Controller
public class MachineryController {

	private final MachineryService machineryService;
	private final MachineryStorageService machineryStorageService;

	public MachineryController(@Autowired MachineryService machineryService, @Autowired MachineryStorageService machineryStorageService) {
		this.machineryService = machineryService;
		this.machineryStorageService = machineryStorageService;
	}

	@GetMapping
	private String showAllMachineries(Model model) {
		model.addAttribute("machineries", machineryService.findAll());
		return "machinery/machineries-page";
	}

	@GetMapping("/show/{id}")
	private String showMachinery(Model model, @PathVariable String id) {
		model.addAttribute("machinery", machineryService.findById(id));
		return "machinery/show-machinery-page";
	}

	@GetMapping("/add")
	private String showAddMachinery(Model model) {
		model.addAttribute("machinery", new Machinery("", "", "", "", "", 0, null, false,
				false, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("storages", machineryStorageService.getMainInfo());

		return "machinery/add-machinery-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditMachinery(Model model, @PathVariable String id) {
		model.addAttribute("machinery", machineryService.findById(id));
		model.addAttribute("storages", machineryStorageService.getMainInfo());

		return "machinery/edit-machinery-page";
	}

	@PostMapping("/add")
	private String addMachinery(Model model, @ModelAttribute Machinery machinery, @RequestParam("storage_id") String storageId) {
		machinery.setMachineryStorage(machineryStorageService.findById(storageId));
		machineryService.add(machinery);

		return "redirect:/machineries";
	}

	@PostMapping("/edit/{id}")
	private String updateMachinery(Model model, @ModelAttribute Machinery machinery, @PathVariable String id, @RequestParam("storage_id") String storageId) {
		machinery.setId(id);
		machinery.setMachineryStorage(machineryStorageService.findById(storageId));

		machineryService.update(machinery);
		return "redirect:/machineries";
	}

	@PostMapping("/delete/{id}")
	private String deleteMachinery(Model model, @PathVariable String id) {
		machineryService.delete(id);
		return "redirect:/machineries";
	}

}
