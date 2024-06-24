package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.Optional;


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

    @GetMapping("/events/details/{eventId}")
    public String showEventDetails(@PathVariable Long eventId, Model model) {
        // Fetch the event details from the repository
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            model.addAttribute("event", event);
            return "events/details";
        } else {
            // Handle the case where the event is not found
            return "redirect:/error";
        }
    }

//    @GetMapping("/")
//    public String userEvents(Model model,
//                             @AuthenticationPrincipal Object principal,
//                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minScheduledDate,
//                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxScheduledDate) {
//        String username;
//        List<Event> events;
//
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else if (principal instanceof OAuth2User) {
//            username = ((OAuth2User) principal).getAttribute("name");
//        } else {
//            username = "User";
//        }
//
//        model.addAttribute("name", username);
//
//        if (minScheduledDate != null && maxScheduledDate != null) {
//            events = eventRepository.findByScheduledDateBetween(minScheduledDate, maxScheduledDate);
//        } else {
//            events = eventRepository.findAllByOrderByScheduledDate();
//        }
//        model.addAttribute("events", events);
//        model.addAttribute("event", new Event());
//        return "events";
//    }
}
