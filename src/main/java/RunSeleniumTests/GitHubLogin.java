package RunSeleniumTests;

import Commons.BaseClass;
import Commons.Portals;
//import com.asprise.ocr.Ocr;
import com.asprise.ocr.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GitHubLogin extends BaseClass {

    @Test
    public void landOnGithub() {


        try {
            extentTest = extent.createTest("Login to github");
            login(driver, Portals.GITHUB);

            String val = driver.getTitle();
            Assert.assertTrue(val.toLowerCase(Locale.ROOT).contains("github"));

            int i = 0;
            while (i < 100) {
                driver.navigate().refresh();
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                i++;
            }

            String imageUrl = driver.findElement(By.xpath("//a[@rel='noopener noreferrer']//img[contains(@alt,'Count')]")).getAttribute("src");
            System.out.println("Image source path : \n" + imageUrl);

            driver.get(imageUrl);
            val = driver.getTitle();
//            URL url = new URL(imageUrl);
//            Image image = ImageIO.read(url);
//            String s = new OCR().recognizeCharacters((RenderedImage) image);
//            System.out.println("Text From Image : \n" + s);
//            System.out.println("Length of total text : \n" + s.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
