package com.makersacademy.acebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping("/")
	public String homePage() {
		return "homePage";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
/*
	@RequestMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/posts");
	}
*/
}
