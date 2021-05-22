package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Building;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
import com.onufrei.buildingo.service.employee.interfaces.EmployeeService;
import com.onufrei.buildingo.service.plot.interfaces.PlotService;
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
 * Represents object of BuildingController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/buildings")
@Controller
public class BuildingController {

	private final BuildingService buildingService;
	private final PlotService plotService;
	private final EmployeeService employeeService;

	public BuildingController(@Autowired BuildingService buildingService, @Autowired PlotService plotService, @Autowired EmployeeService employeeService) {
		this.buildingService = buildingService;
		this.plotService = plotService;
		this.employeeService = employeeService;
	}

	@GetMapping
	private String showAllBuildings(Model model) {
		model.addAttribute("buildings", buildingService.findAll());
		return "building/buildings-page";
	}

	@GetMapping("/show/{id}")
	private String showBuilding(Model model, @PathVariable String id) {
		model.addAttribute("building", buildingService.findById(id));
		return "building/show-building-page";
	}

	@GetMapping("/add")
	private String showAddBuilding(Model model) {
		Building nBuilding = new Building(
				"",
				null,
				"",
				null,
				LocalDateTime.now(),
				LocalDateTime.now()
		);
		model.addAttribute("building", nBuilding);
		model.addAttribute("plots", plotService.getAddressList());
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "building/add-building-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditBuilding(Model model, @PathVariable String id) {
		model.addAttribute("building", buildingService.findById(id));
		model.addAttribute("plots", plotService.getAddressList());
		model.addAttribute("employees", employeeService.getIdNamePairs());

		return "building/edit-building-page";
	}

	@PostMapping("/add")
	private String addBuilding(Model model, @ModelAttribute Building building, @RequestParam("building_plot") String plotId,
			@RequestParam("employee_id") String employeeId ) {
		Building nBuilding = new Building(
				"",
				plotService.findById(plotId),
				building.getPlan(),
				employeeService.findById(employeeId),
				LocalDateTime.now(),
				LocalDateTime.now()
		);
		buildingService.add(nBuilding);

		return "redirect:/buildings";
	}

	@PostMapping("/edit/{id}")
	private String updateBuilding(Model model, @ModelAttribute Building building, @RequestParam("building_plot") String plotId,
			@RequestParam("employee_id") String employeeId, @PathVariable String id) {
		Building nBuilding = new Building(
				id,
				plotService.findById(plotId),
				building.getPlan(),
				employeeService.findById(employeeId),
				building.getCreatedAt(),
				building.getModifiedAt()
		);
		buildingService.update(nBuilding);
		return "redirect:/buildings";
	}

	@PostMapping("/delete/{id}")
	private String deleteBuilding(Model model, @PathVariable String id) {
		buildingService.delete(id);
		return "redirect:/buildings";
	}
	
	
	
	
}
