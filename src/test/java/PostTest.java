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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostTest {
  WebDriver driver;
  Faker faker;
  public String name;

  @Before
  public void setup(){
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    faker = new Faker();

    name = faker.name().firstName();

    // user sign up
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    //login
    driver.get("http://localhost:8080/login.html");
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.cssSelector("input[type='submit'][value='Log in']")).click();
  }


  @After
  public void teardown(){
    driver.close();
  }

  @Test
  public void authenticatedUserSeesPosts(){
    //Test 2
    driver.get("http://localhost:8080/posts");
    Assert.assertEquals("Hey, " + name + '!', driver.findElement(By.className("greeting")).getText());
  }

  @Test
  public void disallowInvalidUsersViewingPosts(){
    //Test 3

    //sign out
    driver.findElement(By.cssSelector("input[type='submit'][value='Sign Out']")).click();

    //try to access posts
    driver.get("http://localhost:8080/posts");

    //we get redirected to the login page
    Assert.assertEquals("http://localhost:8080/login.html", driver.getCurrentUrl());
  }

  @Test
  public void userCanPostText(){
    //Test 7
    driver.findElement(By.id("content")).sendKeys("I like bacon!");
    Assert.assertEquals("I like bacon!", driver.findElement(By.cssSelector("p[th:text='${post.content}']")));

  }

}
