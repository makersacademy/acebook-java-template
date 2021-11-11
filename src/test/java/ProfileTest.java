import java.util.List;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProfileTest {
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
  public void showMyPosts(){
    // making posts
    driver.findElement(By.id("content")).sendKeys("this is");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.id("content")).sendKeys("a post");
    driver.findElement(By.id("submit")).click();

    //visit profile page
    driver.get("http://localhost:8080/users/profile");

    //test text appears
    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("this is"));
    Assert.assertTrue("checks that the second post appears", bodyText.contains("a post"));
  }

  @Test
  public void deletePost() {
    driver.findElement(By.id("content")).sendKeys("I'm Here");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.id("content")).sendKeys("I'm not");
    driver.findElement(By.id("submit")).click();

    driver.get("http://localhost:8080/users/profile");

    // delete post
    List<WebElement> a = driver.findElements(By.id("delete"));
    a.get(0).click();
    driver.switchTo().alert().accept(); // handle popup window

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("I'm Here"));
    Assert.assertFalse("checks that the second post appears", bodyText.contains("I'm not"));
  }

  @Test
  public void canOnlySeeMyPosts() {
    driver.findElement(By.id("content")).sendKeys("Not Your Post");
    driver.findElement(By.id("submit")).click();

    driver.get("http://localhost:8080/logout");

    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    driver.findElement(By.id("content")).sendKeys("My Post");
    driver.findElement(By.id("submit")).click();

    driver.get("http://localhost:8080/users/profile");
    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that my post appears", bodyText.contains("My Post"));
    Assert.assertFalse("checks that the second post appears", bodyText.contains("Not Your Post"));
  }
}
