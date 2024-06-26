package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentsRepository;
import com.makersacademy.acebook.repository.EventRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.service.CommentService;
import com.makersacademy.acebook.service.EventService;
import com.makersacademy.acebook.service.S3Service;
import com.makersacademy.acebook.service.ThirdPartyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

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

    @Autowired
    private EventService eventService;

    @Autowired
    private S3Service s3Service;

    @GetMapping("/events/new")
    public String addEvent(Model model) {
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("event", new Event());
        return "events/new";
    }

    @PostMapping("/events/new")
    public RedirectView create(@ModelAttribute Event event,
                               Authentication authentication,
                               @RequestParam("image") MultipartFile image) {
        User currentUser = userRepository.findByUsername(authentication.getName());
        event.setCreatedAt(new Date());
        event.setUser(currentUser);
        try {
            eventService.savePost(event, image);
        } catch (IOException e) {
            e.printStackTrace();
            return new RedirectView("/");
        }

        eventRepository.save(event);
        return new RedirectView("/");
    }

    @GetMapping("/events/details/{eventId}")
    public String showEventDetails(@PathVariable Long eventId, Model model) {

        // Fetch comments for event
        Iterable<Comment> comments = commentsRepository.findByEventIdOrderByCreatedAtDesc(eventId);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());

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

    @PostMapping("/events/details/{eventId}/comments/new")
    public RedirectView createComment(@PathVariable Long eventId, Comment comment, Authentication authentication) {
        commentService.save(comment, eventId, authentication);
        return new RedirectView("/events/details/{eventId}");
    }
}
