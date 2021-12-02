package com.makersacademy.acebook.restcontroller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/likeservice")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping("/getlikes")
    public List<Like> retrieveAllLikes(){
        List<Like> result=likeService.retrieveLikeFromDB();
        return result;
    }
}
