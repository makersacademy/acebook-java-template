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
public class PostsTest {

  WebDriver driver;
  Faker faker;

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    faker = new Faker();

  @Test
  public void deletePost() {
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

    // making posts
    driver.findElement(By.id("content")).sendKeys("Happy days");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.id("content")).sendKeys("Happy morning");
    driver.findElement(By.id("submit")).click();

    // delete post
    List<WebElement> a = driver.findElements(By.id("delete"));
    a.get(0).click();
    driver.switchTo().alert().accept(); // handle popup window

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertFalse("checks that the post appears", bodyText.contains("Happy days"));
    Assert.assertTrue("checks that the second post appears", bodyText.contains("Happy morning"));

  }

  @After
  public void tearDown() {
    driver.close();
  }

  @Test
  public void DatabaseDateTest() {
    // making post
    Date time = new Date(System.currentTimeMillis());
    driver.findElement(By.id("content")).sendKeys("What is the time?");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("Date is added to database", bodyText.contains(time.toString()));
  }

  @Test
  public void successfulPost() {
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

    // making post
    driver.findElement(By.id("content")).sendKeys("5G for the win in vaccines");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("5G for the win in vaccines"));
  }

  @Test
  public void twoPostsAppear() {
    driver.findElement(By.id("content")).sendKeys("The weather today is sunny!");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the initial post appears", bodyText.contains("The weather today is sunny!"));

    // making second post
    driver.findElement(By.id("content")).sendKeys("Hello World!");
    driver.findElement(By.id("submit")).click();

    String bodyText1 = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the initial post appears", bodyText1.contains("The weather today is sunny!"));
    Assert.assertTrue("checks that the second post appears", bodyText1.contains("Hello World!"));
  }

  // Test dateAppears
  // Test nameAppears
  // Test pictureAppears
  // Test postsShownInReverseChronologicalOrder

}