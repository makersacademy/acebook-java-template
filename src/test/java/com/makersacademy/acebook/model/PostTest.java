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
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostTest {

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

	private Post post = new Post("Test Post");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("Test Post"));
	}

	@Test
	public void getSlashPostsReturnsPostsPageIfLoggedIn() {
		String RN = "Random Name";
		String RP = "Random Password";
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys(RN);
		driver.findElement(By.id("password")).sendKeys(RP);
		driver.findElement(By.className("btn")).click();
		driver.get("http://localhost:8080/posts");
		String title = driver.getTitle();
		Assert.assertEquals("Acebook", title);
	}
}
