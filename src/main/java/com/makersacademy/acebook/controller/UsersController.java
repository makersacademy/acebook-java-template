package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendRepository;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.ArrayList;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    // @GetMapping("/users/")
    // public String profile(Model model) {
    //     model.addAttribute("user", new User());
    //     return "users/new";
    // }

    @RequestMapping(value="/users/{username}")
    public String profile(Model model, @PathVariable("username") String username, Principal principal){
        String currentUserName = principal.getName();
        Optional<User> currentUser = userRepository.findByUsername(currentUserName);
        User me = currentUser.get();
        Long myUserIdLong = me.getId();
        Integer myUserId = myUserIdLong.intValue();

        Optional<User> profileUser = userRepository.findByUsername(username);
        User user = profileUser.get();
        Long userIdLong = user.getId();
        Integer userId = userIdLong.intValue();

        List<Friend> friendRequestsToList = new ArrayList<>();
        List<Friend> myFriends = new ArrayList<>();
        Iterable<Friend> all_friend_requests = friendRepository.findAll();
        for(Friend f: all_friend_requests){
            if(f.getToUser()==myUserId || f.getFromUser()==myUserId){
                if(f.getConfirmed()!=1){
                    // is a request
                    if(f.getFromUser()!=myUserId) {
                      friendRequestsToList.add(f);
                    }
                }else{
                    // is confirmed as a friend
                    myFriends.add(f);
                }
            }
        }

        model.addAttribute("profile_id", userId);
        model.addAttribute("profile_username", username);
        model.addAttribute("my_id", myUserId);
        model.addAttribute("my_username", currentUserName);
        model.addAttribute("my_friend_requests", friendRequestsToList);
        model.addAttribute("my_friends", myFriends);
        model.addAttribute("user_repository",userRepository);
        model.addAttribute("friend_repository",friendRepository);

        return "users/profile";
    }
}
