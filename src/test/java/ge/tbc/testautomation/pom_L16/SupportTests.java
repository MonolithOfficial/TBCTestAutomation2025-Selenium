package ge.tbc.testautomation.pom_L16;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.BaseStep;
import ge.tbc.testautomation.steps.SupportSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SupportTests {
    WebDriver driver;
    BaseStep baseStep;
    SupportSteps supportSteps;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://swoop.ge");
        baseStep = new BaseStep(driver);
        supportSteps = new SupportSteps(driver);
    }

    @Test
    public void testAutoComplete() {
        baseStep
                .goToSupportPage();
        String result = supportSteps.fillSupportInputAndReturnSuggestion(Constants.AUTOCOMPLETE_SAMPLE);

        Assert.assertEquals(result, Constants.AUTOCOMPLETE_EXPECTED);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
