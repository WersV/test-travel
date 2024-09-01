package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Objects;

public class DriverFactory {

    public static WebDriver getDriver() throws IOException {

        String driverType = PropertiesLoader.loadProperty("browser.name");

        WebDriver driver = null;

        if (Objects.equals(driverType, "chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } else if (Objects.equals(driverType, "firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
