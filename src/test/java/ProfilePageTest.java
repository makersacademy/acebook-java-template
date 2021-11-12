import com.github.javafaker.Faker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePageTest {
    WebDriver driver;
    Faker faker;
    public String name;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();

        name = faker.name().firstName();

        // user sign up
        signup(name, "password");

        // login
        login(name, "password");
    }

    // defining helper methods
    private void signup(String username, String password) {
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    private void login(String username, String password) {
        driver.get("http://localhost:8080/login.html");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type='submit'][value='Log in']")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("input[type='submit'][value='Sign Out']")).click();
    }

    private void post(String content) {
        driver.findElement(By.id("content")).sendKeys(content);
        driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();
    }

    @After
    public void teardown() {
        driver.close();
    }

    @Test
    public void userNavigateToProfilePAge() {
        // test 14
        Select drpAccount = new Select(driver.findElement(By.name("account")));
        drpAccount.selectByVisibleText("Profile");

    }

}
