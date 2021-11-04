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
import org.openqa.selenium.support.pagefactory.ByAll;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)

public class PostsTest {

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
  public void successfulPost() {
    //signup
    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    //sign in
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    //making post
    driver.findElement(By.id("content")).sendKeys("5G for the win in vaccines");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("5G for the win in vaccines"));    
  }

  //Test dateAppears
  //Test nameAppears
  //Test pictureAppears
  //Test postsShownInReverseChronologicalOrder
}


