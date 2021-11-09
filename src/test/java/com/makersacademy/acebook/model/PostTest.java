package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostTest {

	@Test
	public void postHasContent() {
		Post post = new Post("hello", null, null);
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postRemovesLeadingSpaces() {
		Post post = new Post("    Leading", null, null);
		assertThat(post.getContent(), containsString("Leading"));
	}

	@Test
	public void postRemovesTrailingSpaces() {
		Post post = new Post("Trailing     ", null, null);
		assertThat(post.getContent(), containsString("Trailing"));
	}

	@Test
	public void postAcceptsSentences() {
		Post post = new Post("  Hello, This is a sentence.   ", null, null);
		assertThat(post.getContent(), containsString("Hello, This is a sentence."));
	}
}
