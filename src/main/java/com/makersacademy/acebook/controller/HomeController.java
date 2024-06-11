package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "home"; // Ensure this matches the name of your Thymeleaf template
	}
}
