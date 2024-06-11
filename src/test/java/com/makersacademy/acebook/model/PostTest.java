package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostTest {
  @Mock
	PostRepository repository;
	User user = new User();


	private Post post = new Post("hello", "Greetings!", user);
	@Test
	public void postHasContent() {
		Post post = new Post("hello");
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postIsDeleted() {
		Post post = new Post("hello");


		repository.deleteById(1L);

		verify(repository).deleteById(1L);
	}
}
