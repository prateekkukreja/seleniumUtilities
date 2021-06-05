package Commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver = null;
    String qr = null;
    public ExtentReports extent;
    public ExtentTest extentTest;
    public ExtentHtmlReporter htmlReporter;

    @BeforeClass
    public void beforeClass() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/extent.html");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automated Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.setSystemInfo("Prateek", "Automation");
        extent.setSystemInfo("Browser", "Chrome");
        extent.attachReporter(htmlReporter);

        //        System.setProperty("webdriver.gecko.driver", "/Users/prateek/Downloads/geckodriver 2");
        System.setProperty("webdriver.chrome.driver", "/Users/prateek/Downloads/chromedriver 4");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        try {
            String methodName = result.getMethod().getMethodName();
            if (result.getStatus() == ITestResult.FAILURE) {
                String exceptionMsg = Arrays.toString(result.getThrowable().getStackTrace());
                extentTest.fail("<details><summary><b><font color=red>Exception occured, click to see details:"
                        + "</font></b></summary>" + exceptionMsg.replaceAll(",", "<br>")
                        + "</details> \n");

                String path = takeScreenshot(result.getMethod().getMethodName());
                try {
                    extentTest.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                } catch (Exception e) {
                    e.printStackTrace();
                    extentTest.fail("Test failed, cannot attach SS");
                }
                String logtest = "<b>Test Method " + methodName + " Failed </b>";
                Markup m = MarkupHelper.createLabel(logtest, ExtentColor.RED);
                extentTest.log(Status.FAIL, m);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                String logtest = "<b>Test Method " + methodName + " Passed </b>";
                Markup m = MarkupHelper.createLabel(logtest, ExtentColor.GREEN);
                extentTest.log(Status.PASS, m);
            } else if (result.getStatus() == ITestResult.SKIP) {
                String logtest = "<b>Test Method " + methodName + " Skipped </b>";
                Markup m = MarkupHelper.createLabel(logtest, ExtentColor.YELLOW);
                extentTest.log(Status.SKIP, m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getScreenShotName(String methodName) {
        Date d = new Date();
        String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
        return fileName;
    }

    private String takeScreenshot(String methodName) {
        String fileName = getScreenShotName(methodName);
        File filepath = new File(System.getProperty("user.dir") + "/screenshots/");
        String path = filepath + "/" + fileName;

        try {
            File scrshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrshot, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    String setUrl;

    public void login(WebDriver driver, Portals url) {

        try {
            switch (url) {
                case LINKEDIN:
                    setUrl = "https://www.linkedin.com/login?fromSignIn=true";
                    break;
                case HIRIST:
                    setUrl = "https://www.hirist.com/jobfeed";
                    break;
                case GITHUB:
                    setUrl = "https://github.com/prateekkukreja";
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


    @AfterTest
    public void endReport() {
        extent.flush();
        driver.quit();
    }

}
