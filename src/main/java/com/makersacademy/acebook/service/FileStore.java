package com.makersacademy.acebook.service;

import lombok.AllArgsConstructor;
import java.io.InputStream;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.IOUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import java.io.IOException;
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

    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

}