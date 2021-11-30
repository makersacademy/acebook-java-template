package com.makersacademy.acebook.model;

import static java.lang.Boolean.TRUE;

public class GetPostId {
    Long idStore;

    public Long postId(Long id) {
        idStore = id;
        return id;
    }

    public Long postId() {
        return idStore;
    }
}
