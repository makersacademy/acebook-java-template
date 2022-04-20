package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.HomePage;
import com.makersacademy.acebook.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@GetMapping("/")							// Create a simple "Acebook" page that wellcomes and prompts the user to either sign-in / sign-up
	public String homePage(HomePage model) {
    	User user = model.getUser();
		System.out.println(user.getUsername());
        return "/";
    }
	// return homepage.index.html 

	@RequestMapping(value = "/")
	public RedirectView index() {
		return new RedirectView("/posts");  // remove the redirection? --> No, on homePage should 2 buttons redirect to either sign-in / sign-up
	}
}
