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
public class LogoutTest {

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
    public void successfulLogOutRedirectsToLogInPage() {
        // user signs up
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys("Emma");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // user logs in
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("Emma");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // main page
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/posts", url);
        Assert.assertEquals("Acebook", title);

        driver.findElement(By.xpath("//a[@href='/logout']")).click();
        driver.findElement(By.xpath("//*[text()='Log Out']")).click();

        // http://localhost:8080/login?logout

        String logOuturl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/login?logout", logOuturl);
        boolean exist = driver.findElement(By.xpath("//*[text()='You have been signed out']")).isDisplayed();
        Assert.assertTrue(exist);
    }
}