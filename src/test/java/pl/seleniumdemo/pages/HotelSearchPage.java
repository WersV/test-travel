package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchPage {

    @FindBy(xpath = "//input[contains(@class, 'select2-focusser')]")
    private WebElement searchInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement confirmElement;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultInput")
    private WebElement adultInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//h4[contains(@class, 'list_title')]//b")
    private List<WebElement> hotelNamesLocator;

    private final WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void setCity(String cityName) {
        searchInput.sendKeys(cityName);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-match' and text()='Dubai']")));
        confirmElement.click();
    }

    public void setDates(String checkInDate, String checkOutDate) {
        checkInInput.sendKeys(checkInDate);
        checkOutInput.sendKeys(checkOutDate);
    }

    public void setTravellers() {
        travellersInput.click();
        Assert.assertEquals(adultInput.getAttribute("value"), "2");
        adultPlusBtn.click();
        childPlusBtn.click();
    }

    public void performSearch() {
        searchBtn.click();
    }

    public List<String> findHotelNames() {
        return hotelNamesLocator.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }
}
