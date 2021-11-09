



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
        // System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        // System.setProperty("webdriver.chrome.driver", "/webdrivers/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();

        String name = faker.name().firstName();

        // user sign up
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        // directs to login page
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulSignOutRedirectsToLogin() {
        //Test 1
        // String name = faker.name().firstName();

        // // user sign up
        // driver.get("http://localhost:8080/users/new");
        // driver.findElement(By.id("username")).sendKeys(name);
        // driver.findElement(By.id("password")).sendKeys("password");
        // driver.findElement(By.id("submit")).click();

        // // directs to login page
        // driver.findElement(By.id("username")).sendKeys(name);
        // driver.findElement(By.id("password")).sendKeys("password");
        // driver.findElement(By.cssSelector("input[value='Log in']")).click();

        driver.findElement(By.cssSelector("input[value='Sign Out']")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Login page", title);
    }
}
