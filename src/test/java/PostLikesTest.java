import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostLikesTest {

    WebDriver driver;
    Faker faker;
    TestHelper helper;

    @Before
    public void setup() throws SQLException {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        helper = new TestHelper();
        helper.signIn();
        driver = helper.driver;
        faker = helper.faker;
    }


    @Test
    public void OneLikesTest() {
        driver.get("http://localhost:8080/posts");
        driver.findElement(By.id("content")).sendKeys("post.getContent()");
        driver.findElement(By.id("Submit_button")).click();
        driver.findElement(By.id("likes"));
        driver.findElement(By.id("like_button")).click();
        WebElement item = driver.findElement(By.id("likes"));
//        Assert.assertEquals(item.getText(), "1");
    }

}