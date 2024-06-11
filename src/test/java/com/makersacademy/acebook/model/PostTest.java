//package com.makersacademy.acebook.model;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import com.makersacademy.acebook.model.Post;
//import com.makersacademy.acebook.repository.PostRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PostTest {
//	@Mock
//	PostRepository repository;
//	User user = new User();
//
//
//	private Post post = new Post("hello", "Greetings!", user);
//	@Test
//	public void postHasContent() {
//		Post post = new Post();
//		assertThat(post.getContent(), containsString("hello"));
//	}
//
//	@Test
//	public void postIsDeleted() {
//		Post post = new Post("hello", "Greetings!", user);
//
//
//		repository.deleteById(1L);
//
//		verify(repository).deleteById(1L);
//	}
//
//	@Test
//	public void postisEdited() {
//		// Create a new post
//		Post post = new Post("hello", "Greetings!", user);
//		post.setId(1L); // Set the ID for the post
//
//		// Mock the behavior of findById to return the post when called with ID 1L
//		when(repository.findById(1L)).thenReturn(Optional.of(post));
//
//		// Edit the post content and title
//		post.setContent("hello edited");
//		post.setTitle("Greetings Edited!");
//
//		// Call the method to edit the post
//		repository.save(post);
//
//		// Verify that the post is saved
//		verify(repository).save(post);
//		assertThat(post.getContent(), containsString("hello edited"));
//
//	}
//}



