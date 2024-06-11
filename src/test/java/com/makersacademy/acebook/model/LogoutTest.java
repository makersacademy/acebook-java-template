package com.makersacademy.acebook.model;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LogoutTest {

    WebDriver driver;
    Faker faker;
    User user;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        user = new User();

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulLogoutRedirectsToSignIn(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(user.getUsername());
        driver.findElement(By.id("password")).sendKeys("password");
        WebElement fileInput = driver.findElement(By.id("photo"));
        fileInput.sendKeys("/Users/lauraselby/Documents/projects/acebook/acebook-java/src/main/resources/static/pic.jpeg");
        driver.findElement(By.id("submit")).click();
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys(user.getUsername());
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.id("logout_id")).click();
        driver.findElement(By.tagName("button")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
}
}

