import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LikesOnPostsTest {
    WebDriver driver;
    Faker faker;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //        System.setProperty("webdriver.chrome.driver", "C:/Windows/chromedriver.exe");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void likesCountStartsAtZero() {
        String username = faker.name().firstName();
        String postText = faker.beer().yeast();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // now on /login
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // now logged in on /posts
        driver.findElement(By.id("content")).sendKeys("postText");
        driver.findElement(By.className("post-submit-button")).click();
        // created a yeasty post
        List<WebElement> allPosts = driver.findElements(By.className("post"));
        WebElement firstPost = allPosts.get(0);
        WebElement likesCount = firstPost.findElement(By.className("likes-count"));
        Assert.assertEquals("0 likes", likesCount.getText());
    }

    @Test
    public void userCanLikeAPostOnce() {
        String username = faker.name().firstName();
        String postText = faker.beer().yeast();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // now on /login
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // now logged in on /posts
        driver.findElement(By.id("content")).sendKeys("postText");
        driver.findElement(By.className("post-submit-button")).click();
        // created a yeasty post
        List<WebElement> allPosts = driver.findElements(By.className("post"));
        WebElement firstPost = allPosts.get(0);
        firstPost.findElement(By.className("like-button")).click();
        allPosts = driver.findElements(By.className("post"));
        firstPost = allPosts.get(0);
        WebElement likesCount = firstPost.findElement(By.className("likes-count"));
        Assert.assertEquals("1 like", likesCount.getText());
    }

    @Test
    public void userCannotLikeAPostMoreThanOnce() {
        String username = faker.name().firstName();
        String postText = faker.beer().name();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // now on /login
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // now logged in on /posts
        driver.findElement(By.id("content")).sendKeys("postText");
        driver.findElement(By.className("post-submit-button")).click();
        // created a beery post
        List<WebElement> allPosts = driver.findElements(By.className("post"));
        WebElement firstPost = allPosts.get(0);
        firstPost.findElement(By.className("like-button")).click();
        // clicked like button
        allPosts = driver.findElements(By.className("post"));
        firstPost = allPosts.get(0);
        WebElement likesCount = firstPost.findElement(By.className("likes-count"));
        Assert.assertEquals("1 like", likesCount.getText());
        // clicked like button again
        firstPost.findElement(By.className("like-button")).click();
        allPosts = driver.findElements(By.className("post"));
        firstPost = allPosts.get(0);
        likesCount = firstPost.findElement(By.className("likes-count"));
        Assert.assertEquals("1 like", likesCount.getText());
    }
}
