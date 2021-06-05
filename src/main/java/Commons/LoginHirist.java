package Commons;

import org.openqa.selenium.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginHirist {

    String setUrl = null;
    WebDriver driver = null;
    String id = null;

    public WebDriver loginMethod(WebDriver driver, Portals url) {

        try {
            login(driver, url);
            jobApply(driver);
            scrollDown(driver);

            //Get list count
            List<WebElement> list = driver.findElements(By.xpath("//div[@class='job-apply-checkbox']//label[contains(@for,'')]/preceding-sibling::input[@type='checkbox']"));
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

            scrollUp(driver);

            int i = 0;
            for (WebElement el : list) {

                id = list.get(i).getAttribute("id");
                System.out.println(id);
                el = driver.findElement(By.xpath("//label[contains(@for,'" + id + "')]"));
                boolean display = el.isDisplayed();
                boolean select = el.isSelected();
                boolean enable = el.isEnabled();

//                scroll slowly
                if (i > 2 && i % 5 == 0 || i % 3 == 0) {
                    driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
                    driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
                }

                try {
                    id = list.get(i).getAttribute("id");
                } catch (StaleElementReferenceException elementHasDisappeared) {
                    System.out.println(id);
                    el = driver.findElement(By.xpath("//label[contains(@for,'" + id + "')]"));
                }

                System.out.println(id);
                el = driver.findElement(By.xpath("//label[contains(@for,'" + id + "')]"));

                driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

                if (el.isEnabled() && !el.isSelected()) {
                    el.click();
                    driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
                }
                System.out.println("iterations :: " + i);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//button[@type='button' and @class='apply-all sc-kPVwWT ephOlD']")).click();

        return driver;
    }

    //   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
//            actions.moveToElement(el).click().perform();

    //  String id = driver.findElement(By.xpath("//label[contains(@for,'')]")).getAttribute("for");
    //System.out.println(id);

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

    public void jobApply(WebDriver driver) {
        driver.findElement(By.xpath("//i[@class='icon-login' and @style='padding-right: 8px;']")).click();
        driver.findElement(By.xpath("//span[@tabindex='0' and contains(.,'Sign In')]")).click();

        driver.findElement(By.xpath("//input[@type='text' and @name='email']")).click();
        driver.findElement(By.xpath("//input[@type='text' and @name='email']")).sendKeys("prateek.kukreja18@gmail.com");

        driver.findElement(By.xpath("//input[@type='password' and @name='password']")).click();
        driver.findElement(By.xpath("//input[@type='password' and @name='password']")).sendKeys("Free@123456");

        driver.findElement(By.xpath("//button[@type='submit' and @class='submit-button']")).click();

        //click on search
        driver.findElement(By.xpath("//a[@class='icon-search-big search-icon']")).click();

        driver.findElement(By.xpath("//input[@placeholder='Search Jobs' and @class='sc-jDwBTQ exUiQu']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search Jobs' and @class='sc-jDwBTQ exUiQu']")).sendKeys("Automation Testing");

        driver.findElement(By.xpath("//a[@class='sc-gqjmRU bNsBxT' and contains(.,'Any Exp. Level')]")).click();
        driver.findElement(By.xpath("//li[@class='sc-gzVnrw ilnPED' and contains(.,'7 - 10 yrs')]")).click();

        driver.findElement(By.xpath("//a[@class='icon-search-big sc-ifAKCX eZniBE']")).click();
    }

    public void scrollDown(WebDriver driver) {
        //scroll down
        for (int i = 0; i < 10; i++) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        }
    }

    public void scrollUp(WebDriver driver) {
        //scroll up
        for (int i = 0; i < 10; i++) {
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(document.body.scrollHeight, 0)");
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        }
    }

//    @AfterClass
//    public void After() {
//        driver.quit();
//    }
}
