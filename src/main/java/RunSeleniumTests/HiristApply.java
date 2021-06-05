package RunSeleniumTests;

import Commons.BaseClass;
import Commons.LoginHirist;
import Commons.Portals;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HiristApply extends BaseClass {

    //    WebDriver driver = null;
    String qr = null;
    LoginHirist login = new LoginHirist();


//    @BeforeClass
//    public void setUp() {
////        System.setProperty("webdriver.gecko.driver", "/Users/prateek/Downloads/geckodriver 2");
//        System.setProperty("webdriver.chrome.driver", "/Users/prateek/Downloads/chromedriver 5");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        options.addArguments("--start-maximized");
//        driver = new ChromeDriver(options);
//    }

    @Test
    public void EasyApply() throws Exception {

        extentTest = extent.createTest("Creating test");
        String[] inputs = new String[]{"Automation Testing", "QA Automation", "Test Automation", "SDET"};
        List<String> list = Arrays.asList(inputs);

        login.loginMethod(driver, Portals.HIRIST);
    }


}
