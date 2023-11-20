package com.makersacademy.aceboook.controller;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.model.Post;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostControllerTest {


    @Autowired
    PostRepository postRepository;

    WebDriver driver;
    Faker faker;

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

    @Test
    public void testNewPostIsAtTheTopOfList() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("test_user");
        driver.findElement(By.id("password")).sendKeys("password22");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("content")).sendKeys("New Post");
        driver.findElement(By.id("submit")).click();

        WebElement ul = driver.findElement(By.tagName("ul"));
        List<WebElement> postList = ul.findElements(By.tagName("li"));

        String newPost = postList.get(0).getText();

        Assert.assertEquals("New Post", newPost);
    }

    @Test
    public void testNewPostHasUserIdAssigned() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("test_user");
        driver.findElement(By.id("password")).sendKeys("password22");
        driver.findElement(By.id("submit")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
        driver.findElement(By.id("content")).sendKeys("New Post");
        driver.findElement(By.id("submit")).click();
        List<Post> posts = postRepository.findAllByOrderByTimestampDesc();
        Post latestPost = posts.get(0);
        Long userId = latestPost.getUserId();
        Long expected = 4L;
        Assert.assertEquals(expected, userId);
    }

    @Test
    public void testCommentButtonNavigatesToPostPageFromPosts() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("test_user");
        driver.findElement(By.id("password")).sendKeys("password22");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("comment")).click();
        WebElement pElement = driver.findElement(By.tagName("p"));
        String pText = pElement.getText();
        Assert.assertEquals("Post content", pText);

    }
//    @Test
//    public void testPostPageShowsPostAndListOfComments() {
//        driver.get("http://localhost:8080/login");
//        driver.findElement(By.id("username")).sendKeys("test_user");
//        driver.findElement(By.id("password")).sendKeys("password22");
//        driver.findElement(By.id("submit")).click();
//
//    }
}
