package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.Brigade;
import com.onufrei.buildingo.model.ScheduleEvent;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * Represents object of ScheduleEventController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/schedule-events")
@Controller
public class ScheduleEventController {

	private final ScheduleEventService scheduleEventService;
	private final ScheduleService scheduleService;
	private final BuildingStepService buildingStepService;
	private final BrigadeService brigadeService;

	public ScheduleEventController(@Autowired ScheduleEventService scheduleEventService, @Autowired ScheduleService scheduleService,
			@Autowired BuildingStepService buildingStepService, @Autowired BrigadeService brigadeService) {
		this.scheduleEventService = scheduleEventService;
		this.scheduleService = scheduleService;
		this.buildingStepService = buildingStepService;
		this.brigadeService = brigadeService;
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
		model.addAttribute("scheduleEvent",
				new ScheduleEvent("", "", "", null, new Brigade("", "", "", null, null,
						false, LocalDateTime.now(), LocalDateTime.now()), LocalDateTime.now(), LocalDateTime.now(), null, LocalDateTime.now(),
						LocalDateTime.now()));
		model.addAttribute("schedules", scheduleService.getPlansOfAllTargetBuildings());
		model.addAttribute("steps", buildingStepService.allStepNames());
		model.addAttribute("brigades", brigadeService.getMainInfo());
		return "scheduleEvent/add-scheduleEvent-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditScheduleEvent(Model model, @PathVariable String id) {
		model.addAttribute("scheduleEvent", scheduleEventService.findById(id));
		model.addAttribute("schedules", scheduleService.getPlansOfAllTargetBuildings());
		model.addAttribute("steps", buildingStepService.allStepNames());
		model.addAttribute("brigades", brigadeService.getMainInfo());

		return "scheduleEvent/edit-scheduleEvent-page";
	}

	@PostMapping("/add")
	private String addScheduleEvent(Model model, @ModelAttribute ScheduleEvent scheduleEvent, @RequestParam("schedule_id") String scheduleId,
			@RequestParam("step_id") String stepId, @RequestParam("brigade_id") String brigadeId) {
		scheduleEvent.setSchedule(scheduleService.findById(scheduleId));
		scheduleEvent.setBuildingStep(buildingStepService.findById(stepId));
		scheduleEvent.setBrigade(brigadeService.findById(brigadeId));
		scheduleEventService.add(scheduleEvent);

		return "redirect:/schedule-events";
	}

	@PostMapping("/edit/{id}")
	private String updateScheduleEvent(Model model, @ModelAttribute ScheduleEvent scheduleEvent, @PathVariable String id,
			@RequestParam("schedule_id") String scheduleId, @RequestParam("step_id") String stepId, @RequestParam("brigade_id") String brigadeId) {
		scheduleEvent.setId(id);
		scheduleEvent.setSchedule(scheduleService.findById(scheduleId));
		scheduleEvent.setBuildingStep(buildingStepService.findById(stepId));
		scheduleEvent.setBrigade(brigadeService.findById(brigadeId));

		scheduleEventService.update(scheduleEvent);
		return "redirect:/schedule-events";
	}

	@PostMapping("/delete/{id}")
	private String deleteScheduleEvent(Model model, @PathVariable String id) {
		scheduleEventService.delete(id);
		return "redirect:/schedule-events";
	}

}
