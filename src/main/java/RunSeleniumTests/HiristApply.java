package RunSeleniumTests;

import Commons.LoginHirist;
import Commons.Portals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class HiristApply {

    WebDriver driver = null;
    String qr = null;
    LoginHirist login = new LoginHirist();

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

        login.loginMethod(driver, Portals.HIRIST);
    }

}
