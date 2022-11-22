package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Reply;
import com.makersacademy.acebook.repository.ReplyRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import com.makersacademy.acebook.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.HtmlUtils;



@Controller
public class RepliesController {
    @Autowired
    ReplyRepository repository;
    @Autowired
    UserRepository urepository;

    @PostMapping("/posts/reply")
    public RedirectView createReply(@ModelAttribute Reply reply, Principal principal) {
        Date date = new Date();
        String userName = principal.getName();
        Optional<User> currentUser = urepository.findByUsername(userName);
        User user = currentUser.get();
        Long userIdLong = user.getId();
        Integer userId = userIdLong.intValue();

        reply.setContent(HtmlUtils.htmlEscape(reply.getContent()));
        reply.setTime_posted(date);
        reply.setUser_id(userId);
        reply.setUsername(userName);

        repository.save(reply);

        return new RedirectView("/posts");
    }
    
}
