package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@CrossOrigin
@Controller
public class LandingPageController {

    @Autowired
    private UserRepository userRepository;
    private EventRepository eventRepository;

    @GetMapping("/")
    public String listUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "landingpage";
    }

    @GetMapping("/events/new")
    public String addEvent(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "landingpage";
    }
}
