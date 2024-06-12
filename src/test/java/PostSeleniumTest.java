import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostSeleniumTest {

    private WebDriver driver;
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
    public void testPostsInReverseChronologicalOrder() {
        //Assigning a fake user
        String fakeName = faker.name().firstName();

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
        driver.findElement(By.id("content")).sendKeys("My first post");
        driver.findElement(By.id("contentSubmit")).click();
        driver.findElement(By.id("content")).sendKeys("My second post");
        driver.findElement(By.id("contentSubmit")).click();

        //Checking structure of posts page
        List<WebElement> posts = driver.findElements(By.className("post"));
        assertThat(posts).isNotEmpty();
        String firstPostContent = posts.get(0).getText();
        String secondPostContent = posts.get(1).getText();
        assertThat(firstPostContent).isEqualTo("My second post");
        assertThat(secondPostContent).isEqualTo("My first post");
    }
}
