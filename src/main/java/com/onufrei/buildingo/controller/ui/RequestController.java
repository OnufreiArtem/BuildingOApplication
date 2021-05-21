package com.onufrei.buildingo.controller.ui;

import com.onufrei.buildingo.model.CustomerType;
import com.onufrei.buildingo.model.Request;
import com.onufrei.buildingo.model.RequestType;
import com.onufrei.buildingo.service.brigade.interfaces.BrigadeService;
import com.onufrei.buildingo.service.building.interfaces.BuildingService;
import com.onufrei.buildingo.service.constructionManagement.interfaces.ConstructionManagementService;
import com.onufrei.buildingo.service.request.interfaces.RequestService;
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
 * Represents object of RequestController
 *
 * @author Artem Onufrei
 * @version 1
 * @since 20.05.2021
 */

@RequestMapping("/requests")
@Controller
public class RequestController {

	private final RequestService requestService;
	private final BrigadeService brigadeService;
	private final ConstructionManagementService managementService;
	private final BuildingService buildingService;

	public RequestController(@Autowired RequestService requestService, @Autowired BrigadeService brigadeService,
			@Autowired ConstructionManagementService managementService, @Autowired BuildingService buildingService) {
		this.requestService = requestService;
		this.brigadeService = brigadeService;
		this.managementService = managementService;
		this.buildingService = buildingService;
	}

	@GetMapping
	private String showAllRequests(Model model) {
		model.addAttribute("requests", requestService.findAll());
		return "request/requests-page";
	}

	@GetMapping("/show/{id}")
	private String showRequest(Model model, @PathVariable String id) {
		model.addAttribute("request", requestService.findById(id));
		return "request/show-request-page";
	}

	@GetMapping("/add")
	private String showAddRequest(Model model) {
		model.addAttribute("request", new Request("", "", "", null, false, null, null, null, null, LocalDateTime.now(), LocalDateTime.now()));
		model.addAttribute("brigades", brigadeService.getMainInfo());
		model.addAttribute("managements", managementService.getAllAddresses());
		model.addAttribute("buildings", buildingService.getMainInfo());
		return "request/add-request-page";
	}

	@GetMapping("/edit/{id}")
	private String showEditRequest(Model model, @PathVariable String id) {
		model.addAttribute("request", requestService.findById(id));
		model.addAttribute("brigades", brigadeService.getMainInfo());
		model.addAttribute("managements", managementService.getAllAddresses());
		model.addAttribute("buildings", buildingService.getMainInfo());

		return "request/edit-request-page";
	}

	@PostMapping("/add")
	private String addRequest(Model model, @ModelAttribute Request request, @RequestParam("request_type") String requestType,
			@RequestParam("building_id") String buildingId, @RequestParam("brigade_id") String brigadeId, @RequestParam("management_id") String managementId) {

		RequestType type = null;
		try {
			type = RequestType.valueOf(requestType);
		} catch (IllegalArgumentException ignored) {
		}
		request.setType(type);
		request.setBuilding(buildingService.findById(buildingId));
		request.setConstructionManagement(managementService.findById(managementId));
		request.setBrigadeThatAsked(brigadeService.findById(brigadeId));

		requestService.add(request);
		return "redirect:/requests";
	}

	@PostMapping("/edit/{id}")
	private String updateRequest(Model model, @ModelAttribute Request request, @PathVariable String id, @RequestParam("request_type") String requestType,
			@RequestParam("building_id") String buildingId, @RequestParam("brigade_id") String brigadeId, @RequestParam("management_id") String managementId) {
		request.setId(id);
		RequestType type = null;
		try {
			type = RequestType.valueOf(requestType);
		} catch (IllegalArgumentException ignored) {
		}
		request.setType(type);
		request.setBuilding(buildingService.findById(buildingId));
		request.setConstructionManagement(managementService.findById(managementId));
		request.setBrigadeThatAsked(brigadeService.findById(brigadeId));

		requestService.update(request);
		return "redirect:/requests";
	}

	@PostMapping("/delete/{id}")
	private String deleteRequest(Model model, @PathVariable String id) {
		requestService.delete(id);
		return "redirect:/requests";
	}

}
