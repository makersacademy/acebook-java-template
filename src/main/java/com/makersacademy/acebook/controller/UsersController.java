package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Profile;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Reply;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.ProfileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.ReplyRepository;
import com.makersacademy.acebook.repository.LikeRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class UsersController {

    @Autowired
    UserRepository urepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ReplyRepository reply_repo;
    @Autowired
    PostRepository prepository;
    @Autowired
    LikeRepository lrepository;



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

    @GetMapping("/users/me")
    public RedirectView me(Principal principal) {
      String currentUserName = principal.getName();
      return new RedirectView(String.format("/users/%s",currentUserName));
    }

    @GetMapping("/users/myphotoraw")
    void manual(Principal principal,HttpServletResponse response) throws IOException {
      String currentUserName = principal.getName();
      Optional<User> currentUser = urepository.findByUsername(currentUserName);
      User me = currentUser.get();
      String photoUrl = me.getImage();
      response.getWriter().println(photoUrl);
    }


    @GetMapping("/users")
    public String index(Model model, Principal principal) {
        if (principal == null) {
          return "redirect:/login";
        }
        Iterable<User> users = urepository.findAll();
        Iterable<Friend> friends = friendRepository.findAll();
        int num_of_friends = 0;
        for(Friend f: friends){
          if (f.getConfirmed() == 1){
            num_of_friends = num_of_friends + 1;

          }
        }
        model.addAttribute("numOfFriends", num_of_friends);
        model.addAttribute("all_users", users);
        return "users/index";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, Principal principal) {
        user.setUsername(HtmlUtils.htmlEscape(user.getUsername()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        urepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @RequestMapping(value="/users/{username}")
    public String profile(Model model, @PathVariable("username") String username, Principal principal){
        if (principal == null) {
          return "redirect:/login";
        }
        String currentUserName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(currentUserName);
        User me = currentUser.get();
        Long myUserIdLong = me.getId();
        Integer myUserId = myUserIdLong.intValue();

        Optional<User> profileUser = urepository.findByUsername(username);
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

        // post history

        Iterable<Post> posts = prepository.findAllByUserId(userId);

        List<Post> postsToList = new ArrayList<>();

        // get all likes for each post
        HashMap<Long, Integer> allLikes = new HashMap<Long, Integer>();
        // save which ones the user has liked
        HashMap<Long, Boolean> postsUserHasLiked = new HashMap<Long, Boolean>();
        for(Post p: posts) {
            allLikes.put(
                p.getId(),
                lrepository.findAllByPost(p.getId()).size()
            );
            postsUserHasLiked.put(
                p.getId(),
                lrepository.hasLiked(p.getId(), userIdLong)
            );
            postsToList.add(p);
        }
        System.out.println(allLikes);

        //reversing posts to get newest first
        int sizeOfList = postsToList.size();
        List<Post> reversedPosts = new ArrayList<>();
        for (int i = 1; i<=sizeOfList;i++) {
            reversedPosts.add(postsToList.get(sizeOfList-i));
        }


        //Replies stuff here
        Iterable<Reply> replies = reply_repo.findAll();

        model.addAttribute("posts", reversedPosts);
        model.addAttribute("post", new Post());
        model.addAttribute("replies", replies);
        model.addAttribute("reply", new Reply());
        model.addAttribute("like", new Like());
        model.addAttribute("allLikes", allLikes);
        model.addAttribute("postsUserHasLiked", postsUserHasLiked);

        model.addAttribute("profile_id", userId);
        model.addAttribute("profile_username", username);
        model.addAttribute("my_id", myUserId);
        model.addAttribute("my_username", currentUserName);
        model.addAttribute("my_friend_requests", friendRequestsToList);
        model.addAttribute("my_friends", myFriends);
        model.addAttribute("user_repository", urepository);
        model.addAttribute("friend_repository", friendRepository);
        model.addAttribute("profile_info_optional", profileInfoOptional);
        model.addAttribute("profile_user", user);

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
