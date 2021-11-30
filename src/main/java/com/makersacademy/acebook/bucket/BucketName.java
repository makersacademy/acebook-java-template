package com.makersacademy.acebook.bucket;

import lombok.Getter;

import lombok.Setter;
//
//
//@Getter
//@Setter
public enum BucketName {
    PROFILE_IMAGE("acebook-images-javacadabra");

    private final String bucketName;

        BucketName (String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}

