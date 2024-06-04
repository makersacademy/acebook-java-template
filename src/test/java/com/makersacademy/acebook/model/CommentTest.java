package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
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
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

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
        driver.get("http://localhost:" + port + "/login");
        // Login
        driver.findElement(By.id("username")).sendKeys("johndoe");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.tagName("button")).click();
    }

    @Test
    public void signInCreateComment() {
        login();
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
}
