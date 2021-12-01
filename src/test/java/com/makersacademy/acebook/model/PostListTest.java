package com.makersacademy.acebook.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PostListTest {
    private Post post = new Post("Old Entry");
    private Post post2 = new Post("Latest Entry");
    private ArrayList<Post> iterables = new ArrayList<Post>(Arrays.asList(post, post2));
    private PostList postList = new PostList();

    @Test
    public void postListReturnsArray() {
        postList.setList(iterables);
        assertThat(postList.getList(), instanceOf(ArrayList.class));
    }

    @Test
    public void postListReturnsArrayOfPost() {
        postList.setList(iterables);
        assertThat(postList.getList().get(0), instanceOf(Post.class));
    }

    @Test
    public void postListReturnsSpecificPost() {
        postList.setList(iterables);
        assertEquals("List item is post", postList.getList().get(1), post);
    }

    @Test
    public void postListSortList() {
        postList.setList(iterables);
        assertEquals(postList.postArrayList.get(0).getContent(), "Latest Entry");
    }
}

