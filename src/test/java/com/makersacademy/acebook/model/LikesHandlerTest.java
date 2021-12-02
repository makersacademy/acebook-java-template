package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class LikesHandlerTest {
    private LocalDateTime testDate = LocalDateTime.now();
    CurrentUser currentUser= new CurrentUser();

    HttpServletRequest request = mock(HttpServletRequest.class);
    PostRepository repository = mock(PostRepository.class);
    LikesRepository likesRepository=mock(LikesRepository.class);
    LikesHandler likesHandler = new LikesHandler(repository, likesRepository,currentUser);

//    @Test
//    public void hasLikes() {
//        Post testPost = new Post();
//        testPost.populate_mock((long) 1, "a", testDate, "yoyo", 0);
//        when(request.getParameter(any())).thenReturn("1");
//
//
//        likesHandler.handleLike(request);
//
//    }
}
