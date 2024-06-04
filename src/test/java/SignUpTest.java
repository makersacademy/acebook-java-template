import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpTest {

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
    public void successfulSignUpRedirectsToSignIn() {
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }

    @Test
    public void testUsernameAlreadyTakenReturnsErrorReturnToSignUp(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys("johndoe");
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("submit")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Username already exists.", alertText); //Check alert message correct
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/new")); //Check return to sign up page
    }

    @Test
    public void testEmailAlreadyTakenReturnsErrorReturnToSignUp(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys("john.doe@example.com");
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("submit")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Email already exists.", alertText); //Check alert message correct
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/new")); //Check return to sign up page
    }

    @Test
    public void testEmailInWrongFormatReturnsErrorMessageReturnToSignUp(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys("johndoe1mail.com");
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("Password123!");
        driver.findElement(By.id("submit")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Invalid email address.", alertText); //Check alert message correct
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/new")); //Check return to sign up page
    }

    @Test
    public void testPasswordsDoNotMatchReturnsErrorReturnToSignUp(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("Password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("Password1234!");
        driver.findElement(By.id("submit")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Passwords do not match or are invalid.", alertText); //Check alert message correct
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/new")); //Check return to sign up page
    }

    @Test
    public void testPasswordsMatchButWrongFormatReturnsErrorReturnToSignUp(){
        driver.get("http://localhost:8080/users/new");
        driver.findElement(By.id("username")).sendKeys(faker.name().username());
        driver.findElement(By.id("firstname")).sendKeys(faker.name().firstName());
        driver.findElement(By.id("lastname")).sendKeys(faker.name().lastName());
        driver.findElement(By.id("email")).sendKeys(faker.internet().emailAddress());
        driver.findElement(By.id("bio")).sendKeys(faker.lorem().sentence());
        driver.findElement(By.id("password")).sendKeys("password123!");
        driver.findElement(By.id("confirm_password")).sendKeys("password123!");
        driver.findElement(By.id("submit")).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals("Passwords do not match or are invalid.", alertText); //Check alert message correct
        alert.accept();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/users/new")); //Check return to sign up page
    }
}
