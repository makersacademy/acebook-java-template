package com.makersacademy.acebook.model;

import java.util.ArrayList;

public class LikesList {

    public ArrayList<Like> likeArrayList = new ArrayList<>();

    public ArrayList<Like> getList() {
        return likeArrayList;
    }

    public void setList(Iterable<Like> iterableIn) {
        for (Like like : iterableIn) {
            likeArrayList.add(like);
        }
    }

}
