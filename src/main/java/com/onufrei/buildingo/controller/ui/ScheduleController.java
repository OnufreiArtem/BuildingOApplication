package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Schedule;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
import com.onufrei.buildingo.service.schedule.interfaces.ScheduleService;
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
 * Represents object of ScheduleController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/schedules")
@Controller
public class ScheduleController {

	private final ScheduleService scheduleService;
	private final BuildingService buildingService;

	public ScheduleController(@Autowired ScheduleService scheduleService, @Autowired BuildingService buildingService) {
		this.scheduleService = scheduleService;
		this.buildingService = buildingService;
	}

	@GetMapping
	private String showAllSchedules(Model model) {
		model.addAttribute("schedules", scheduleService.findAll());
		model.addAttribute("buildings", buildingService.getMainInfo());
		return "schedule/schedules-page";
	}

	@GetMapping("/show/{id}")
	private String showSchedule(Model model, @PathVariable String id) {
		model.addAttribute("schedule", scheduleService.findById(id));
		return "schedule/show-schedule-page";
	}

	@GetMapping("/add")
	private String showAddSchedule(Model model) {
		model.addAttribute("schedule", new Schedule("", null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("buildings", buildingService.getMainInfo());

		return "schedule/add-schedule-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditSchedule(Model model, @PathVariable String id) {
		model.addAttribute("schedule", scheduleService.findById(id));
		model.addAttribute("buildings", buildingService.getMainInfo());
		
		return "schedule/edit-schedule-page";
	}

	@PostMapping("/add")
	private String addSchedule(Model model, @ModelAttribute Schedule schedule, @RequestParam("building_id") String buildingId) {
		schedule.setTargetBuilding(buildingService.findById(buildingId));
		scheduleService.add(schedule);

		return "redirect:/schedules";
	}

	@PostMapping("/edit/{id}")
	private String updateSchedule(Model model, @ModelAttribute Schedule schedule, @PathVariable String id, @RequestParam("building_id") String buildingId) {
		schedule.setId(id);
		schedule.setTargetBuilding(buildingService.findById(buildingId));

		scheduleService.update(schedule);
		return "redirect:/schedules";
	}

	@PostMapping("/delete/{id}")
	private String deleteSchedule(Model model, @PathVariable String id) {
		scheduleService.delete(id);
		return "redirect:/schedules";
	}

}
