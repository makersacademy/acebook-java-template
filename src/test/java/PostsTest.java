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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PostsTest {

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
    public void MinLength() {
        driver.get("http://localhost:8080/posts");
        driver.findElement(By.id("username")).sendKeys("j");
        driver.findElement(By.id("password")).sendKeys("pass");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("content")).sendKeys("123");
        driver.findElement(By.id("submit")).click();
        boolean postFound = false;
        List<WebElement> links = driver.findElements(By.tagName("li"));
        for (int i = 1; i < links.size(); i++)
        {
            if (links.get(i).getText().equals("123")){postFound = true; break;}
        }
        Assert.assertEquals(true, postFound);

        driver.findElement(By.id("content")).sendKeys("12");
        driver.findElement(By.id("submit")).click();
        postFound = false;
        links = driver.findElements(By.tagName("li"));
        for (int i = 1; i < links.size(); i++)
        {
            if (links.get(i).getText().equals("12")){postFound = true; break;}
        }
        Assert.assertEquals(false, postFound);

        driver.findElement(By.id("content")).sendKeys("1");
        driver.findElement(By.id("submit")).click();
        postFound = false;
        links = driver.findElements(By.tagName("li"));
        for (int i = 1; i < links.size(); i++)
        {
            if (links.get(i).getText().equals("1")){postFound = true; break;}
        }
        Assert.assertEquals(false, postFound);

    }
}
