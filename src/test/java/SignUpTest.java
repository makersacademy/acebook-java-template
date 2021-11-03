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
import org.openqa.selenium.support.pagefactory.ByAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
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
        String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }

    @Test
    public void successfulSignInAfterSignUp(){
        //signup
        driver.get("http://localhost:8080/users/new");
        String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        //sign in
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button")).click(); //how to click?
        String title = driver.getTitle();
        Assert.assertEquals("Acebook", title);
    }

    @Test
    public void unsuccessfulSignInRedirectsToSignIn(){
        //signin with unknown account
        driver.get("http://localhost:8080/");
        String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("wordpass");
        driver.findElement(By.xpath("//button")).click();
        
        //check still on sign in page
        String title = driver.getTitle();
        Assert.assertEquals("Please sign in", title);
    }

    @Test
    public void unsuccessfulSignInGivesWarningMessage(){
        //signin with unknown account
        driver.get("http://localhost:8080/");
        String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("wordpass");
        driver.findElement(By.xpath("//button")).click();

        //check message Bad credentials appears
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("checks it warns credentials are bad", bodyText.contains("Bad credentials"));
    }

    @Test
    public void successfulSignInAfterUnsuccessfulSignIn(){
        //signup
        driver.get("http://localhost:8080/users/new");
        String name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();

        //signin with wrong password
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("wordpass");
        driver.findElement(By.xpath("//button")).click();

        //sign in
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button")).click(); //how to click?
        String title = driver.getTitle();
        Assert.assertEquals("Acebook", title);
    }



}
