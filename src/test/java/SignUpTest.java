import static org.junit.Assert.assertTrue;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;

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
public class SignUpTest {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthoritiesRepository authRepo;

    private WebDriver driver;
    private String fakeUser;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        fakeUser = new Faker().name().username();
        authRepo.deleteAll();
        userRepo.deleteAll();
    }

    @Test
    public void successfulSignUpRedirectsToSignIn() {
        this.createUser();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }

    @Test
    public void successfulSignUpAddsUserToAuthAndUserTable() {
        this.createUser();
        assertTrue(userRepo.existsUserByUsername(fakeUser));
        assertTrue(authRepo.existsAuthorityByUsername(fakeUser));
    }

    private void createUser() {
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(fakeUser);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
