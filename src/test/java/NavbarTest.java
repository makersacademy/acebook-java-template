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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class NavbarTest {

  WebDriver driver;
  Faker faker;
  @Value("${spring.datasource.chromedriver}")
  private String chromedriver;

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver", chromedriver);
    driver = new ChromeDriver();
    faker = new Faker();
  }

  @After
  public void tearDown() {
    driver.close();
  }

  @Test
  public void testWhenLoggedOutNavbarHasSignUpLink() {
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.linkText("Sign Up")).click();
    String url = driver.getCurrentUrl();
    Assert.assertEquals("http://localhost:8080/users/new", url);
  }

  @Test
  public void testWhenLoggedOutNavbarHasLogInLink() {
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.linkText("Log In")).click();
    String url = driver.getCurrentUrl();
    Assert.assertEquals("http://localhost:8080/login", url);
  }

  @Test
  public void testWhenLoggedInNavbarHasLogOutButton() {
    // Signing up
    driver.get("http://localhost:8080/users/new");
    String randomName = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(randomName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // Logging in
    driver.findElement(By.id("username")).sendKeys(randomName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.className("btn")).click();

    // Logging out
    driver.findElement(By.className("navbar-logout")).click();
    String url = driver.getCurrentUrl();
    Assert.assertEquals("http://localhost:8080/login?logout", url);
  }

  @Test
  public void testWhenLoggedInNavbarHasPostsLink() {
    // Signing up
    driver.get("http://localhost:8080/users/new");
    String randomName = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(randomName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // Logging in
    driver.findElement(By.id("username")).sendKeys(randomName);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.className("btn")).click();

    driver.findElement(By.linkText("Posts")).click();
    String url = driver.getCurrentUrl();
    Assert.assertEquals("http://localhost:8080/posts", url);
  }
}
