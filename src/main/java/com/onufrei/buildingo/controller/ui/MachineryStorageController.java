package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.BuildingStep;
import com.onufrei.buildingo.model.MachineryStorage;
import com.onufrei.buildingo.service.machineryStorage.interfaces.MachineryStorageService;
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
 * Represents object of MachineryStorageController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/machinery-storages")
@Controller
public class MachineryStorageController {

	private final MachineryStorageService storageService;

	public MachineryStorageController(@Autowired MachineryStorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping
	private String showAllBuildingSteps(Model model) {
		model.addAttribute("storages", storageService.findAll());
		return "machineryStorage/storages-page";
	}

	@GetMapping("/show/{id}")
	private String showBuildingStep(Model model, @PathVariable String id) {
		model.addAttribute("storage", storageService.findById(id));
		return "machineryStorage/show-storage-page";
	}

	@GetMapping("/add")
	private String showAddBuildingStep(Model model) {
		model.addAttribute("storage", new MachineryStorage("", "", "", "", LocalDateTime.now(), LocalDateTime.now()));

		return "machineryStorage/add-storage-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditBuildingStep(Model model, @PathVariable String id) {
		model.addAttribute("storage", storageService.findById(id));

		return "machineryStorage/edit-storage-page";
	}

	@PostMapping("/add")
	private String addBuildingStep(Model model, @ModelAttribute MachineryStorage storage) {
		MachineryStorage nStorage = new MachineryStorage(
				"",
				storage.getAddress(),
				storage.getName(),
				storage.getDescription(),
				LocalDateTime.now(),
				LocalDateTime.now()
		);

		storageService.add(nStorage);

		return "redirect:/machinery-storages";
	}

	@PostMapping("/edit/{id}")
	private String updateBuildingStep(Model model, @ModelAttribute MachineryStorage storage, @PathVariable String id) {
		storage.setId(id);
		storageService.update(storage);
		return "redirect:/machinery-storages";
	}

	@PostMapping("/delete/{id}")
	private String deleteBuildingStep(Model model, @PathVariable String id) {
		storageService.delete(id);
		return "redirect:/machinery-storages";
	}
	
}
