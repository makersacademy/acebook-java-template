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
public class SignUpTest {

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
    public void successfulSignUpRedirectsToSignIn() {
        String details = faker.name().firstName();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(details);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }
    @Test
    public void navBarAppearsOnPages() {
        driver.get("http://localhost:8080/");
        WebElement navBar = driver.findElement(By.tagName("nav"));
        Assert.assertNotNull("Navigation bar should be present on the page", navBar);
    }
    @Test
    public void navLinkCorrectLinksWhenLoggedIn(){
        String details = faker.name().firstName();
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(details);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
        driver.findElement(By.id("username")).sendKeys(details);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        List<WebElement> signupLinks = driver.findElements(By.linkText("Sign Up"));
        List<WebElement> loginLinks = driver.findElements(By.linkText("Login"));
        Assert.assertTrue("Sign Up should not be present on the page", signupLinks.isEmpty());
        Assert.assertTrue("Login should not be present on the page", loginLinks.isEmpty());
        Assert.assertNotNull("Log out should be present on the page", logoutLink);
    }
    @Test
    public void navLinkCorrectLinksWhenLoggedOut(){
        driver.get("http://localhost:8080/");
        WebElement loginLink = driver.findElement(By.linkText("Login"));
        WebElement signupLink = driver.findElement(By.linkText("Sign Up"));

        Assert.assertNotNull("Log out should be present on the page", loginLink);
        Assert.assertNotNull("Sign Up should be present on the page", signupLink);

        List<WebElement> logoutLinks = driver.findElements(By.linkText("Logout"));
        Assert.assertTrue("Logout should not be present on the page", logoutLinks.isEmpty());
    }
}
