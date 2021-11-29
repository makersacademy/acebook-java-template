import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.transform.Result;
import java.sql.*;

public class TestHelper {

    WebDriver driver;
    Faker faker;
    String name;

   // @Autowired
   // PasswordEncoder encoder;

    public String getName() {
        return name;
    }

    public void setup() throws SQLException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
        DriverManager.registerDriver(new Driver());
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/acebook_springboot_test");
        String password = "Password";
        String passwordEncoded = encoder.encode(password);
        String query = "INSERT INTO users(username,password,enabled) VALUES('Testuser','"+passwordEncoded+"',true)";
        Statement result = con.createStatement();
        ResultSet rs = result.executeQuery(query);
        con.close();

    }
    public void signUpAndIn(){
        // setup();
        driver = new ChromeDriver();
        faker = new Faker();
        driver.get("http://localhost:8080/users/new");
        name = faker.name().firstName();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[contains(text(), 'Log In')]")).click();
    }
}
