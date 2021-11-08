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

public class TimePostedTest {
    WebDriver driver;
    Faker faker;
    public String name;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        
        name = faker.name().firstName();

        // user sign up
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // sign in
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulPostDisplaysTimePosted() {
        driver.findElement(By.id("content")).sendKeys("Hello");
        driver.findElement(By.cssSelector("input[value='Post']")).click();
        Assert.assertTrue(driver.getPageSource().contains("moments ago"));
    }
}
