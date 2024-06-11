import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostSeleniumTest {

    private WebDriver driver;
    Faker faker;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        driver.get("http://localhost:8080/posts");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPostsinChronological() {
        //Assigning a fake user & posts
        String fakeName = faker.name().firstName();
        String post1 = faker.random().toString();
        String post2 = faker.random().toString();

        //Signing up into the app
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(fakeName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        // Logging into the app with same details
        driver.findElement(By.id("username")).sendKeys(fakeName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.className("btn")).click();

        //Add in fake posts
        driver.findElement(By.id("content")).sendKeys(post1);
        driver.findElement(By.id("contentSubmit")).click();
        driver.findElement(By.id("content")).sendKeys(post2);
        driver.findElement(By.id("contentSubmit")).click();

        //Checking structure of posts page
        List<WebElement> posts = driver.findElements(By.className("post"));
        assertThat(posts).isNotEmpty();
        String firstPostContent = posts.get(0).getText();
        String secondPostContent = posts.get(1).getText();
        assertThat(firstPostContent).isEqualTo(post2);
        assertThat(secondPostContent).isEqualTo(post1);
    }
}
