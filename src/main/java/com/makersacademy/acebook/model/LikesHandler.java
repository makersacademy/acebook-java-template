package com.makersacademy.acebook.model;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LikesHandler {
    private String username;

    public boolean liked(Iterable<Like> iLikesList, RedirectAttributes redirect, String username, Post post) {
        this.username = username;
        boolean isLikable = isLikable(redirect, post, iLikesList);
        return isLikable;
    }

    private boolean isLikable(RedirectAttributes redirect, Post post, Iterable<Like> iLikeList) {
        boolean isSameUser = post.getUsername().equals(username);
        if (isSameUser) {
            redirect.addFlashAttribute("User cannot like its own posts");
            return false;
        }
        boolean userHasAlreadyLikedPost = postHasUser(post, iLikeList);
        if (userHasAlreadyLikedPost) {
            redirect.addFlashAttribute("User has already liked this posts");
        }
        return !userHasAlreadyLikedPost;
    }

    private Boolean postHasUser(Post post, Iterable<Like> iLikeList) {

        LikesList olikeList = new LikesList();
        olikeList.setList(iLikeList);
        System.out.println("olike----> ");
        System.out.println(olikeList);

        ArrayList<Like> arrayList = olikeList.likeArrayList;
        List<Like> test1 = arrayList.stream().filter(like ->
                like.getPost_id().equals(post.getId())
        ).toList();
        System.out.println("test1----> ");
        printerListLike(test1);


        List<String> test2 = test1.stream().map(Like::getUsername).toList();

        System.out.println("test2----> ");
        printerList(test2);


        boolean test3 = test2.stream().anyMatch(username ->
                post.getUsername().equals(username));

        System.out.println("test3----> ");
        System.out.println(test3);


        return test3;

    }

    private void printerListLike(List<Like> likeList) {
        likeList.forEach(like -> {
            System.out.println("list<Like>--->");
            System.out.println(like);
        });
    }

    private void printer(LikesList likeList) {
        likeList.likeArrayList.forEach(like -> {
            System.out.println("like--->");
            System.out.println(like);
        });
    }

    private void printerI(Iterable<Like> likeList) {
        likeList.forEach(like ->
        {
            System.out.println("Iterable<Like>--->");
            System.out.println(like);
        });
    }

    private void printerL(Stream<Like> likeList) {
        likeList.forEach(like -> {
            System.out.println("Stream<Like>--->");
            System.out.println(like);
        });
    }

    private void printerS(Stream<String> likeList) {
        likeList.forEach(like -> {
            System.out.println("Stream<String>--->");
            System.out.println(like);
        });
    }

    private void printerList(List<String> likeList) {
        likeList.forEach(like -> {
            System.out.println("List<String>--->");
            System.out.println(like);
        });
    }

}
