package ge.tbc.testautomation.gettingStarted_L11;

import static ge.tbc.testautomation.data.Constants.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationTests {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void sliderTest(){
        driver.get(JQUERY_BASE_URL);
//        driver.navigate().to(SLIDER);
//        driver.navigate().back();
//        driver.navigate().refresh();
        WebElement sliderLink = driver.findElement(By.xpath("//a[text()='Slider']"));
        sliderLink.click();
        WebElement sliderFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(sliderFrame);
        WebElement sliderHandle = driver.findElement(By.cssSelector("span.ui-slider-handle"));
        Actions actions = new Actions(driver);
        int x = 0;
        while (x < 10){
            actions.
                    clickAndHold(sliderHandle)
                    .moveByOffset(10, 0)
                    .release()
                    .perform();
            x++;
        }

        driver.switchTo().defaultContent();
    }

    @Test
    public void sliderDescriptionTest(){
        driver.get(JQUERY_BASE_URL);
        WebElement sliderLink = driver.findElement(By.xpath("//a[text()='Slider']"));
        sliderLink.click();
        WebElement sliderDescription = driver.findElement(By.cssSelector("p.desc"));

        String sliderDescriptionText = sliderDescription.getText();
        Assert.assertEquals(sliderDescriptionText, SLIDER_DESCRIPTION);
    }

    @Test
    public void navigationTest4(){

    }

//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }
}
