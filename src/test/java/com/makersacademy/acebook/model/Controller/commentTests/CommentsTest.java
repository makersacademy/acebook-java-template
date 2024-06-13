package com.makersacademy.acebook.model.Controller.commentTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;




public class CommentsTest {
    WebDriver driver;
    Faker faker;
    User user;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        user = new User();
        user.setUsername(faker.name().firstName());

        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(user.getUsername());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys(user.getUsername());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();

    }

    @After
    public void tearDown() {
        driver.close();
    }

@Test
    public void createComment() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    //Find the post and enter content
    driver.findElement(By.id("content")).sendKeys("Hello this is a post");
    //Click the submit button
    driver.findElement(By.id("post-content-submit")).click();

    WebElement post = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='post-container mt-4']//p[text()='Hello this is a post']")));

    WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group']//input[contains(@name, 'commentText')]")));

    form.sendKeys("This is a comment");

    form.submit();

    //Check if the comment was created
    WebElement commentList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='comment-container mt-2']//ul[@id='commentsList']")));
    assert commentList.getText().contains("This is a comment");

    }

    @Test
    public void resetComments() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Find the comment and enter content
        driver.findElement(By.id("content")).sendKeys("Hello this is a comment");
        //Click resetButton
        driver.findElement(By.id("comment-reset-button")).click();

        //Check if the comment was reset
        WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='form-group']//input[contains(@name, 'commentText')]")));
        assert form.getText().isEmpty();
        System.out.println("Comments have been reset successfully");

    }

    @Test
    public void noContentInPostNoPostIsCreated() {
        WebElement contentbox = driver.findElement(By.id("commentText"));
        String hasRequiredAttribute = contentbox.getAttribute("required");
        Assert.assertEquals("true", hasRequiredAttribute);

    }

}



