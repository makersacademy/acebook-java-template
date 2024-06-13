package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Import(PostService.class)
@ActiveProfiles("test")
public class PostServiceIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("testuser");
        userRepository.save(user);

        Post post1 = new Post("First post", user);
        post1.setCreatedAt(LocalDateTime.now().minusDays(1));
        postRepository.save(post1);

        Post post2 = new Post("Second post", user);
        post2.setCreatedAt(LocalDateTime.now());
        postRepository.save(post2);

        Post post3 = new Post("Third post", user);
        post3.setCreatedAt(LocalDateTime.now().minusHours(1));
        postRepository.save(post3);
    }

    @Test
    public void getAllPostsFromNewestToOldestShouldReturnPostsInDescendingOrderOfCreation() {
        Iterable<Post> postsIterable = postService.getAllPostsFromNewestToOldest();
        List<Post> posts = (List<Post>) postsIterable;

        Assert.assertEquals(3, posts.size());

        Iterator<Post> iterator = posts.iterator();
        Assert.assertEquals("Second post", iterator.next().getContent());
        Assert.assertEquals("Third post", iterator.next().getContent());
        Assert.assertEquals("First post", iterator.next().getContent());
    }
}