package ge.tbc.testautomation.waitsAndForms_L13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class FormsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
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

    @Test
    public void nativeSelectTest() {
        driver.get("https://techcanvass.com/examples/register.html");
        Select select = new Select(driver.findElement(By.cssSelector("select[name='model']")));
        select.selectByValue("Mega123m");
        select.selectByVisibleText("Serene Pad 64G");
    }

    @Test
    public void customDropDownTest() {
        driver.get("https://ng-bootstrap.github.io/#/components/dropdown/examples");
        WebElement customDropDown = driver.findElement(By.id("dropdownBasic1"));
        customDropDown.click();
        List<WebElement> options = driver.findElements(By.xpath("//button[@id = 'dropdownBasic1']/following-sibling::div/button"));

        WebElement anotherActionOption = options.stream()
                .filter(webElement -> webElement.getText().equalsIgnoreCase("Another Action"))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("NO OPTION HAS BEEN FOUND"));
        anotherActionOption.click();
    }
}
