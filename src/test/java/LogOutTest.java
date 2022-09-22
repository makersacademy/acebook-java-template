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

public class LogOutTest {

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
  public void logOutReturnsLogOutPageForUser() {
    // There's no post method for WebDriver unfortunately, only get
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("username")).sendKeys("ash");
    driver.findElement(By.id("password")).sendKeys("123");
    driver.findElement(By.id("submit")).click();
    driver.get("http://localhost:8080/logout");
    // This line is test clicking logout button
    // driver.findElement(By.id("Logout")).clic
    Assert.assertEquals(driver.getTitle(), "Confirm Log Out?");
  }
  //

  @Test
  public void clickinglogOutOnLogOutPageLogsUserOut() {
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("submit")).click();
    driver.get("http://localhost:8080/logout");
    // findElement(By.id) didn't work for logout page, so used By.tagName for Log
    // Out <button>
    driver.findElement(By.tagName("button")).click();
    // Tests title of page to equal log in page's
    //
    Assert.assertEquals(driver.getTitle(), "Please sign in");
  }

  // Test to see if session exists

  // @Test
  // public void logInCreatesCookies() {
  // driver.get("http://localhost:8080/login");
  // driver.findElement(By.id("username")).sendKeys("boris");
  // driver.findElement(By.id("password")).sendKeys("password123");
  // driver.findElement(By.id("submit")).click();
  // Assert.assertTrue(driver.manage().getCookieNamed("boris");
  //

  // Test for redirecting to login page from /logout if not logged in

  // @Test
  // public void logOutRedirectsGuestToLogInPage() {
  // driver.get("http://localhost:8080/logout");
  // String title = driver.getTitle();
  // Assert.assertEquals("Please sign in", title);
  //

}
