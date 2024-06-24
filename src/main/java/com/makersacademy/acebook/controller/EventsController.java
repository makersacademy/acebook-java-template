package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.CommentsRepository;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.CommentService;
import com.makersacademy.acebook.service.ThirdPartyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Controller
public class EventsController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThirdPartyEventService thirdPartyEventService;


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

    @GetMapping("/events/{event_id}")
    public String viewSingleEvent(@PathVariable Long event_id, Model model) {
        Event event = eventRepository.findById(event_id).orElseThrow(() -> new IllegalArgumentException("Invalid eventId:" + event_id));
        Iterable<Comment> comments = commentsRepository.findByEventIdOrderByCreatedAtDesc(event_id);
        model.addAttribute("event", event);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());

        return "events/eventinfo.html";
    }

    @PostMapping("/events/{event_id}/comments/new")
    public RedirectView createComment(@PathVariable Long event_id, Comment comment, Authentication authentication) {
        commentService.save(comment, event_id, authentication);
        return new RedirectView("/events/{event_id}");
    }
}
