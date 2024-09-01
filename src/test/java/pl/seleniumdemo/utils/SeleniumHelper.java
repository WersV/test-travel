package pl.seleniumdemo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
