import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogOutTest {
    WebDriver driver;
    Faker faker;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();

    }

    @Test
    public void testLogOutButton(){
        driver.get("http://localhost:8080/users/new");
        final String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        driver.findElement(By.id("logout")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Log Out')]")).click();
        String title = driver.getTitle();
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals("Please sign in", title);
        Assert.assertEquals("http://localhost:8080/login?logout", expectedUrl);

    }

}
