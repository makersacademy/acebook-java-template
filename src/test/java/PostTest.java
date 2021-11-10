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
import java.util.List;
import org.openqa.selenium.WebElement;

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
    driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();
    Assert.assertEquals("I like bacon!", driver.findElement(By.tagName("h3")).getText());
    // Assert.assertTrue(driver.getPageSource().contains("I like bacon!"));

  }

  @Test
  public void postsDisplayedInOrder(){
    //Test 8
    String firstPost = "Bread is made from flour";
    String secondPost = "Crisps are made from potatos";
    driver.findElement(By.id("content")).sendKeys(firstPost);
    driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();
    driver.findElement(By.id("content")).sendKeys(secondPost);
    driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();

    //currently post content is the only thing using h3 tag, so we can use it to query
    //If we add more h3 tags to the page this test will break, but we can just create
    //a postcontent class and use By.className instead :)
    List<WebElement> result = driver.findElements(By.tagName("h3"));
      Assert.assertEquals(secondPost, result.get(0).getText());
      Assert.assertEquals(firstPost,result.get(1).getText());



  }

  @Test
  public void cap250char(){
    String char300 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. "+
      "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. "+
      "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec.";

    driver.findElement(By.id("content")).sendKeys(char300);
    driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();
    String page_content = driver.getPageSource();
    // System.out.println(page_content);
    Assert.assertEquals(char300.substring(0,249), driver.findElement(By.tagName("h3")).getText());
  }

  @Test
}
