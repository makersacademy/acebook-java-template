package com.makersacademy.acebook.service;

import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.makersacademy.acebook.model.Comment;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import com.makersacademy.acebook.model.Post;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void savePostToDB(MultipartFile file, String content){
        Post p = new Post();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        p.setContent(content);
        try {
            p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch(IOException e){
            e.printStackTrace();
        }

        postRepository.save(p);
    }
}
