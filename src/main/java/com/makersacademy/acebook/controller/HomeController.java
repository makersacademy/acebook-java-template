package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.HomePage;
import com.makersacademy.acebook.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String homePage() {
		return "homePage";
	}

	public RedirectView index() {
		return new RedirectView("/posts");  // remove the redirection? --> No, on homePage should 2 buttons redirect to either sign-in / sign-up
	}
}
