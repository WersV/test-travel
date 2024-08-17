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
    public void searchHotel() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        //type city name
        driver.get("http://www.kurs-selenium.pl/demo/");
        WebElement searchInput = driver.findElement(By.xpath("//input[contains(@class, 'select2-focusser')]"));
        searchInput.sendKeys("Dubai");
        String confirmElement = "//span[@class='select2-match' and text()='Dubai']";
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmElement)));
        driver.findElement(By.xpath(confirmElement)).click();

        //set dates by writing dates
        WebElement checkInInput = driver.findElement(By.name("checkin"));
//        checkInInput.sendKeys("22/08/2024");
        WebElement checkOutInput = driver.findElement((By.name("checkout")));
//        checkOutInput.sendKeys("30/08/2024");

        //set dates by using datepicker
        checkInInput.click();
        driver.findElement(By.xpath("//div[@class='datepicker-days']//td[text()='22']")).click();
//        driver.findElements(By.xpath("//div[@class='datepicker-days']//td[text()='30']")).stream().filter(el -> el.isDisplayed()).skip(1).findFirst().ifPresent(el -> el.click());
        driver.findElements(By.xpath("//div[@class='datepicker-days']//td[not(contains(@class, 'disabled')) and text()='30']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click()); //without skip() method
    }
}
