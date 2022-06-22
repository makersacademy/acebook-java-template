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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class DeletePostTest {

    WebDriver driver;
    Faker faker;
    String username;
    String password;
    String text;
    String id;

    @Before
    public void setup() {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
      driver = new ChromeDriver();
      faker = new Faker();
      username = faker.name().firstName();
      password= "mypassword";
      text = "Maven is working";
      id = "1";
    } 

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void updatePosts(){
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //Sign up as username
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();
      //signed in as username
      WebElement post = driver.findElement(By.id(id));
      Assert.assertEquals(text, post.getText());
      // driver.findElement(By.id("edit")).click();
      //List<WebElement> editLinks = driver.findElements(By.className("edit-link"));
      // create another post (to create a more realistic scenario)
      // find all edit links use latest to edit
      // click edit go to new page with form for editing
      // form should be pre-populated with current value for content
      // change the content
      // submit the form
      // expect the page to have the edited content
    }
}
