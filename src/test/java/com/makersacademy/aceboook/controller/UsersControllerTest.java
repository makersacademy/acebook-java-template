package com.makersacademy.aceboook.controller;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
//import jdk.internal.org.xml.sax.Locator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UsersControllerTest {

    WebDriver driver;
    WebDriver page;
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

//    @Test
//    public void usersCanReadUsername() {
//        driver.get("localhost:8080/users/3");
//        String username = driver.getTitle();
//        Assert.assertEquals("tom", username);
//    }

//    @Test
//    public void successfulSignUpRedirectsToSignIn() {
//        driver.get("http://localhost:8080/users/new");
//        driver.findElement(By.id("username")).sendKeys(faker.name().firstName());
//        driver.findElement(By.id("password")).sendKeys("password");
//        driver.findElement(By.id("submit")).click();
//        String title = driver.getTitle();
//        Assert.assertEquals("Please sign in", title);
//    }
}
