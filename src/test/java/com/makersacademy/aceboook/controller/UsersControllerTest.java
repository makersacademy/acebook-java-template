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

    @Test
    public void usersCanReadUsername() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("test_user");
        driver.findElement(By.id("password")).sendKeys("password22");
        driver.findElement(By.id("submit")).click();
        driver.get("http://localhost:8080/users/4");
        WebElement pElement = driver.findElement(By.id("profileUsername"));
        String pText = pElement.getText();
        Assert.assertEquals("Test_User", pText);

    }
}
