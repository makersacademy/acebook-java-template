package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/post/addComment")
    public RedirectView createComment(@ModelAttribute Comment newComment) {

        commentRepository.save(newComment);
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println("HEREEEEEEEEEEEEE");
        System.out.println(newComment);

//        set user_id:
//        Optional<User> currentUser = userRepository.findByUsername(principal.getName());
//        User principalUser = currentUser.orElse(null);
//        newComment.setUserId(principalUser.getId());

//        get visibility:
        System.out.println("PRINT OBJECT OUT AGAIN: ");
        commentRepository.save(newComment);

        return new RedirectView("/posts");

    }
}
