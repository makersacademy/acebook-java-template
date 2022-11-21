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
public class LikeTest {

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
    public void successfulLikeLiked() {
        // register
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys("name");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // login
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("name");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // create post
        driver.get("http://localhost:8080/posts");
        driver.findElement(By.id("content")).sendKeys(faker.friends().quote());
        driver.findElement(By.id("submit")).click();
        // like post
        driver.get("http://localhost:8080/posts");
        driver.findElement(By.id("like-button")).click();
        // find likes
        String likeCount = driver.findElement(By.id("post-like-count")).getText();
        Assert.assertEquals("1", likeCount);
    }
}
