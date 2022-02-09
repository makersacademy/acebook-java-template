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

public class PostsInReverseOrderTest {
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
      driver.close(); // test later on with driver.quit();
  }

  @Test
  public void postsShownInReverseOrder() {
    // Create user account
    String userName = faker.name().firstName();
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("username")).sendKeys(userName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();
    // Log In
    driver.findElement(By.id("username")).sendKeys(userName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.tagName("button")).click();
    // create new posts
    driver.findElement(By.id("content")).sendKeys("first post");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.id("content")).sendKeys("second post");
    driver.findElement(By.id("submit")).click();
    // Test the order
    String page = driver.getPageSource();
    Integer firstPosition = page.indexOf("first post"); 
    Integer secondPosition = page.indexOf("second post");
    Assert.assertTrue(firstPosition > secondPosition);
  }
}
