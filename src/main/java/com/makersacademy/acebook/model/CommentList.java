package com.makersacademy.acebook.model;

import java.util.ArrayList;
import java.util.Collections;

public class CommentList {

    public ArrayList<Comment> commentList = new ArrayList<>();
    public ArrayList<Comment> getList() {
        return commentList;
    }

    public void setList(Iterable<Comment> iterableIn) {
        for (Comment comment : iterableIn) {
            commentList.add(comment);
        }
        sortList();
    }

    public ArrayList<Comment> sortList(){
        Collections.reverse(commentList);
        return commentList;
    }
}
