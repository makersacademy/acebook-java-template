import java.util.List;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.openqa.selenium.support.pagefactory.ByAll;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentTest {

  WebDriver driver;
  Faker faker;

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    faker = new Faker();
    // signup
    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

  }

  @After
  public void tearDown() {
    driver.close();
  }

  @Test
  public void deleteComment() {

    // making posts
    driver.findElement(By.id("content")).sendKeys("This is a new comment test");
    driver.findElement(By.id("submit")).click();

    // Add a comment
    driver.findElement(By.id("comment")).click();
    driver.findElement(By.id("content")).sendKeys("Comment on my test");
    driver.findElement(By.id("submit")).click();

    // delete comment
    List<WebElement> a = driver.findElements(By.id("deleteComment"));
    a.get(0).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertFalse("checks that the comment gets deleted", bodyText.contains("Comment on my test"));

  }

  @Test
  public void commentsAppear() {

    // making a post
    driver.findElement(By.id("content")).sendKeys("Comment Appears Test 1");
    driver.findElement(By.id("submit")).click();

    // Add a comment
    driver.findElement(By.id("comment")).click();
    driver.findElement(By.id("content")).sendKeys("Comment on Comment Test 1");
    driver.findElement(By.id("submit")).click();

    String bodyText1 = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the initial post appears", bodyText1.contains("Comment Appears Test 1"));
    Assert.assertTrue("checks that the comment appears", bodyText1.contains("Comment on Comment Test 1"));
  }

  @Test
  public void commentsDontAppearOnWrongPosts() {

    // making a post
    driver.findElement(By.id("content")).sendKeys("Comment Appears Test 2");
    driver.findElement(By.id("submit")).click();

    // Add a comment
    driver.findElement(By.id("comment")).click();
    driver.findElement(By.id("content")).sendKeys("Comment on Comment Test 2");
    driver.findElement(By.id("submit")).click();

    // Check that a new post has its own comment but not that of the post made
    // before
    String bodyText1 = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the new post appears", bodyText1.contains("Comment Appears Test 2"));
    Assert.assertTrue("checks that the new comment on this post appears",
        bodyText1.contains("Comment on Comment Test 2"));
    Assert.assertFalse("checks that the comment on previous post doesnt appear",
        bodyText1.contains("Comment on Comment Test 1"));
  }

}