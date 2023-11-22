package com.makersacademy.aceboook.controller;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentControllerTest {


    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

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
    public void testAddCommentReflectedInCommentsList() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("test_user");
        driver.findElement(By.id("password")).sendKeys("password22");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("comment")).click();
        driver.findElement(By.id("addComment")).sendKeys("Here is my new comment!");
        driver.findElement(By.id("submitComment")).click();

        WebElement commentsUL = driver.findElement(By.tagName("ul"));
        List<WebElement> commentsLI = commentsUL.findElements(By.tagName("li"));
        String comment = commentsLI.get(0).getText();

        Assert.assertEquals("Here is my new comment!", comment);

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
