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

public class SignOutTest {

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
  public void successfulSignOutRedirectsToSignIn() {
    driver.get("http://localhost:8080/logout");
    driver.findElement(By.xpath("//button")).click();
    String title = driver.getTitle();
    Assert.assertEquals("Please sign in", title);
  }

}
