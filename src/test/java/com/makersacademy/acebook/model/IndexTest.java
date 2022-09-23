package com.makersacademy.acebook.model;

import org.junit.Test;
import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class IndexTest {
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
    public void returnsPostsPageHTML() {
        // Test Values
        String RN = "Random Name";
        String RP = "Random Password";

        // Mocks Creating User
        driver.get("http://localhost:8080/signup");
        driver.findElement(By.id("email")).sendKeys(RN);
        driver.findElement(By.id("password")).sendKeys(RP);
        driver.findElement(By.className("submit-btn")).click();

        // Mocks Logging in
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys(RN);
        driver.findElement(By.id("password")).sendKeys(RP);
        driver.findElement(By.className("btn")).click();

        driver.get("http://localhost:8080/posts");
        Assert.assertEquals("Profile",
                driver.findElement(By.tagName("p")).getText());
    }
}
