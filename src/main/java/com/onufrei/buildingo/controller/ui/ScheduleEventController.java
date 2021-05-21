package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Schedule;
import com.onufrei.buildingo.model.ScheduleEvent;
import com.onufrei.buildingo.service.buildingStep.interfaces.BuildingStepService;
import com.onufrei.buildingo.service.schedule.interfaces.ScheduleService;
import com.onufrei.buildingo.service.scheduleEvent.interfaces.ScheduleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents object of ScheduleEventController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/scheduleEvents")
@Controller
public class ScheduleEventController {

	private final ScheduleEventService scheduleEventService;
	private final ScheduleService scheduleService;
	private final BuildingStepService buildingStepService;

	public ScheduleEventController(@Autowired ScheduleEventService scheduleEventService, @Autowired ScheduleService scheduleService,
			@Autowired BuildingStepService buildingStepService) {
		this.scheduleEventService = scheduleEventService;
		this.scheduleService = scheduleService;
		this.buildingStepService = buildingStepService;
	}

	@GetMapping
	private String showAllScheduleEvents(Model model) {
		model.addAttribute("scheduleEvents", scheduleEventService.findAll());
		return "scheduleEvent/scheduleEvents-page";
	}

	@GetMapping("/show/{id}")
	private String showScheduleEvent(Model model, @PathVariable String id) {
		model.addAttribute("scheduleEvent", scheduleEventService.findById(id));
		return "scheduleEvent/show-scheduleEvent-page";
	}

	@GetMapping("/add")
	private String showAddScheduleEvent(Model model) {
		model.addAttribute("scheduleEvent", new ScheduleEvent("", "", "", null, new ArrayList<>(), LocalDateTime.now(),
				LocalDateTime.now(), null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("schedules", scheduleService.findAll().stream().map(Schedule::getId).collect(Collectors.toList()));
		model.addAttribute("steps", buildingStepService.findAll());
		return "scheduleEvent/add-scheduleEvent-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditScheduleEvent(Model model, @PathVariable String id) {
		model.addAttribute("scheduleEvent", scheduleEventService.findById(id));

		return "scheduleEvent/edit-scheduleEvent-page";
	}

	@PostMapping("/add")
	private String addScheduleEvent(Model model, @ModelAttribute ScheduleEvent scheduleEvent) {
		scheduleEventService.add(scheduleEvent);

		return "redirect:/scheduleEvents";
	}

	@PostMapping("/edit/{id}")
	private String updateScheduleEvent(Model model, @ModelAttribute ScheduleEvent scheduleEvent, @PathVariable String id) {
		scheduleEvent.setId(id);

		scheduleEventService.update(scheduleEvent);
		return "redirect:/scheduleEvents";
	}

	@PostMapping("/delete/{id}")
	private String deleteScheduleEvent(Model model, @PathVariable String id) {
		scheduleEventService.delete(id);
		return "redirect:/scheduleEvents";
	}


}
