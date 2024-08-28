package pl.seleniumdemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

public class BaseTest extends DriverFactory {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = getDriver("firefox");
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }

}
