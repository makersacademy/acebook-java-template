package com.makersacademy.aceboook.controller;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostControllerTest {


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
        driver.findElement(By.id("username")).sendKeys("user_1");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("content")).sendKeys("New Post");
        driver.findElement(By.id("submit")).click();

        WebElement ul = driver.findElement(By.tagName("ul"));
        List<WebElement> postList = ul.findElements(By.tagName("li"));

        String newPost = postList.get(0).getText();

        Assert.assertEquals("New Post", newPost);


    }
}
