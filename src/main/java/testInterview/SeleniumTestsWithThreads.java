package testInterview;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import groovyjarjarantlr4.v4.codegen.model.OutputFile;
import org.apache.commons.io.FileUtils;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SeleniumTestsWithThreads {

    WebDriver driver = null;

    @BeforeClass
    public void setUp() {
//        System.setProperty("webdriver.gecko.driver", "/Users/prateek/Downloads/geckodriver 2");
//        System.setProperty("webdriver.chrome.driver", "/Users/prateek/Downloads/chromedriver 5");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-notifications");
//        options.addArguments("--start-maximized");
//        driver = new ChromeDriver(options);
    }

    @Test
    public void ReadArticleOnReddit() throws Exception {

        System.out.println("In Read Method");
        System.setProperty("webdriver.gecko.driver", "/Users/prateek/Downloads/geckodriver 2");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new FirefoxDriver(options);

        driver.navigate().to("https://www.reddit.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element = driver.findElement(By.xpath("//h3[@class='_eYtD2XCVieq6emjKBH3m' and contains(text(),'Kartik Aryan')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    @Test
    public void CreateAndVerifyQRCode() throws Exception {

        System.out.println("In QR Method");
        System.setProperty("webdriver.chrome.driver", "/Users/prateek/Downloads/chromedriver 5");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        String qr = "kooxy";
        driver.navigate().to("http://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + qr);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Write QR to a File
        File filePath = driver.findElement(By.xpath("//img[contains(@style,'webkit')]")).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(filePath, new File("/Users/prateek/Downloads/testScreen.png"));

        //Read QR from a File
        BufferedImage bufferedImage = ImageIO.read(filePath);

        // process the image
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            String val = result.getText();
            Assert.assertEquals(qr, val);
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
        }
    }

    @AfterClass
    public void After() {
        driver.quit();
    }

}
