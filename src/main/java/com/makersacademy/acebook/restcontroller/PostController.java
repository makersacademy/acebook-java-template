package com.makersacademy.acebook.restcontroller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/postservice")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/save")
    public List<Post> submitPost(@RequestBody Post body){
        List<Post> result=postService.submitPostToDB(body);
        return result;
    }

    @GetMapping("/getpost")
    public List<Post> retrieveAllPost(){
        List<Post> result=postService.retrievePostFromDB();
        return result;
    }

    @DeleteMapping("/delete")
    public List<Post> deleteParticularPost(@PathVariable("postID") UUID postID){
        List<Post> result= postService.deletePostFromDB(postID);
        return result;
    }
}
