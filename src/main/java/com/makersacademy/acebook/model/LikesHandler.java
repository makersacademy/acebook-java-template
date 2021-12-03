package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LikesHandler {
    private final PostRepository repository;
    private final LikesRepository likesRepository;
    private String username;

    public LikesHandler(PostRepository repository, LikesRepository likesRepository) {
        this.repository = repository;
        this.likesRepository = likesRepository;
    }

    public boolean liked(HttpServletRequest request, RedirectAttributes redirect, String username) {
        this.username = username;
        String parameter = request.getParameter("postId");
        long postId = Long.parseLong(parameter);
        Optional<Post> query = repository.findById(postId);
        Post post = query.get();
        boolean isLikable = isLikable(redirect, post);
        if (isLikable) {
            post.incrementLikes();
            repository.save(post);
            Like like = new Like(post.getUsername(), post.getId());
            likesRepository.save(like);
        }
        return isLikable;
    }

    private boolean isLikable(RedirectAttributes redirect, Post post) {

        System.out.println("pun--->");
        System.out.println(post.getUsername());

        System.out.println("cun--->");
        System.out.println(username);

        boolean isSameUser = post.getUsername().equals(username);
        if (isSameUser) {
            redirect.addFlashAttribute("User cannot like its own posts");
            return false;
        }
//        boolean userHasAlreadyLikedPost = postHasUser(post);
//        if (userHasAlreadyLikedPost) {
//            redirect.addFlashAttribute("User has already liked this posts");
//        }
//        return userHasAlreadyLikedPost;
        return true;
    }

    private Boolean postHasUser(Post post) {
        LikesList likeList = collectIdLikes(post);
        List<String> test = likeList.getList().stream().map(Like::getUsername).collect(Collectors.toList());
        return (test.contains(username));
    }

    private LikesList collectIdLikes(Post post) {
        LikesList likeList = new LikesList();
        Iterable<Like> test = likesRepository.findAll();
        likeList.setList(test);
        likeList.likeArrayList.stream().filter(like ->
                like.getPost_id().equals(post.getId())
        );
        System.out.println("--->");
        System.out.println(likeList);
        printer(likeList);
        return likeList;
    }

    private void printer(LikesList likeList) {
        likeList.likeArrayList.forEach(like ->
                System.out.println("--->${like}")
        );
    }

}