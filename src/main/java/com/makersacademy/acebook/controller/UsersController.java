package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.*;
import com.makersacademy.acebook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.*;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, @RequestParam("fileData") String fileData) {
        user.setImageUrl(fileData);

        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/users/{id}")
    public ModelAndView show(@PathVariable Long id, Principal principal, Model model) {
        Optional<User> pageUser = userRepository.findById(id);
        User user = pageUser.orElse(null);

        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        ModelAndView modelAndView = new ModelAndView("/users/show");
        modelAndView.addObject("currentUser", principalUser);
        modelAndView.addObject("user", user);

        Iterable<Friend> userRequests = friendRepository.findByRequesterIdOrReceiverId(principalUser.getId(), principalUser.getId());

        for (Friend friend : userRequests) {
            if (Objects.equals(friend.getReceiverId(), id) || Objects.equals(friend.getRequesterId(), id)) {
                modelAndView.addObject("userRequest", friend);
                break;
            } else {
                modelAndView.addObject("userRequest", null);
            }
        }

        if (principal.getName().equals(Objects.requireNonNull(user).getUsername())) {
            modelAndView.addObject("isCurrentUser", true);
        } else {
            modelAndView.addObject("isCurrentUser", false);
        }

        Iterable<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        ArrayList<HashMap<String, Object>> postsAndPosters = new ArrayList<>();

        for (Post post : posts) {
            HashMap<String, Object> entry = new HashMap<>();

            Optional<User> optionalUser = userRepository.findById(post.getUserId());
            User poster = optionalUser.orElse(null);

            List<Like> postLikes = likeRepository.countByLikeStatusAndPostId(true, post.getId());

            if (Objects.equals(post.getUserId(), id)) {
                entry.put("post", post);
                entry.put("user", poster);
                entry.put("likes", postLikes);

                postsAndPosters.add(entry);
            }
        }

        model.addAttribute("profilePicture", principalUser.getImageUrl());
        model.addAttribute("postsAndPosters", postsAndPosters);

        return modelAndView;
    }

    @PostMapping("/handleNewProfile/{id}")
    public String handleNewProfile(@RequestParam String fileData, @PathVariable Long id) {
        if (fileData != null && !fileData.isEmpty()) {
            userRepository.updateImageUrlByUserId(id, fileData);
        }

        return "redirect:/users/" + id;
    }
}
