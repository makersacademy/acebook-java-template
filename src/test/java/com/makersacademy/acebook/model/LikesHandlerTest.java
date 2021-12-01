package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.PostRepository;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

Post TEST
public class LikesHandlerTest {
    private PostRepository repository= mock(PostRepository.class);
    LikesHandler likesHandler= new LikesHandler(repository);

    @Test
    public void hasLikes(){
        HttpServletRequest request=mock(HttpServletRequest.class);
        when(repository.findAllById(any())).thenReturn()


        likesHandler.handleLike(request);

    }
}
