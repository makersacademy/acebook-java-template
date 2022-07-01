import com.github.javafaker.Faker;
import com.makersacademy.acebook.Application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PostLoad;

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

public class EmptyPostTest {

    WebDriver driver;
    Faker faker;
    String username;
    String password;
    String testText;

    @Before
    public void setup() {
      System.setProperty("webdriver.chrome.driver", "/Program Files/chromedriver/chromedriver.exe");
      driver = new ChromeDriver();
      faker = new Faker();
      username = faker.name().firstName();
      password= "mypassword";   
      testText = "    "; 
    } 

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testPostingEmptyString(){
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //Sign up as username
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();
      //signed in as username

      List<WebElement> postsList = driver.findElements(By.className("post-class"));
      //get number of posts
      driver.findElement(By.id("submit")).click();
      //submit empty post

      List<WebElement> newPostsList = driver.findElements(By.className("post-class"));
      Assert.assertEquals(postsList.size(), newPostsList.size());
      //check that the number of posts hasn't changed
    }

    @Test
    public void testPostingStringOfOnlySpace(){
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //Sign up as username
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();
      //signed in as username

      List<WebElement> postsList = driver.findElements(By.className("post-class"));
      //get number of posts
      driver.findElement(By.id("content-input")).sendKeys(testText);
      driver.findElement(By.id("submit")).click();
      //submit a post that only contains spaces

      List<WebElement> newPostsList = driver.findElements(By.className("post-class"));
      Assert.assertEquals(postsList.size(), newPostsList.size());
      //check that the number of posts hasn't changed
    }
}
