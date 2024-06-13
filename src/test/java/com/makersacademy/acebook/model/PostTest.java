package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PostTest {

	private Post post = new Post("hello");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postsInReverseChronological() {
		Post post1 = new Post("Post 1"); //Older Post
		post1.setCreatedAt(LocalDateTime.now().minusHours(1));
		Post post2 = new Post("Post 2");
		post2.setCreatedAt(LocalDateTime.now()); // Newest post
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		posts.add(post2);

		// Sort the posts in reverse chronological order
		posts.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));

		// Verify that the posts are in reverse chronological order
		for (int i = 0; i < posts.size() - 1; i++) {
			LocalDateTime current = posts.get(i).getCreatedAt();
			LocalDateTime next = posts.get(i + 1).getCreatedAt();
			assertThat(current, greaterThanOrEqualTo(next));
		}
	}

	@Test
	public void likesAreTypedAsListOfLongsThatCanBeSetAndGot() {
		List<Long> emptyList = new ArrayList<>();
        assertEquals(emptyList, post.getLikes());

		List<Long> likesList = new ArrayList<>();
		likesList.add(1L);
		likesList.add(2L);
		likesList.add(3L);
		post.setLikes(likesList);

		assertEquals(likesList, post.getLikes());
	}
}
