package ge.tbc.testautomation.lecture_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowser {
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser){
        System.out.println(browser);

        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void inputTest() {
        driver.get("https://techcanvass.com/examples/register.html");
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@value='First Name']"));
        firstNameInput.sendKeys("I AM GURAMI");
    }

    @Test
    public void radioButtonTest() {
        driver.get("https://techcanvass.com/examples/register.html");
        WebElement femaleRadioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='female']"));
        if (!femaleRadioButton.isSelected()){
            femaleRadioButton.click();
        }
    }
}
