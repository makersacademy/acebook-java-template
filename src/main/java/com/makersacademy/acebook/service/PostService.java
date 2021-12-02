package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> submitPostToDB(Post postData){
        postData.setStamp(LocalDateTime.now());
        postRepository.save(postData);
        List<Post> result=retrievePostFromDB();
        return result;
    }
    public List<Post> retrievePostFromDB(){
        List<Post> result=postRepository.findAll(Sort.by(Sort.Direction.DESC,"stamp"));
        return result;
    }
    public List<Post> deletePostFromDB(UUID postID){
        postRepository.deleteById(postID);
        ArrayList<Post> result=postRepository.findAll();
        return result;
    }

}
