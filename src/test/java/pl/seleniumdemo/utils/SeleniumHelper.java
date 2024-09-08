package pl.seleniumdemo.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SeleniumHelper {

    public static void waitForElementToExist(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> presenceOfAllElementsLocatedBy(WebDriver driver, By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static Media getScreenshot(WebDriver driver) throws IOException {
        String path = takeScreenshoot(driver);
        return MediaEntityBuilder.createScreenCaptureFromPath(path).build();
    }

    public static String takeScreenshoot(WebDriver driver) throws IOException {
        int randomNum = (int) (Math.random()*1000);
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/" + randomNum + "screen.png";
        FileUtils.copyFile(srcFile, new File(path));
        return path;
    }
}
