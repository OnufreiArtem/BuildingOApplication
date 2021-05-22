package com.onufrei.buildingo.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home controller for entire application
 *
 * @author Artem Onufrei
 * @version 1
 * @since 16.05.2021
 */

@Controller
public class HomeController {

	@GetMapping
	public String showHomepage(Model model) {
		return "homepage";
	}

	@GetMapping("/titlePage")
	public String showTitlePage(Model model) {
		return "titlepage";
	}

}
