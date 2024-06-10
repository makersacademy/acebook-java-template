package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;



    @Data
    @Entity
    @Table(name = "COMMENTS")
    public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String text;

        @ManyToOne
        @JoinColumn(name = "post_id", nullable = false)
        private Post post;

        public Comment() {
        }

        public Comment(String text, Post post) {
            this.text = text;
            this.post = post;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Post getPost() {
            return this.post;
        }

        public void setPost(Post post) {
            this.post = post;
        }
    }

