package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class LikesController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @PatchMapping("/posts/{id}/likes")
    public RedirectView updateLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Post> maybePost = postRepository.findById(id);
        Post post = null;
        if (maybePost.isPresent()) {
            post = maybePost.get();
        }
        // fetch signed-in user's record from DB
        Optional<User> maybeUser = userRepository.findByUsername(userDetails.getUsername());
        User user = null;
        if (maybeUser.isPresent()) {
            user = maybeUser.get();
        }
        // if both exist, we can interact with them
        if (post != null && user != null) {
            List<Long> likes = post.getLikes();
            Long userId = user.getId();
            // update likes on post only if user has not liked post before
            if (!likes.contains(userId)) {
                likes.add(userId);
                post.setLikes(likes);
                postRepository.save(post);
            }
        }
        return new RedirectView("/posts");
    }
}
