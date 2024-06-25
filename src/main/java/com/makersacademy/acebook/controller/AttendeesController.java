package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Attendee;
import com.makersacademy.acebook.service.AttendeesService;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AttendeesController {

    @Autowired
    private AttendeesService attendeesService;

    @PostMapping("/events/attend/{eventId}")
    public RedirectView createAttendee(@PathVariable Long eventId, Authentication authentication, Attendee attendee) {
        attendeesService.save(attendee, eventId, authentication);
        return new RedirectView("/");
    }
}
