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

//@Autowired
//private Environment env;
//
//    @Value"AKIAQFEJAPBAJQIEBPM3")
//    private String accessKey;
//    @Value("${secretKey}")
//    private String secretKey;
//    @Value("${region}")
//    private String region;


    @Bean
    public AmazonS3 s3client() {


        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAQFEJAPBAJQIEBPM3",
                "dgwd/iIJif62hqgdtMd9n3hxf6Ld7UOAYa8tUZFg"
        );
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName("eu-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return s3Client;
    }
}
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

//    @Bean
//    public static En propertyConfigInDev() {
//        return new PropertySourcesPlaceholderConfigurer();
//}

//    @Bean
//public AmazonS3 s3() {
//    AWSCredentials awsCredentials = new BasicAWSCredentials(
//            "AKIAQFEJAPBAJQIEBPM3",
//            "dgwd/iIJif62hqgdtMd9n3hxf6Ld7UOAYa8tUZFg"
//    );
//    return AmazonS3ClientBuilder
//            .standard()
//            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//            .build();
//}





