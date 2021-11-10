package com.makersacademy.acebook.service;

import lombok.AllArgsConstructor;
import java.io.InputStream;
import java.net.URL;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Service;
import com.makersacademy.acebook.config.AmazonConfig;

@AllArgsConstructor
@Service
public class FileStore {
    private final AmazonS3 amazonS3 = new AmazonConfig().s3();

    public void upload(String path,
                       String fileName,
                       InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        try {
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public URL getUrl(String path, String key) {
        // System.out.println(amazonS3.getUrl(path, key));
        return amazonS3.getUrl(path, key);
    }

}