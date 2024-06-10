package com.makersacademy.acebook.model.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.makersacademy.acebook.model.Post;
import org.junit.Test;

public class PostsRepositoryTest {
    @Test
    public void CheckIfPostWorks() {
        // Create a new post
        Post post = new Post("Content");

        // Check if the post is not null
        assertNotNull(post);

        // Check if the content is set correctly
        assertEquals("Content", post.getContent());

    }


}










//        page.navigate("localhost:8080/termsAndConditions");
//        Locator pageBody = page.locator("body");
//        assertThat(pageBody).containsText("Coming soon! In the meantime, please behave yourselves.");
//    }










