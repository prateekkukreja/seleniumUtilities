package RunSeleniumTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SeleniumTestRun {

    WebDriver driver = null;
    String qr = null;

    @BeforeTest
    public void takeInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter input :: ");
        qr = scan.next();
        System.out.println("Input :: " + qr);
    }


    @BeforeClass
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "/Users/prateek/Downloads/geckodriver 2");
        System.setProperty("webdriver.chrome.driver", "/Users/prateek/Downloads/chromedriver 5");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void test1() throws Exception {
        driver.navigate().to("https://www.reddit.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element = driver.findElement(By.xpath("//h3[@class='_eYtD2XCVieq6emjKBH3m' and contains(text(),'Kartik Aryan')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    @Test
    public void test2() throws Exception {

        driver.navigate().to("http://api.qrserver.com/v1/create-qr-code/?size=150x150&data=");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element = driver.findElement(By.xpath("//h3[@class='_eYtD2XCVieq6emjKBH3m' and contains(text(),'Kartik Aryan')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    @AfterClass
    public void After() {
        driver.quit();
    }

}

