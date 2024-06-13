package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;

import com.makersacademy.acebook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

@Controller
public class UsersController {

    public static final String UPLOAD_DIRECTORY = "src/main/resources/static/images";

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired  
    FriendRepository friendRepository;


    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getId(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/login");
    }

    @GetMapping("/users/my-profile")
    public String sessionProfile(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        redirectAttributes.addAttribute("username", auth.getName());
        return "redirect:/users/{username}";
    }

    @GetMapping("/users/{username}")
    public String profile(@PathVariable("username") String username, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User sessionUser = userRepository.findByUsername(auth.getName());
        model.addAttribute("username", username);
        User profileUser = userRepository.findByUsername(username);
        Iterable<Post> posts = postRepository.findByUserIdOrderByCreatedAtDesc(profileUser.getId());
        for (Post post: posts){
            post.setLikes(likeRepository.countByPost(post));
        }
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("sessionUser", sessionUser);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        String friend_status = GetFriendStatus(auth.getName(), username);
        model.addAttribute("friend_status", friend_status);
        return "users/profile";
    }

    @Transactional
    @PostMapping("/users/upload-profile-image")
    public String uploadProfileImage(RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile file) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());

        StringBuilder fileNames = new StringBuilder("/images/");
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        String stringFileName = fileNames.toString();
      
        redirectAttributes.addAttribute("username", auth.getName());
        return "redirect:/users/{username}";

    public String GetFriendStatus(User one, User two){
        User sender = userRepository.findByUsername(one.getUsername());
        User recipient = userRepository.findByUsername(two.getUsername());

        if (sender == null || recipient == null || sender == recipient) return "N/A";

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Sent";
        }
        existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Received";
        }
        return "None";
    }

    public String GetFriendStatus(String one, String two){
        User sender = userRepository.findByUsername(one);
        User recipient = userRepository.findByUsername(two);

        if (sender == null || recipient == null || sender.getId() == recipient.getId()) return "N/A";

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Sent";
        }
        existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Received";
        }
        return "None";
    }
}