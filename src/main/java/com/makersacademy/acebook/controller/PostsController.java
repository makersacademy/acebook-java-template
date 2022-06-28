package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostsController {

  @Autowired
  PostRepository postRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  FriendRepository friendRepository;

  @GetMapping("/posts")
  public String index(Model model, Principal principal, @ModelAttribute Post formPost) {
    User user = userRepository.findByUsername(principal.getName()).get(0);
    List<Post> allPosts = postRepository.findAllByOrderByTimeDesc();
    List<Friend> friends = friendRepository.findByUserIdAndAccepted(user.getId(), true);
    List<Long> friendsIds = new ArrayList<Long>();
    List<Post> friendPosts = new ArrayList<Post>();

    friendsIds.add(user.getId());
    for (Friend friend : friends) {
      friendsIds.add(friend.getFriend().getId());
    }

    for (Post post : allPosts) {
      if (friendsIds.contains(post.getUser().getId())) {
        friendPosts.add(post);
      }
    }

    model.addAttribute("posts", friendPosts);
    return "posts/feed";
  }

  @PostMapping("/posts")
  public RedirectView create(@ModelAttribute Post post, Principal principal) {
    User user = userRepository.findByUsername(principal.getName()).get(0);
    post.setUser(user);
    postRepository.save(post);
    return new RedirectView("/posts");
  }
}
