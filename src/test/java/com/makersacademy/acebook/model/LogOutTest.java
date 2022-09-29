package com.makersacademy.acebook.model;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class LogOutTest {

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
  public void logOutReturnsSignInPageForUser() {
    // There's no post method for WebDriver unfortunately, only get
    driver.get("http://localhost:8080/signup");
    driver.findElement(By.id("email")).sendKeys("ash");
    driver.findElement(By.id("password")).sendKeys("123");
    driver.findElement(By.className("submit-btn")).click();
    driver.get("http://localhost:8080/login");
    driver.findElement(By.id("username")).sendKeys("ash");
    driver.findElement(By.id("password")).sendKeys("123");
    driver.findElement(By.className("submit-btn")).click();

    driver.findElement(By.id("Logout")).click();
    driver.switchTo().alert().accept();
    Assert.assertEquals("Sign In", driver.getTitle());
  }

  @Test
  public void clickinglogOutOnLogOutPageLogsUserOut() {
    driver.get("http://localhost:8080/signup");
    driver.findElement(By.id("email")).click();
    driver.get("http://localhost:8080/logout");

    driver.findElement(By.className("submit-btn")).click();
    Assert.assertEquals("Sign In", driver.getTitle());
  }
}