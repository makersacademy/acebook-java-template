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
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName;

    @Autowired
    public PostService(
            @Value("${aws.region}") String region,
            @Value("${aws.accessKeyId}") String accessKeyId,
            @Value("${aws.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.bucket.name}") String bucketName) {

        this.bucketName = bucketName;

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);

        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        this.s3Presigner = S3Presigner.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();
    }


    public String saveProfilePicture(MultipartFile image) throws IOException {
        String filename = "profile_pictures/" + System.currentTimeMillis() + "_" + image.getOriginalFilename();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());
        PutObjectResponse response = s3Client.putObject(putObjectRequest,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(image.getBytes()));
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(r -> r.bucket(bucketName).key(filename))
                .signatureDuration(java.time.Duration.ofDays(7))
                .build();
        return s3Presigner.presignGetObject(getObjectPresignRequest).url().toString();
    }

    @Transactional
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
    public void toggleLike(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Optional<Like> existingLike = likeRepository.findByPostAndUser(post, user);

        if (existingLike.isPresent()) {
            Like like = existingLike.get();
            likeRepository.delete(like);
            post.removeLike(like); // Remove the like from the post
        } else {
            Like like = new Like(user, post);
            likeRepository.save(like);
            post.addLike(like); // Link the like to the post
        }
    }

    public long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}