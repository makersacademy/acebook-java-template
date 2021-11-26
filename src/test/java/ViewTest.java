import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ViewTest {

    WebDriver driver;
    Faker faker;
    TestHelper helper;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        helper = new TestHelper();
        helper.signUpAndIn();
        driver = helper.driver;
        faker = helper.faker;
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testPostContent() {
        driver.get("http://localhost:8080/posts");

        driver.findElement(By.id("content")).sendKeys("first post");
        driver.findElement(By.id("Submit_button")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("first post"));
    }

    @Test
    public void testFullyPostDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy kk:mm");
        LocalDateTime myTime = LocalDateTime.now();

        driver.get("http://localhost:8080/posts");

        driver.findElement(By.id("content")).sendKeys("first post");
        driver.findElement(By.id("Submit_button")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(myTime.format(formatter)));
    }

    @Test
    public void testWholePost() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy kk:mm");
        LocalDateTime myTime = LocalDateTime.now();
        driver.get("http://localhost:8080/posts");
        driver.findElement(By.id("content")).sendKeys("first post");
        driver.findElement(By.id("Submit_button")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(myTime.format(formatter)));
        Assert.assertTrue("Text not found!", bodyText.contains(helper.getName()));
        Assert.assertTrue("Text not found!", bodyText.contains("0"));
    }


}

