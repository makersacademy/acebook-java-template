import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import java.util.List;
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

public class EditPostTest {

    WebDriver driver;
    Faker faker;
    String username;
    String password;
    String testText;
    String editText;
    String id;

    @Before
    public void setup() {
      System.setProperty("webdriver.chrome.driver", "/Program Files/chromedriver/chromedriver.exe");
      driver = new ChromeDriver();
      faker = new Faker();
      username = faker.name().firstName();
      password= "mypassword";
      testText = "This text was made by EditPostTest";
      editText = "This text was Edited";
    } 

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void EditPost(){
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //Sign up as username
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();
      //signed in as username

      driver.findElement(By.id("content-input")).sendKeys(testText);
      driver.findElement(By.id("submit")).click();
      //inputs testText into the form and sumbits the post
      List<WebElement> postsList = driver.findElements(By.className("post-content")); 


      //Check last element on list is the post we posted
      WebElement firstPost = postsList.get(0);
      Assert.assertEquals(testText, firstPost.getText());

      driver.findElement(By.id("edit-link")).click();
      driver.findElement(By.id("edit-input")).clear();
      driver.findElement(By.id("edit-input")).sendKeys(editText);
      driver.findElement(By.id("submit-edit")).click();



      List<WebElement> editedPostsList = driver.findElements(By.className("post-content"));

      //check if last element on list is same with edited
      WebElement editedFirstPost = editedPostsList.get(0);
      Assert.assertEquals(editText, editedFirstPost.getText());

    }
}
