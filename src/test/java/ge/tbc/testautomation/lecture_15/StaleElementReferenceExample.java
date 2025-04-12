package ge.tbc.testautomation.lecture_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class StaleElementReferenceExample {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void staleElementReferenceExceptionTest() {
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        WebElement disableButton = new
                WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[text()='Disable']")));
        System.out.println(disableButton.getText());
        driver.navigate().refresh();
        System.out.println(disableButton.getText());
    }
}
