package com.makersacademy.acebook.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Bean
    public AmazonS3 s3() {
      String awsAccessKeyId = System.getenv("AWS_ACCESS_KEY_ID");
      System.out.println(awsAccessKeyId);
      String awsSecretAccessKey = System.getenv("AWS_SECRET_ACCESS_KEY");
      System.out.println(awsSecretAccessKey);
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("eu-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withPathStyleAccessEnabled(true)
                .build();
    }
}