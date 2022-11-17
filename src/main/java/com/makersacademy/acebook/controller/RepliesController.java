package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.Reply;
import com.makersacademy.acebook.repository.ReplyRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class RepliesController {
    @Autowired
    ReplyRepository repository;

    @PostMapping("/posts/reply")
    public RedirectView createReply(@ModelAttribute Reply reply) {
        Date date = new Date();
        reply.setTime_posted(date);
        repository.save(reply);

        return new RedirectView("/posts");
    }
    
}
