package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Movie;
import com.makersacademy.acebook.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller


public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/search")
    public String searchMovie(Model model) {
        try {
            List<Movie> movies = movieService.searchMovie("Incep").get();
            model.addAttribute("movies", movies);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            model.addAttribute("movies", List.of());
        }
        return "index";
    }
}
