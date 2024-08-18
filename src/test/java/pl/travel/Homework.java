package pl.travel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework {

    @Test
    public void searchWithEmptyLocation() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");


        //homework - test search with no location
        driver.findElement(By.xpath("//input[@name='checkin']")).click();
        driver.findElement(By.xpath("//td[@class='day ' and text()='22']")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        driver.findElement(By.id("travellersInput")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("childPlusBtn")));
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        driver.findElement(By.xpath("//h2[text()='No Results Found']"));
    }
}
