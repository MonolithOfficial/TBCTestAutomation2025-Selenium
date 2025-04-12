package ge.tbc.testautomation.lecture_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CookieTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void cookieTest() {
        Cookie namedCookie = driver.manage().getCookieNamed("optimizelyBuckets"); // GET
        System.out.println(namedCookie.getExpiry());

        Cookie newCookie = new Cookie("AutomatedCookie", "THIS PAGE IS BEING CONTROLLED BY OBAMA", "/", new Date(2026, Calendar.MAY, 12));
        driver.manage().addCookie(newCookie); // ADD

        driver.manage().deleteCookieNamed("rack.session");
    }


}
