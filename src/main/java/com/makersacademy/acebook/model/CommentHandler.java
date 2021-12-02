package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

public class CommentHandler {


    private String comment;
    private String username;
    private Post post;

    public CommentHandler(){

    }
    public void newComment(HttpServletRequest request, PostRepository repository ){
    post = repository.findById(Long.parseLong(request.getParameter("commentsConditionSubmit"))).get();
    post.showOrHideComments();
    repository.save(post);
    username = getUsername();
    comment = request.getParameter("commentSubmit");
    }

    public String getComment(){
        return this.comment;
    }

    public String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        return username;
    }

    public Long getId(){
        return post.getId();
    }
}
