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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@Controller
public class LandingPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String listUsersAndEvents(Model model) {
        List<User> users = userRepository.findAll();
        List<Event> events = StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        model.addAttribute("events", events);
        return "landingpage";
    }
}
