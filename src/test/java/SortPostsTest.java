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

public class SortPostsTest {

    WebDriver driver;
    Faker faker;
    String username;
    String password;
    String testTextOne;
    String testTextTwo;
    String id;

    @Before
    public void setup() {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
      driver = new ChromeDriver();
      faker = new Faker();
      username = faker.name().firstName();
      password= "mypassword";
      testTextOne = "1";
      testTextTwo = "2";     
    } 

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void deletePostsById(){
      driver.get("http://localhost:8080/users/new");
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.id("submit")).click();
      //Sign up as username
      driver.findElement(By.id("username")).sendKeys(username);
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.className("btn")).click();
      //signed in as username

      driver.findElement(By.id("content-input")).sendKeys(testTextOne);
      driver.findElement(By.id("submit")).click();
      driver.findElement(By.id("content-input")).sendKeys(testTextTwo);
      driver.findElement(By.id("submit")).click();
      //inputs testText into the form and sumbits the post
      List<WebElement> postsList = driver.findElements(By.className("post-class")); 
      List<String> postText = new ArrayList<String>();
      postsList.forEach((text) -> postText.add(text.getText()));

      Integer textOneIndex = postText.indexOf(testTextOne);
      Integer textTwoIndex = postText.indexOf(testTextTwo);
      Assert.assertTrue(textTwoIndex < textOneIndex);
       //checks that the post is on the page    
      // create another post (to create a more realistic scenario)
      // create new post
      // get list of ids of posts
      // get last post id (since latest post will always be last)
      // click delete button that has that id
      // refresh page
      // check that no post has that content + id
    }
}
