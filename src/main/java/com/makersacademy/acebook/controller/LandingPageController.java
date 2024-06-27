package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Attendee;
import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AttendeesRepository;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.AttendeesService;
import com.makersacademy.acebook.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
public class LandingPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SearchService searchService;

    @Autowired
    private AttendeesService attendeesService;

    @Autowired
    private AttendeesRepository attendeesRepository;

    @RequestMapping(value = "/")
    public RedirectView index() {
        return new RedirectView("/landingpage");
    }

    @GetMapping("/")
    public String userEvents(Model model,
                             @AuthenticationPrincipal Object principal,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minScheduledDate,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxScheduledDate,
                             @RequestParam(required = false) String keyword) {
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof OAuth2User) {
            username = ((OAuth2User) principal).getAttribute("name");
        } else {
            username = "User";
        }

        model.addAttribute("name", username);

        List<Event> events;
        if (keyword != null && !keyword.isEmpty()) {
            events = searchService.searchEvents(keyword);
        } else if (minScheduledDate != null && maxScheduledDate != null) {
            events = eventRepository.findByScheduledDateBetween(minScheduledDate, maxScheduledDate);
        } else {
            events = eventRepository.findAllByOrderByScheduledDateDesc();
        }

        User user = userRepository.findByUsername(username);
        for (Event event: events) {
            event.setAttendees(attendeesRepository.countByEvent(event));
            List<Attendee> userAttendees = attendeesRepository.findByUserAndEvent(user, event);
            event.setUserAttending(!userAttendees.isEmpty());
        }

        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());

        return "landingpage";
    }
}