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
public class NavbarTest {

    WebDriver driver;
    Faker faker;
    User user;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        user = new User();
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
    public void successfulDisplayOfNavbar() {
        WebElement navbar = driver.findElement(By.className("navbar"));
        Assert.assertEquals(true, navbar.isDisplayed());
    }

    @Test
    public void successfulClickOnAcebookInNavBar() {
        driver.findElement(By.id("acebook_home")).click();
        WebElement title = driver.findElement(By.tagName("h1"));
        String titleAsStr = title.getText();
        Assert.assertEquals("Posts", titleAsStr);
    }

    @Test
    public void profilePicShownInNavbar() {
        WebElement profilePic = driver.findElement(By.className("profile-pic"));
        Assert.assertEquals(true, profilePic.isDisplayed());

//         Verify the src attribute is correct (this is optional, based on your needs)
//        String expectedSrc = "http://localhost:8080/uploads/testuser.jpg"; // Update this based on your implementation
//        assertTrue(profilePic.getAttribute("src").contains(expectedSrc), "Profile picture source should be correct");
//    }
}}




