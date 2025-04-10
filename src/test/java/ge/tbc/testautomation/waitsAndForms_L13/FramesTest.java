package ge.tbc.testautomation.waitsAndForms_L13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class FramesTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/nested_frames");
    }

    @Test
    public void frameTest() {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        WebElement middleText = driver.findElement(By.xpath("//div[@id='content']"));
        System.out.println(middleText.getText());
        driver.switchTo().defaultContent(); // BACK TO SURFACE LEVEL
    }

    @Test
    public void testAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void windowTest() {
        driver.get("https://demoqa.com/browser-windows");
        WebElement windowButton = driver.findElement(By.id("windowButton"));
        String currentWindowHandle = driver.getWindowHandle();
        windowButton.click();

        Set<String> windows = driver.getWindowHandles();
        for (String tab :
                windows) {
            // SWITCH TO NEWLY OPENED TAB
            if (!tab.equalsIgnoreCase(currentWindowHandle)){
                System.out.println(tab);
                driver.switchTo().window(tab);
                WebElement sampleHeading = driver.findElement(By.id("sampleHeading"));
                System.out.println(sampleHeading.getText());
            }
        }
    }
}
