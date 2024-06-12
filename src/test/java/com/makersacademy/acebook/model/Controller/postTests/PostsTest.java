package com.makersacademy.acebook.model.Controller.postTests;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PostsTest {

    WebDriver driver;
    Faker faker;
    User user;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker(); //generates fake data
        user = new User(); //User is the class that exists in the model package
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
    public void createPost() {
        //Find the post and enter content
        driver.findElement(By.id("content")).sendKeys("Hello this is a post");
        //Click the submit button
        driver.findElement(By.id("post-content-submit")).click();

        //Check if the post was created
        String postText = driver.findElement(By.tagName("p")).getText();
        assert postText.contains("Hello this is a post");
    }



}

