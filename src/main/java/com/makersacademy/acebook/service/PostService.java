package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import java.io.IOException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private S3Service s3Service;

    public void savePost(Post post, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String imageUrl = s3Service.saveImage(image);
            post.setImageUrl(imageUrl);
        }
        postRepository.save(post);
    }
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
}