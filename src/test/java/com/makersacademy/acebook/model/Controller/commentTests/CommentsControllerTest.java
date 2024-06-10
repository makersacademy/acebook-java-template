package com.makersacademy.acebook.model.Controller.commentTests;

import com.makersacademy.acebook.controller.CommentsController;
import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.repository.CommentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentsControllerTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentsController commentsController;

    @Before
    public void setUp() {
        // Initialize any necessary dependencies or mocks
    }

    @Test
    public void createComment_whenCommentRepositoryIsNotNull_shouldSaveCommentAndRedirect() {
        // Arrange
        String postId = "123";
        String commentText = "Test comment";
        Comment comment = new Comment();
        comment.setText(commentText);

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Act
        RedirectView redirectView = commentsController.createComment(postId, commentText);

        // Assert
        assertNotNull(redirectView);
        assertEquals("/posts/" + postId, redirectView.getUrl());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    public void createComment_whenCommentRepositoryIsNull_shouldReturnError() {
        // Arrange
        String postId = "123";
        String commentText = "Test comment";
        Comment comment = new Comment();
        comment.setText(commentText);

        // Mock the CommentRepository to return null
        when(commentRepository).thenReturn(null);

        // Act
        RedirectView redirectView = commentsController.createComment(postId, commentText);

        // Assert
        assertNotNull(redirectView);
        assertEquals("/error", redirectView.getUrl()); // Assuming you have an error page at "/error"
    }

    @Test
    public void createComment_whenCommentRepositoryThrowsException_shouldReturnError() {
        // Arrange
        String postId = "123";
        String commentText = "Test comment";
        Comment comment = new Comment();
        comment.setText(commentText);

        // Mock the CommentRepository to throw an exception
        when(commentRepository.save(any(Comment.class))).thenThrow(new RuntimeException("Repository error"));

        // Act
        RedirectView redirectView = commentsController.createComment(postId, commentText);

        // Assert
        assertNotNull(redirectView);
        assertEquals("/error", redirectView.getUrl()); // Assuming you have an error page at "/error"
    }

    @Test
    public void createComment_whenCommentTextIsEmpty_shouldReturnError() {
        // Arrange
        String postId = "123";
        String commentText = "";
        Comment comment = new Comment();
        comment.setText(commentText);

        // Act
        RedirectView redirectView = commentsController.createComment(postId, commentText);

        // Assert
        assertNotNull(redirectView);
        assertEquals("/error", redirectView.getUrl()); // Assuming you have an error page at "/error"
    }
    }

//    @Test
//    public void createComment_whenCommentTextIsTooLong_shouldReturnError() {
//        // Arrange
//        String postId = "123";
//        String commentText = "A".repeat(1001); // Assuming the maximum length is 1000 characters
//        Comment comment = new Comment();
//        comment.setText(commentText);
//
//        // Act
//        RedirectView redirectView = commentsController.createComment(postId, commentText);
//
//        // Assert
//        assertNotNull(redirectView);
//        assertEquals("/error", redirectView.getUrl()); // Assuming you have an error page at "/error"
//    }
//}