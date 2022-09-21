package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.assertj.core.api.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

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

	private Post post = new Post("hello");
	private Post post2 = new Post("");

	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}

	@Test
	public void postHasNoContent() {
		assertThat(post2.getContent(), containsString(""));
	}

}
