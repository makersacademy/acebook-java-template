package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.ThirdPartyEvent;
import com.makersacademy.acebook.service.ThirdPartyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller

public class ThirdPartyEventController {

    @Autowired
    private ThirdPartyEventService thirdPartyEventService;

    @GetMapping("/events/third-party-events")
    public String searchEvent(Model model) {
        try {
            List<ThirdPartyEvent> thirdPartyEvents = thirdPartyEventService.searchEvent().get();
            model.addAttribute("thirdPartyEvents", thirdPartyEvents);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // model.addAttribute("thirdPartyEvents", List.of());
        }
        return "events/third-party-events";
    }
}
