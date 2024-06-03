package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.CommentRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostImageUploadTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    static WebDriver driver;
    static Faker faker;

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

    public WebDriver getDriver() {
        return driver;
    }

    public void login() {
        driver.get("http://localhost:8080/login");
        // Login
        driver.findElement(By.id("username")).sendKeys("johndoe");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.tagName("button")).click();
    }

    @Test
    public void signInCreatePostWithImage() {
        // Create a new post
        Post post = new Post();
        post.setContent("post test");
        String expectedUrl = "https://res.cloudinary.com/dk3vxa56n/image/upload/c_limit,h_60,w_90/v1717424673/o2zfyet3gtglmdmszi4r.png";
        post.setImg_url(expectedUrl);
        User user = userRepository.findByUsername("johndoe");
        post.setUser(user);
        postRepository.save(post);
        login();
//      Finds all posts then finds the most recent post
        List<WebElement> post_elements = driver.findElements(By.className("post"));
        WebElement firstPostElement = post_elements.get(0);
        // Verify the image URL
        WebElement postImageElement = firstPostElement.findElement(By.id("postImage"));
        String actualUrl = postImageElement.getAttribute("src");
        Assert.assertEquals(expectedUrl, actualUrl);
        postRepository.deleteTestPost();
    }
}
