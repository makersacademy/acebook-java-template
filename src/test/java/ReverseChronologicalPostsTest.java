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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
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

        List<WebElement> content = driver.findElements(By.className("post-text"));
        List<String> postsInDisplayedOrder = new ArrayList<String>();
        int i = 0;
        for (WebElement contentElement : content) {
            String webElementString = contentElement.getText();
            if ( i <= 3) {
                postsInDisplayedOrder.add(webElementString);
            }
        i++;
        }
        List<String> expectedList = Arrays.asList( "4", "3", "2", "1");
        Assert.assertEquals(expectedList, postsInDisplayedOrder);
    }
}