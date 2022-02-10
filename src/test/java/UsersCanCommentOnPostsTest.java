import static org.junit.Assert.assertTrue;

import javax.validation.constraints.AssertTrue;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.Assert;
import org.junit.After;
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

public class UsersCanCommentOnPostsTest {

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
        driver.close(); // test later on with driver.quit();
    }

    @Test
    public void commentsOnPosts() {
        // Create user account
        String userName = faker.name().firstName();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // Log In
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // create new post
        driver.findElement(By.id("content")).sendKeys("first post");
        driver.findElement(By.id("submit")).click();
        // create comment on post
        driver.findElement(By.id("comment")).sendKeys("comment the post");
        driver.findElement(By.id("submit_comment")).click();
        // Test the order
        String page = driver.getPageSource();
        Assert.assertTrue(page.contains("comment the post"));
    }
}
