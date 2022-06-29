package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.PostRepository;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LikesController {

  @Autowired
  LikeRepository likeRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  PostRepository postRepository;

  // Password authentication
  Boolean matchingPassword = true;

  @GetMapping("/likes/{postId}")
  public RedirectView likePost(@PathVariable Long postId, Principal principal) {
    User user = userRepository.findByUsername(principal.getName()).get(0);
    Post post = postRepository.findAllById(postId).get(0);

    List<Like> likes = likeRepository.findByUserIdAndPostId(user.getId(), postId);

    if (likes.isEmpty()) {
      Like like = new Like(user, post);
      post.setLikesCount(likeRepository.countByPostId(postId) + 1);
      likeRepository.save(like);
    } else {
      post.setLikesCount(likeRepository.countByPostId(postId) - 1);
      likeRepository.delete(likes.get(0));
    }
    return new RedirectView("/posts");
  }

}
