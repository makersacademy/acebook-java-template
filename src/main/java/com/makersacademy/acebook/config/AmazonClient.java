package com.makersacademy.acebook.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Value;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
public class AmazonClient {

    @Bean
    public AmazonS3 s3client() {


        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAQFEJAPBAOABMRTW5",
                "+THzy4D1a/YJDZPXxtK1/88/ab/y3ik39YT6FF+g"
        );
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("eu-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withPathStyleAccessEnabled(true)
                .build();

        return s3Client;
    }
}




