package ge.tbc.testautomation.lecture_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JSExecutor {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.techlistic.com/2017/02/automate-demo-web-table-with-selenium.html");
    }

    @Test
    public void scrollTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0, 1000)");

        WebElement popularTutorials = driver.findElement(By.xpath("//span[text()='Popular Tutorials']"));
        js.executeScript("arguments[0].scrollIntoView()", popularTutorials);

        String documentLocation = (String) js.executeScript("return document.location.href");
        System.out.println(documentLocation);
    }
}
