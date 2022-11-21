package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Profile;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.ProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

import antlr.StringUtils;

import java.io.IOException;

import java.security.Principal;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    @Autowired
    ProfileRepository profileRepository;

    

    //@Bean BCryptPasswordEncoder bCryptPasswordEncoder() {
    //  return new BCryptPasswordEncoder();
    //}

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @GetMapping("/users")
    public String index(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("all_users", users);
        return "users/index";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {

      

        //String username = user.getUsername();
        //String password = user.getPassword();
        //Long id = user.getId();
        //String image = user.getImage();
        //String encrypted_password = bCryptPasswordEncoder.encode(password);
        //user.setPassword(encrypted_password);
        
        //String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        //System.out.println(passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

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

        Optional<Profile> profileInfoOptional = profileRepository.findByUserId(userIdLong);
        if (profileInfoOptional.isPresent()) {
          Profile profileInfo = profileInfoOptional.get();
          model.addAttribute("profile_info", profileInfo);
        }


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
        model.addAttribute("user_repository", userRepository);
        model.addAttribute("friend_repository", friendRepository);
        model.addAttribute("profile_info_optional", profileInfoOptional);

        return "users/profile";
    }

    static public String hash_password(String password) {
      char[] list_of_characters = new char[password.length()];

      for (int i = 0; i < password.length();i++) {
          char current_letter = password.charAt(i);
          int letter = (int) current_letter;
          letter = letter + 1;
          char encrypted_letter = (char) letter;
          list_of_characters[i] = encrypted_letter;

      }

      String result = new String(list_of_characters);
      return result;
  }
}
