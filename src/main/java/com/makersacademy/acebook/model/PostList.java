package com.makersacademy.acebook.model;


import java.util.ArrayList;


public class PostList {

    public ArrayList<Post> postArrayList = new ArrayList<>();


    public ArrayList<Post> getList() {
        return postArrayList;
    }

    public void setList(Iterable<Post> iterableIn) {
        System.out.println("---------In PostList---------");
        for (Post post : iterableIn) {
            postArrayList.add(post);
        }
    }

}
