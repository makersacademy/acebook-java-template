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
public class ViewTest {

    WebDriver driver;
    Faker faker;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        TestHelper helper = new TestHelper();
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
    public void testPostDate() {
        driver.get("http://localhost:8080/posts");

        driver.findElement(By.id("content")).sendKeys("first post");
        driver.findElement(By.id("Submit_button")).click();
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Posted :"));
    }
}

