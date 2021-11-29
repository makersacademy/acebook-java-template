package com.makersacademy.acebook.model;

import java.util.ArrayList;
import java.util.Collections;

public class PostList {

    public ArrayList<Post> postArrayList = new ArrayList<>();
    public ArrayList<Post> getList() {
        return postArrayList;
    }

    public void setList(Iterable<Post> iterableIn) {
        for (Post post : iterableIn) {
            postArrayList.add(post);
        }
        sortList();
    }

    public ArrayList<Post> sortList(){
        Collections.reverse(postArrayList);
        return postArrayList;
    }

}
