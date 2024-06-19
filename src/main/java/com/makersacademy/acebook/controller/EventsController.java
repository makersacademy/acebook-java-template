package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public String index(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "events/index";
    }

    @PostMapping("/events")
    public RedirectView create(@ModelAttribute Event event) {
        eventRepository.save(event);
        return new RedirectView("/events");
    }
}
