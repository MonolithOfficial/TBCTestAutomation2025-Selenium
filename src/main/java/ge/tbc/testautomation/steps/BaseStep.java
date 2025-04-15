package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseStep {
    protected BasePage basePage = new BasePage();
    protected WebDriver driver;

    public BaseStep(WebDriver driver) {
        this.driver = driver;
    }

    public BaseStep goToMainPage(){
        WebElement mainLogoElement = driver.findElement(basePage.mainLogo);
        mainLogoElement.click();

        return this;
    }

    public BaseStep goToSupportPage(){
        WebElement supportPageLinkElement = driver.findElement(basePage.supportPageLink);
        supportPageLinkElement.click();

        return this;
    }
}
