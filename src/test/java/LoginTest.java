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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LoginTest {

    WebDriver driver;
    Faker faker;
    @Value("${spring.datasource.chromedriver}")
    private String chromedriver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", chromedriver);
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulLogoutDirectsToSignIn() {
        driver.get("http://localhost:8080/users/new");
        String randomName = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(randomName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        
        // TimeUnit.SECONDS.sleep(1);
        // Thread.sleep(90000);
        // driver.get("http://localhost:8080");
        driver.findElement(By.id("username")).sendKeys(randomName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.className("btn")).click();

        String title = driver.getTitle();
        Assert.assertEquals("Acebook", title);
    }
}
