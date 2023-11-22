package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class LikeController {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/handleLike/{id}")
    public String handleLike(@PathVariable Long id, Principal principal) {
        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
        User principalUser = currentUser.orElse(null);
        assert principalUser != null;

        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(principalUser.getId(), id);

        if (existingLike.isPresent()) {
            likeRepository.updateLikeStatusByUserIdAndPostId(principalUser.getId(), id);
        } else {
            Like newLike = new Like(true, principalUser.getId(), id);
            likeRepository.save(newLike);
        }

        return "redirect:/posts";
    }
}
