import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.postgresql.Driver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHelper {

    WebDriver driver;
    Faker faker;
    String name;


    public String getName() {
        return name;
    }

    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        faker = new Faker();
    }

    public void signIn() throws SQLException {
        runSqlSetUp();
        setup();
        name = "TestUser";
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("username")).sendKeys(name);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[contains(text(), 'Log In')]")).click();
    }

    public void runSqlSetUp() throws SQLException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        DriverManager.registerDriver(new Driver());
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/acebook_springboot_test");
        runSqlUsers(encoder, con);
        runSqlAuthorities(encoder, con);
        con.close();
    }

    public void runSqlUsers(PasswordEncoder encoder, Connection con) throws SQLException {
        String password = "password";
        String passwordEncoded = encoder.encode(password);
        String query = "INSERT INTO users(username,password,enabled) VALUES('TestUser','" + passwordEncoded + "',true)";
        Statement result = con.createStatement();
        result.executeUpdate(query);
    }

    public void runSqlAuthorities(PasswordEncoder encoder, Connection con) throws SQLException {
        Statement result = con.createStatement();
        String query = "INSERT INTO authorities (authority,username) VALUES('ROLE_USER','TestUser')";
        result.executeUpdate(query);
    }
}
