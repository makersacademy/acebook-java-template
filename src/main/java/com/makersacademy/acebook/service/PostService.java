package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.LikeRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import javax.transaction.Transactional;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private S3Service s3Service;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    public void savePost(Post post, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String imageUrl = s3Service.saveImage(image);
            post.setImageUrl(imageUrl);
        }
        postRepository.save(post);
    }
    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        posts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return posts;
    }
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Iterable<Post> getAllPostsFromNewestToOldest(){
        List<Post> posts = (List<Post>) postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        return posts;
    }


    @Transactional
    public Comment addComment(Long postId, String content, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment(content, post, user);
        return commentRepository.save(comment);
    }

    @Transactional
    public void addLike(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if the user has already liked the post
        if (likeRepository.findByPostAndUser(post, user).isEmpty()) {
            Like like = new Like(user, post);
            likeRepository.save(like);
            post.addLike(like); // Link the like to the post
        } else {
            throw new IllegalStateException("User already liked this post");
        }
    }

    @Transactional
    public void removeLike(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Find the existing like and delete it
        Like like = likeRepository.findByPostAndUser(post, user)
                .orElseThrow(() -> new IllegalStateException("User has not liked this post"));
        likeRepository.delete(like);
        post.removeLike(like); // Remove the like from the post
    }

    public long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}