package com.makersacademy.acebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.FriendshipRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.services.FriendsService;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class MessagesController {

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  LikeRepository likerepository;

  @Autowired
  FriendsService friendsService;

  @Autowired
  CommentRepository comrepository;

  //
  @GetMapping("/messages")
  public String messages(Model model, HttpSession session) {

    // Get (session) user ID
    Long userID = Long.parseLong(session.getAttribute("id").toString());
    model.addAttribute("id", userID);
    User currentUser = userRepository.findById(userID).get();
    model.addAttribute("currentuser", currentUser);

    // Get user object
    model.addAttribute("friend", new User());
    // Get friends
    Iterable<com.makersacademy.acebook.model.User> friends = userRepository.getFriends(userID);
    // Count friends, only return if not 0. Send count to TL for "You haven't made
    // any friends yet." message conditional
    int friendCount = 0;
    // (Need user for enhanced for loop, even though it "is not used")
    for (User user : friends) {
      friendCount++;
    }
    model.addAttribute("friendcount", friendCount);
    if (friendCount > 0) {
      model.addAttribute("friends", friends);
    }
    return "messages/messages";
  }

  public PostRepository getPostRepository() {
    return postRepository;
  }

  public void setPostRepository(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public UserRepository getUserRepository() {
    return userRepository;
  }

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LikeRepository getLikerepository() {
    return likerepository;
  }

  public void setLikerepository(LikeRepository likerepository) {
    this.likerepository = likerepository;
  }

  public FriendsService getFriendsService() {
    return friendsService;
  }

  public void setFriendsService(FriendsService friendsService) {
    this.friendsService = friendsService;
  }

  public CommentRepository getComrepository() {
    return comrepository;
  }

  public void setComrepository(CommentRepository comrepository) {
    this.comrepository = comrepository;
  }
}
