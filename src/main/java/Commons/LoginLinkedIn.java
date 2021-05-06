package Commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.AfterClass;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginLinkedIn {

    String setUrl = null;
    WebDriver driver = null;

    public WebDriver loginMethod(WebDriver driver, Portals url) {

        login(driver, url);

        driver.findElement(By.xpath("//input[@id='username']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("prateek.kukreja18@gmail.com");

        driver.findElement(By.xpath("//input[@id='password']")).click();
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Deutsche@9876");

        driver.findElement(By.xpath("//button[contains(@class, 'btn__primary--large from__button--floating')]")).click();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.navigate().to("https://messages.google.com/web/conversations");

        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement SMS;
        SMS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ng-star-inserted' and @dir='auto' and contains(.,'LinkedIn')]")));

        //Read Span texts
        driver.findElement(By.xpath("//span[@class='ng-star-inserted' and @dir='auto' and contains(.,'LinkedIn')]")).click();

        List<WebElement> messages = driver.findElements(By.xpath("//div[@class='text-msg ng-star-inserted' and contains(.,'LinkedIn verification code')][last()]"));
        WebElement lastMessage = messages.get(messages.size() - 1);
        String[] tmp = lastMessage.getText().split(" ");
        int len = tmp.length;
        String otp = tmp[len - 1];
        System.out.println(otp);

        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.xpath("//input[@id='input__phone_verification_pin' and @class='form__input--text input_verification_pin']")).click();
        driver.findElement(By.xpath("//input[@id='input__phone_verification_pin' and @class='form__input--text input_verification_pin']")).sendKeys(otp);
        driver.findElement(By.xpath("//button[@id='two-step-submit-button' and @class='form__submit form__submit--stretch']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='global-nav__primary-link-text' and contains(.,'Jobs')]")));
        return driver;
    }

    public void login(WebDriver driver, Portals url) {

        try {
            switch (url) {
                case LINKEDIN:
                    setUrl = "https://www.linkedin.com/login?fromSignIn=true";
                    break;
                case HIRIST:
                    setUrl = "https://www.hirist.com/jobfeed";
                    break;
                default:
                    setUrl = "https://www.google.com";
            }
            driver.navigate().to(setUrl);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            System.out.println("opening website :: " + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @AfterClass
//    public void After() {
//        driver.quit();
//    }
}
