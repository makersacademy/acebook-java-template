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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LogInTest {

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
    public void successfulLogInRedirectsToPosts() {
        // user signs up
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys("Emma");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("signup")).click();
        // user logs in
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("Emma");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("login")).click();
        // main page
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/posts", url);
        Assert.assertEquals("Acebook", title);
    }

    @Test
    public void unsuccessfulLogInRedirectsBackToLogin() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("Emma");
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("login")).click();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/login?error", url);
    }
}