package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final String bucketName;

    @Autowired
    public PostService(Dotenv dotenv) {
        String region = dotenv.get("AWS_REGION");
        String accessKeyId = dotenv.get("AWS_ACCESS_KEY_ID");
        String secretAccessKey = dotenv.get("AWS_SECRET_ACCESS_KEY");
        this.bucketName = dotenv.get("AWS_S3_BUCKET_NAME");

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

    public void savePost(Post post, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String imageUrl = saveImageToS3(image);
            post.setImageUrl(imageUrl);
        }
        postRepository.save(post);
    }

    private String saveImageToS3(MultipartFile image) throws IOException {
        String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();

        // Upload the image to S3
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());

        PutObjectResponse response = s3Client.putObject(putObjectRequest,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(image.getBytes()));

        // Generate a pre-signed URL for the uploaded image
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(r -> r.bucket(bucketName).key(filename))
                .signatureDuration(java.time.Duration.ofDays(7)) // URL expiration time
                .build();

        return s3Presigner.presignGetObject(getObjectPresignRequest).url().toString();
    }

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }
}