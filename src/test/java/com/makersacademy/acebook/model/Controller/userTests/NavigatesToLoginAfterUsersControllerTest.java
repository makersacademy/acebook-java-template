package com.makersacademy.acebook.model.Controller.userTests;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class NavigatesToLoginAfterUsersControllerTest {
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

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void navigatesToLoginAfterUsersController() throws IOException {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.get("http://localhost:8080/users/new");
            driver.findElement(By.id("username")).sendKeys(user.getUsername());
            driver.findElement(By.id("password")).sendKeys("password");
            driver.findElement(By.id("submit")).click();

            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://localhost:8080/users/new")));

            String url = driver.getCurrentUrl();
            assertEquals("http://localhost:8080/login", url);

    }
}

