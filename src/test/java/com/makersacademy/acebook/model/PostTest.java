package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.PostRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostTest {

	private Post post = new Post("hello");
	@Autowired
	private PostRepository postRepository;

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
	public void signInViewPost() {
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys("Noah");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.tagName("button")).click();
		WebElement element = driver.findElement(By.tagName("ul"));
		Assert.assertEquals("Welcome!\nLikes: 8\nLike", element.getText());
	}

	@Test
	public void signInCreatePostCheckLikesIs0() {
		driver.get("http://localhost:8080/login");
		driver.findElement(By.id("username")).sendKeys("Noah");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.id("content")).sendKeys("post test");
		driver.findElement(By.id("content_create")).click();

		List<WebElement> element = driver.findElements(By.tagName("ul"));
		ArrayList<String> posts = new ArrayList<String>();
		for (WebElement e : element) {
			posts.add(e.getText());
		}
//		System.out.println(posts);
		Integer postNextID = Integer.valueOf(posts.get(posts.size()-1));
		System.out.println("-----------------" + postNextID);
		driver.findElement(By.id(String.format("like_button%s", posts.size()-1)))).click();
		Assert.assertEquals("post test\nLikes: 1\nLike", postNextID);
		postRepository.deleteTestPost();
	}

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

}

