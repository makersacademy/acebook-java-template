package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/events/new")
    public String addEvent(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "events/new";
    }

    @PostMapping("/events/new")
    public RedirectView create(@ModelAttribute Event event, Authentication authentication) {
        User currentUser = userRepository.findByUsername(authentication.getName());
        event.setCreatedAt(new Date());
        event.setUser(currentUser);
        eventRepository.save(event);
        return new RedirectView("/home");
    }

    @GetMapping("/events/users")
    public String userEvents(Model model, @AuthenticationPrincipal Object principal) {
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof OAuth2User) {
            username = ((OAuth2User) principal).getAttribute("name");
        } else {
            username = "User";
        }

        model.addAttribute("name", username);

        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "home";
    }
}
