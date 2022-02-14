import static org.junit.Assert.assertTrue;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;

import org.junit.After;
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
public class picturesTest {

  WebDriver driver;
  String fakeUser;

  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    fakeUser = new Faker().name().username();
  }

  @After
  public void tearDown() {
      driver.close();
  }

  @Test
  public void indexPageHasAUploadButton() {
    this.createUserAndLogin();
    driver.get("http://localhost:8080/pictures");
    assertTrue(driver.findElement(By.id("upload")).isDisplayed());    
  }  

  private void createUserAndLogin() {
    // Create user account
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("username")).sendKeys(fakeUser);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();
    // Log In
    driver.findElement(By.id("username")).sendKeys(fakeUser);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.tagName("button")).click();
}
}
