package com.makersacademy.acebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    private final PostRepository postRepository;

    public TestController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        return new ModelAndView("/test");
    }

    @PostMapping("/test")
    public ModelAndView handleImageUpload(@RequestParam("imageInfo") String imageInfo) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/test");
        String result = imageInfo;
        modelAndView.addObject("result", result);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(result, Map.class);
        String thumbnail_url = map.get("thumbnail_url");
        modelAndView.addObject("thumbnail_url", thumbnail_url);
        return modelAndView;
    }
}
