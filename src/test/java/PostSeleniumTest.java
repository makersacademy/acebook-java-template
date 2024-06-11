import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/posts");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testPostsinChronological() {
        List<WebElement> posts = driver.findElements(By.className("post"));
        assertThat(posts).isNotEmpty();
        String firstPostContent = posts.get(0).findElement(By.className("content")).getText();
        String secondPostContent = posts.get(1).findElement(By.className("content")).getText();
        assertThat(firstPostContent).isEqualTo("Second post");
        assertThat(secondPostContent).isEqualTo("First post");
    }
}
