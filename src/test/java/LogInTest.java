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
    
    // We would like to write a test for already exisitng feature logging in:

    @Test
    public void successfulLogInRedirectsToPosts() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("Danny");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/posts", url);
        Assert.assertEquals("Acebook", title);

        // WebElement h1 = driver.findElement(By.tagName("h1"));
        // Assert.assertEquals("Posts", h1);
    }   

    @Test
    public void unsuccessfulLogInRedirectsBackToLogin() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("Danny");
        driver.findElement(By.id("password")).sendKeys("no");
        driver.findElement(By.tagName("button")).click();
        boolean exist = driver.findElement(By.className("alert")).isDisplayed();
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();

        Assert.assertEquals("http://localhost:8080/login?error", url);
        Assert.assertTrue(exist);
        
    }   
}