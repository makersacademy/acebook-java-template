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
public class CommentTest {
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
    public void userCanCommentOnTheirPost() {
        String username = faker.name().firstName();
        String commentText = faker.beer().name();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // now on /login
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // now logged in on /posts
        driver.findElement(By.id("content")).sendKeys("My post");
        driver.findElement(By.className("post-submit-button")).click();
        // created a post
        List<WebElement> allPosts = driver.findElements(By.className("post"));
        WebElement firstPost = allPosts
                .get(0);
        firstPost.findElement(By.id("content")).sendKeys(commentText);
        firstPost.findElement(By.className("comment-submit-button")).click();
        // created a comment on the first post on the page
        List<WebElement> allComments= driver.findElements(By.className("comment"));
        // assert that there is any match for comment text in list of comments on page
        Assert.assertTrue(allComments.stream().anyMatch(comment -> comment.getText().equals(commentText)));
    }
}
