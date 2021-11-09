import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.model.Post;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = Application.class)
@SpringBootTest(classes = Application.class)
// @DataJpaTest
public class ReverseChronologicalPostsTest {

    WebDriver driver;
    Faker faker;
    @Value("${spring.datasource.chromedriver}")
    private String chromedriver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", chromedriver);
        driver = new ChromeDriver();
        faker = new Faker();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void successfulPostsDisplayInReverseChronologicalOrder() {
        driver.get("http://localhost:8080/users/new");
        String randomName = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(randomName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        driver.findElement(By.id("username")).sendKeys(randomName);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.className("btn")).click();

        driver.findElement(By.id("content-input")).sendKeys("1");
        driver.findElement(By.id("submit-post-btn")).click();
        driver.findElement(By.id("content-input")).sendKeys("2");
        driver.findElement(By.id("submit-post-btn")).click();
        driver.findElement(By.id("content-input")).sendKeys("3");
        driver.findElement(By.id("submit-post-btn")).click();
        driver.findElement(By.id("content-input")).sendKeys("4");
        driver.findElement(By.id("submit-post-btn")).click();

        List<WebElement> content = driver.findElements(By.className("text"));
        List<String> postsInDisplayedOrder = new ArrayList<String>();
        for (WebElement contentElement : content) {
            // for (int i = 0; i == 4; i++) {
                String webElementString = contentElement.getText();
                postsInDisplayedOrder.add(webElementString);
            // }
        }
        List<String> expectedList = new ArrayList<>();
            expectedList.add("4");
            expectedList.add("3");
            expectedList.add("2");
            expectedList.add("1");
        System.out.println("Expected List " + expectedList);
        System.out.println("Posts in displayed order " + postsInDisplayedOrder);
        Assert.assertEquals(expectedList, postsInDisplayedOrder);
    }
}