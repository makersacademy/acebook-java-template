import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserPageTest {
    WebDriver driver;
    Faker faker;
    String username;
    String password;


    /* Mateusz chromedriver patch:
     * /Program Files/chromedriver/chromedriver.exe
     */
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Program Files/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        faker = new Faker();
        username = faker.name().firstName();
        password = "mypassword";
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void navBarUserButton() {
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //redirects to sign in
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();

      driver.findElement(By.id("user-btn")).click();
      WebElement userText = driver.findElement(By.id("user-profile"));
      Assert.assertEquals(username + " profile", userText.getText());
    }

  
}
