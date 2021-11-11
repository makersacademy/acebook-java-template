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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import org.openqa.selenium.WebElement;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentTest {
  WebDriver driver;
  Faker faker;
  public String name;

  @Before
  public void setup(){
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();
    faker = new Faker();

    name = faker.name().firstName();

    // user sign up
    signup(name, "password");


    //login
    login(name, "password");   
  }

  //defining some helper methods
  private void signup(String username, String password){
    driver.get("http://localhost:8080/users/new");
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("submit")).click();
  }

  private void login(String username, String password){
    driver.get("http://localhost:8080/login.html");
    driver.findElement(By.id("username")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.cssSelector("input[type='submit'][value='Log in']")).click();
  }

  private void logout(){
    driver.findElement(By.cssSelector("input[type='submit'][value='Sign Out']")).click();
  }

  private void post(String content){
    driver.findElement(By.id("content")).sendKeys(content);
    driver.findElement(By.cssSelector("input[type='submit'][value='Post']")).click();
  }


  @After
  public void teardown(){
    driver.close();
  }

  @Test
  public void userCommentOnOtherUserPost(){
    //Test 11
    post("Fruit doesn't taste nice");
    logout();

    String new_name = faker.name().firstName();
    String new_password = "magicword";
    signup(new_name, new_password);
    login(new_name, new_password);

    String comment = "I agree";
    driver.findElement(By.cssSelector("input[placeholder='Write a comment...']")).sendKeys(comment);
    driver.findElement(By.cssSelector("input[class='comment-button'][value='Comment']")).click();

    //selects the 2nd span in the 1st p of the 1st ul of class comment-post
    Assert.assertEquals(comment, driver.findElement(By.cssSelector("ul[class='comment-post']>p>span:nth-of-type(2)")).getText());
  }
}

