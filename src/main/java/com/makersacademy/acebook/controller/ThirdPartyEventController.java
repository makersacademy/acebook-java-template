package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Event;
import com.makersacademy.acebook.model.ThirdPartyEvent;
import com.makersacademy.acebook.service.ThirdPartyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller

public class ThirdPartyEventController {

    @Autowired
    private ThirdPartyEventService thirdPartyEventService;

    @GetMapping("/events/third-party-events")
    public String getDefaultTPEvents(Model model) {
        try {
            List<ThirdPartyEvent> thirdPartyEvents = thirdPartyEventService.getDefaultTPEvents().get();
            model.addAttribute("thirdPartyEvents", thirdPartyEvents);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // model.addAttribute("thirdPartyEvents", List.of());
        }
        return "events/third-party-events";
    }

    @GetMapping("/events/third-party-events/search")
    public String searchTPEvents(Model model, @AuthenticationPrincipal Object principal,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date minScheduledDate,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxScheduledDate) {


        try {
            List<ThirdPartyEvent> thirdPartyEvents = thirdPartyEventService.searchTPEvents(minScheduledDate, maxScheduledDate).get();
            model.addAttribute("thirdPartyEvents", thirdPartyEvents);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // model.addAttribute("thirdPartyEvents", List.of());
        }
        return "events/third-party-events";
    }
}
