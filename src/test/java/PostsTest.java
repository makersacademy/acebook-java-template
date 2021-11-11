import java.util.List;

import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.openqa.selenium.support.pagefactory.ByAll;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    // signup
    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

  }

  @After
  public void tearDown() {
    driver.close();
  }

  @Test
  public void deletePost() {

    // making posts
    driver.findElement(By.id("content")).sendKeys("Happy days");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.id("content")).sendKeys("Good evening!");
    driver.findElement(By.id("submit")).click();

    // delete post
    List<WebElement> a = driver.findElements(By.id("delete"));
    a.get(0).click();
    driver.switchTo().alert().accept(); // handle popup window

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertFalse("checks that the post appears", bodyText.contains("Good evening!"));
    Assert.assertTrue("checks that the second post appears", bodyText.contains("Happy days"));

  }

  @Test
  public void deleteOnlyPostAssociatedToCurrentUser() {
    // post 1
    // signup
    driver.get("http://localhost:8080/users/new");
    String name1 = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name1);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name1);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    // making posts
    driver.findElement(By.id("content")).sendKeys("Hello, this is a test for the first user");
    driver.findElement(By.id("submit")).click();

    // post 2
    // signup
    driver.get("http://localhost:8080/users/new");
    String name2 = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name2);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name2);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    // making posts
    driver.findElement(By.id("content")).sendKeys("Hello, this is a test for the second user");
    driver.findElement(By.id("submit")).click();

    // delete post
    // List<WebElement> a = driver.findElements(By.id("delete"));
    // a.get(0).click();
    driver.findElement(By.id("delete")).click();
    driver.switchTo().alert().accept(); // handle popup window

    String bodyText = driver.findElement(By.tagName("body")).getText();
    String name = driver.findElement(By.id("username")).getText();

    Assert.assertFalse("checks that current user's post appears",
        bodyText.contains("Hello, this is a test for the second user"));
    Assert.assertFalse("checks that current username appears", name.contains(name2));
    Assert.assertTrue("checks that the first post appears",
        bodyText.contains("Hello, this is a test for the first user"));
    Assert.assertTrue("checks that the first username appears", bodyText.contains(name1));

  }

  @Test
  public void DatabaseDateTest(){
    LocalDateTime now = LocalDateTime.now();  
    driver.findElement(By.id("content")).sendKeys("What is the time?");
    driver.findElement(By.id("submit")).click();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");  
    String time = dtf.format(now).toString();  

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("Date is added to database", bodyText.contains(time));
  }

  @Test
  public void successfulPost() {

    // making post
    driver.findElement(By.id("content")).sendKeys("5G for the win in vaccines");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("5G for the win in vaccines"));
  }

  @Test
  public void twoPostsAppear() {

    // making first post
    driver.findElement(By.id("content")).sendKeys("The weather today is sunny!");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the initial post appears", bodyText.contains("The weather today is sunny!"));

    // making second post
    driver.findElement(By.id("content")).sendKeys("Hello World!");
    driver.findElement(By.id("submit")).click();

    String bodyText1 = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the initial post appears", bodyText1.contains("The weather today is sunny!"));
    Assert.assertTrue("checks that the second post appears", bodyText1.contains("Hello World!"));
  }

  // Test dateAppears
  // Test nameAppears
  // Test pictureAppears
  // Test postsShownInReverseChronologicalOrder

  @Test
  public void addsNameAgainstPost() {
    // signup
    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    // making post
    driver.findElement(By.id("content")).sendKeys("This is a test");
    driver.findElement(By.id("submit")).click();

    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the post appears", bodyText.contains("This is a test"));
    Assert.assertTrue("checks that the name appears", bodyText.contains(name));
  }

  @Test
  public void uploadAndShowPicture() {
    // post 1
    // signup
    driver.get("http://localhost:8080/users/new");
    String name = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    // making post
    driver.findElement(By.id("content")).sendKeys("This is post 1");
    driver.findElement(By.id("submit")).click();

    // post 2
    // signup
    driver.get("http://localhost:8080/users/new");
    String name1 = faker.name().firstName();
    driver.findElement(By.id("username")).sendKeys(name1);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("submit")).click();

    // sign in
    driver.findElement(By.id("username")).sendKeys(name1);
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.xpath("//button")).click();

    // making post
    driver.findElement(By.id("content")).sendKeys("This is post 2");
    driver.findElement(By.id("submit")).click();

    // assertions
    String bodyText = driver.findElement(By.tagName("body")).getText();
    Assert.assertTrue("checks that the first post appears", bodyText.contains("This is post 1"));
    Assert.assertTrue("checks that the first name appears", bodyText.contains(name));
    Assert.assertTrue("checks that the second post appears", bodyText.contains("This is post 2"));
    Assert.assertTrue("checks that the second name appears", bodyText.contains(name1));
  }

  @Test
  public void uploadAndShowContentPicture() {
    // making post
    driver.findElement(By.id("content")).sendKeys("This is an image");
    URL url = getClass().getResource("static/images/BigDuck.jpg");
    driver.findElement(By.id("file")).sendKeys(url.getPath());
    driver.findElement(By.id("submit")).click();

    WebElement text = driver.findElement(By.className("contentImage"));
    Assert.assertNotNull(text);
  }
}