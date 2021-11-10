import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class ProfilePhotoTest {
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
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        

    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void uploadPhotoSignIn() {
        // Find test photo location of parrot on your local machine
        String photoPath = System.getProperty("user.dir")+"/src/test/java/images/parrot.png";
        // Choose the test photo to upload as your profile photo
        WebElement addFile = driver.findElement(By.id("photo"));
        addFile.sendKeys(photoPath);
        driver.findElement(By.id("submit")).click();
        // Wait 2 seconds incase any delay in sending it to AWS S3
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        // Sign in
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        // Check that the image is displayed, i.e. that it has dimensions
        Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@class, 'logged_in_user_profile_photo')]")).getSize().getWidth() > 1);
    }
}
