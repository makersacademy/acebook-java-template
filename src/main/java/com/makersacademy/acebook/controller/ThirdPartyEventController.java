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


//public class MovieController {
//
//    @Autowired
//    private ThirdPartyEventService movieService;
//
//    @GetMapping("/search")
//    public String searchEvent(Model model) {
//        try {
//            List<ThirdPartyEvent> movies = movieService.searchMovie("Incep").get();
//            model.addAttribute("movies", movies);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//            model.addAttribute("movies", List.of());
//        }
//        return "index";
//    }
//}

public class ThirdPartyEventController {

    @Autowired
    private ThirdPartyEventService thirdPartyEventService;

    @GetMapping("/search")
    public String searchEvent(Model model) {
        try {
            List<ThirdPartyEvent> events = thirdPartyEventService.searchEvent().get();
            model.addAttribute("events", events);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            model.addAttribute("events", List.of());
        }
        return "index";
    }
}
//
//AsyncHttpClient client = new DefaultAsyncHttpClient();
//client.prepare("GET", "https://real-time-events-search.p.rapidapi.com/search-events?query=Newcastle%2C%20UK&start=0")
//	.setHeader("x-rapidapi-key", "330a9e01edmsh3544e9d43e6ff69p17ee8ejsnfee0e591ba76")
//	.setHeader("x-rapidapi-host", "real-time-events-search.p.rapidapi.com")
//	.execute()
//	.toCompletableFuture()
//	.thenAccept(System.out::println)
//	.join();
//
//client.close();