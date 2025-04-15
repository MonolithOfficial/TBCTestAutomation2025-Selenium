package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.pages.SupportPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupportSteps extends BaseStep {
    SupportPage supportPage = new SupportPage();
    JavascriptExecutor js;
    WebDriverWait wait;

    public SupportSteps(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    public String fillSupportInputAndReturnSuggestion(String message){
        WebElement supportInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(supportPage.supportInput));
        supportInputElement.sendKeys(message);

        WebElement suggestion = supportInputElement
                .findElement(By.xpath(String
                        .format("following::div[contains(@data-testid, '%s')]//p", message)));

        // ლექციაზე რომ პრობლემა იყო. სელენიუმის getText-მა არ იმუშავა, მაგრამ ჯავასრიპტით ეს სოლიშენი მუშაობს
        return (String) js.executeScript("return arguments[0].innerText", suggestion);
    }
}
