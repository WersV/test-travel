package pl.travel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HotelSearch {

    @Test
    public void searchHotel() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
//        WebElement searchInput = driver.findElement(By.className("select2-focusser")); //alternative way of searching for this input
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class, 'select2-focusser')]"));
        searchInput.sendKeys("Dubai");

        String confirmElement = "//span[@class='select2-match' and text()='Dubai']";

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmElement)));

        driver.findElement(By.xpath(confirmElement)).click();

    }
}
