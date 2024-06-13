//import com.github.javafaker.Faker;
//import com.makersacademy.acebook.Application;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class SignUpTest {
//
//    WebDriver driver;
//    Faker faker;
//
//    @Before
//    public void setup() {
////        use environment variable to get chromedriver location
////        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//////        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER_LOCATION"));
//////        DesiredCapabilities capabilities = new DesiredCapabilities();
//////        capabilities.setCapability("chrome.options", new ChromeOptions().addArguments("--allowed-ips=18.196.138.205\t"));
//////        WebDriver driver = new ChromeDriver(capabilities);
//////        ChromeOptions options = new ChromeOptions();
//////        options.addArguments("--headless");
//////        options.addArguments("--no-sandbox");
//////        options.addArguments("--disable-dev-shm-usage");
////        driver = new ChromeDriver();
//////        driver = new ChromeDriver();
////        faker = new Faker();
//        // Set the path to the ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//
//        // Configure Chrome options
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Run in headless mode for CI environments
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//
//        // Initialize the WebDriver
//        driver = new ChromeDriver(options);
//    }
//
//    @After
//    public void tearDown() {
//        driver.close();
//    }
//
//    @Test
//    public void successfulSignUpRedirectsToSignIn() {
//        driver.get("http://localhost:8080/");
//        driver.findElement(By.id("username")).sendKeys(faker.name().firstName());
//        driver.findElement(By.id("password")).sendKeys("password");
//        driver.findElement(By.id("submit")).click();
//        String title = driver.getTitle();
//        Assert.assertEquals("Please sign in", title);
//    }
//
//    @Test
//    public void successfulSignUpRedirectsToWelcome() {
//        // ... rest of the test code
//        String title = driver.getTitle();
//        Assert.assertEquals("Welcome", title); // assuming "Welcome" is the title of the welcome page
//    }
//}
//
//
//
