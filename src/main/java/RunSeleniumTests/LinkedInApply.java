package RunSeleniumTests;

import Commons.LoginLinkedIn;
import Commons.Portals;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkedInApply {

    WebDriver driver = null;
    String qr = null;
    LoginLinkedIn login = new LoginLinkedIn();

    @BeforeTest
    public void takeInput() {
        System.out.println("Test Started");
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
    public void EasyApply() throws Exception {

        String[] inputs = new String[]{"Automation Testing", "QA Automation", "Test Automation", "SDET"};
        List<String> list = Arrays.asList(inputs);

        login.loginMethod(driver, Portals.LINKEDIN);

        driver.findElement(By.xpath("//span[@class='global-nav__primary-link-text' and contains(.,'Jobs')]")).click();
        int i = 0;
        while (i < inputs.length) {
            driver.findElement(By.xpath("//input[@id='jobs-search-box-keyword-id-ember2033']")).click();
            driver.findElement(By.xpath("//input[@id='jobs-search-box-keyword-id-ember2033']")).sendKeys(list.get(0));
            i++;
        }
    }

    //    @Test
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
