package com.makersacademy.acebook.model;

import static org.junit.Assert.assertEquals;

import java.util.List;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ProfilePageTest {

    static WebDriver driver;
    static Faker faker;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    public void login() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("johndoe");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.tagName("button")).click();
    }

    @Test
    public void profilePageDisplaysUserInfo() {
        login();
        driver.get("http://localhost:8080/users/profile");

        User user = userRepository.findByUsername("johndoe");

        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String bio = user.getBio();


        WebElement firstNameElement = driver.findElement(By.id("firstname"));
        WebElement lastNameElement = driver.findElement(By.id("lastname"));
        WebElement bioElement = driver.findElement(By.id("bio"));

        assertEquals(firstname, firstNameElement.getText());
        assertEquals(lastname, lastNameElement.getText());
        assertEquals(bio, bioElement.getText());
    }
    @Test
    public void profilePageDisplaysUserPosts() {
        login();
        driver.get("http://localhost:8080/users/profile");

        List<WebElement> posts = driver.findElements(By.id("post-content"));
        String[] expectedPosts = {
                "Excited to be here!",
                "This is my first post!"};

        for (int i = 0; i < expectedPosts.length; i++) {
            assertEquals(expectedPosts[i], posts.get(i).getText());
        }
    }

        @Test
    public void profilePageAllowsAddingNewPost() {
        login();
        driver.get("http://localhost:8080/users/profile");

        String newPostContent = "This is a testing post!";
        WebElement postInput = driver.findElement(By.id("content"));
        WebElement addPostButton = driver.findElement(By.id("content_create"));
        postInput.sendKeys(newPostContent);
        addPostButton.click();
        List<WebElement> posts = driver.findElements(By.id("post-content"));
        WebElement latestPost = posts.get(0);
        assertEquals(newPostContent, latestPost.getText());
        postRepository.deleteTestPost();
    }
    @Test
    public void ProfileCreateComment() {
        login();
        driver.get("http://localhost:8080/users/profile");

        // Create a new post
        driver.findElement(By.id("content")).sendKeys("post test");
        driver.findElement(By.id("content_create")).click();

        Post find = postRepository.findTopByOrderByIdDesc();
        Long id = find.getId();

        // Comment Button is clicked in order for the comment-input field to appear
        driver.findElement(By.id(String.format("comment_button%d", id))).click();
        WebElement comment_element = driver.findElement(By.id(String.format("comment-input%s", id)));
        comment_element.sendKeys("comment test");

        driver.findElement(By.id(String.format("submit_button%s", id))).click();

        List<WebElement> post_element = driver.findElements(By.className("post"));
        WebElement element1 = post_element.get(0);

        WebElement comment = element1.findElement(By.className("comment"));

        Assert.assertEquals("comment test", comment.getText());
        commentRepository.deleteTestComment();
        postRepository.deleteTestPost();
    }

    @Test
    public void ProfileLikeTestPost() {
        login();
        driver.get("http://localhost:8080/users/profile");
        Post find = postRepository.findTopByOrderByIdDesc();
        Long id = find.getId();

//		Finding the like button for the test post and clicking
        WebElement like_element = driver.findElement(By.id(String.format("like_button%s", id)));
        like_element.click();

//		Find the test post and asserts that the like count is 1
        List<WebElement> post_element = driver.findElements(By.className("post"));
        WebElement element1 = post_element.get(0);
        Assert.assertEquals("John\nThis is a testing post!\nLikes: 4\nLike\nthis is a new comment\nComment", element1.getText());
        postRepository.deleteTestPost();
    }
}
