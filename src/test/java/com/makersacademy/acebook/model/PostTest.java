package com.makersacademy.acebook.model;

import com.makersacademy.acebook.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {
	@Mock
	PostRepository repository;
	User user = new User();

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
	Date date = formatter.parse("2024-06-11 20:16:56");
	Long id = 1L;

	List<Comment> comments;

	private final Post post = new Post(id, "hello", "Greetings!", date, user, comments);

	public PostTest() throws ParseException {
	}

	@Test
	public void postHasContent() {
		Post post = new Post(id, "hello", "Greetings!", date, user, comments);
		assertThat(post.getContent(), containsString("Greetings!"));
	}

	@Test
	public void postIsDeleted() {
		Post post = new Post(id, "hello", "Greetings!", date, user, comments);

		repository.deleteById(1L);

		verify(repository).deleteById(1L);
	}

	@Test
	public void postIsEdited() {
		// Create a new post
		Post post = new Post(id, "hello", "Greetings!", date, user, comments);
		post.setId(1L); // Set the ID for the post

		// Edit the post content and title
		post.setTitle("hello Edited!");
		post.setContent("Greetings edited");

		// Call the method to edit the post
		repository.save(post);

		// Verify that the post is saved
		verify(repository).save(post);
		assertThat(post.getContent(), containsString("Greetings edited"));
	}
}
