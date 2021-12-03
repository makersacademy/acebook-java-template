package com.makersacademy.acebook.model;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        ArrayList<Like> arrayList = olikeList.likeArrayList;
        List<Like> test1 = arrayList.stream().filter(like ->
                like.getPost_id().equals(post.getId())
        ).toList();
        List<String> test2 = test1.stream().map(Like::getUsername).toList();

        AtomicBoolean match = new AtomicBoolean(false);
        test2.forEach(user -> {
                    if (user.equals(username)) {
                        match.set(true);
                    }
                }
        );
        return match.get();
    }

}
