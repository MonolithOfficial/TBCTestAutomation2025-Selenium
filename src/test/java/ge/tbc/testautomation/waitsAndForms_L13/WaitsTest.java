package ge.tbc.testautomation.waitsAndForms_L13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

//    @Test
//    public void implicitWaitTest() {
//        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
//        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
//        enableButton.click();
//        WebElement enabledText = driver.findElement(By.id("message"));
//        Assert.assertEquals(enabledText.getText(), "It's enabled!");
//    }

    @Test
    public void explicitWait() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        WebElement enabledText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertEquals(enabledText.getText(), "It's enabled!");

        WebElement input = driver.findElement(By.id("input-example"));
        boolean isInputEnabled = false;

        isInputEnabled = wait.until(element -> {
            if (input.isEnabled()){
                return true;
            }
            else {
                return false;
            }
        });
        Assert.assertTrue(isInputEnabled);
    }

    @Test
    public void testFluentWait() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100)) // POLLING MECHANISM
                .ignoring(NoSuchElementException.class);

        WebElement enableButton =
                driver.findElement(
                        By.xpath("//form[@id='input-example']/button[@type='button'"));
        enableButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("message")));
        WebElement message = driver.findElement(By.id("message"));
        message.click();
    }
}
