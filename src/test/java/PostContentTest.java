import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostContentTest {

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
    public void successfulPost() {
        driver.get("http://localhost:8080/users/new");
        String test = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(test);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("create_tag")).click();

        driver.findElement(By.id("username")).sendKeys(test);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("log_in_test")).click();

        driver.findElement(By.id("messageSender_test")).sendKeys("This is a test" + Keys.ENTER);
        String post = driver.findElement(By.id("post_id_test")).getText();
        Assert.assertEquals("This is a test", post);
    }
}
