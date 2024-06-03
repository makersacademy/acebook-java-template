package com.makersacademy.acebook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    PostRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post, @RequestParam(value = "imageInfoInput", required=false) String imageInfo) throws IOException {
        if (imageInfo != "") {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(imageInfo, Map.class);
            String thumbnail_url = map.get("thumbnail_url");
            post.setImg_url(thumbnail_url);
        }
        repository.save(post);
        return new RedirectView("/posts");
    }

    @PostMapping("/posts-like")
    public RedirectView like(@RequestParam("postId") Long postId) {
        Optional<Post> postOptional = repository.findById(postId);
        Post post = postOptional.get();
        post.incrementLikeCount();
        repository.save(post);
        return new RedirectView("/posts");
    }
}
