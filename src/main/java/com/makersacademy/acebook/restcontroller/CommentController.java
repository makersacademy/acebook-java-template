package com.makersacademy.acebook.restcontroller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commentservice")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/getcomments")
    public List<Comment> retrieveAllComment(){
        List<Comment> result=commentService.retrieveCommentFromDB();
        return result;
    }
}
