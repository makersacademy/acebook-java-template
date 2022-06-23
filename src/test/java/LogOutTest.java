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

public class LogOutTest {

    WebDriver driver;
    Faker faker;
    String username;
    String password;


    /* Mateusz chromedriver patch:
     * /Program Files/chromedriver/chromedriver.exe
     */
    @Before
    public void setup() {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
      driver = new ChromeDriver();
      faker = new Faker();
      username = faker.name().firstName();
      password= "mypassword";
    } 

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void logsOutAndRedirectsToSignIn(){
      driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        //Sign up as username
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("btn")).click();
        //signed in as username
        driver.findElement(By.className("log-out-btn")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Confirm Log Out?", title);
        driver.findElement(By.className("btn")).click();
        //Logged out of user
        WebElement signInText= driver.findElement(By.className("form-signin-heading"));
        Assert.assertEquals("Please sign in", signInText.getText());
    }
}
