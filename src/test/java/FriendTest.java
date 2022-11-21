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
public class FriendTest {

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
    public void makeFriendRequest() {
        // register
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys("user2");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        // login
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("user2");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // sends friend request
        driver.get("http://localhost:8080/users/name");
        driver.findElement(By.id("add-friend-button")).click();
        driver.get("http://localhost:8080/users/name");
        Assert.assertEquals(
            "Pending",
            driver.findElement(By.id("add-friend-button")).getAttribute("value")
        );
    }

    @Test
    public void rejectFriendRequest() {
        // login
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("name");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // get friend requests
        driver.get("http://localhost:8080/users/name");
        Assert.assertEquals(
            "user2",
            driver.findElement(By.id("friend-request-name")).getText()
        );
        // reject request
        driver.findElement(By.id("reject-request-button")).click();
        driver.get("http://localhost:8080/users/name");
        Assert.assertEquals(
            true,
            driver.findElements(By.id("friend-request-name")).isEmpty()
        );
    }

    @Test
    public void acceptFriendRequest() {
        // login as requesting user
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("user2");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // sends friend request
        driver.get("http://localhost:8080/users/name");
        driver.findElement(By.id("add-friend-button")).click();
        // login as recipient
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("name");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // get friend requests
        driver.get("http://localhost:8080/users/name");
        Assert.assertEquals(
            "user2",
            driver.findElement(By.id("friend-request-name")).getText()
        );
        // accept request
        driver.findElement(By.id("accept-request-button")).click();
        driver.get("http://localhost:8080/users/name");
        // check that user2 is now a friend
        Assert.assertEquals(
            "user2",
            driver.findElement(By.id("friend-name")).getText()
        );
        // login as user 2
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("user2");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.tagName("button")).click();
        // check that name is now a friend
        driver.get("http://localhost:8080/users/name");
        Assert.assertEquals(
            "You're friends!",
            driver.findElement(By.id("is-friends")).getText()
        );
    }
}
