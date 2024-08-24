package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchPage {

    @FindBy(xpath = "//input[contains(@class, 'select2-focusser')]")
    private WebElement searchInput;

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

    @FindBy(xpath = "//h2[text()='No Results Found']")
    private WebElement noResults;


    private final WebDriver driver;

    private final WebDriverWait wait;


    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    public void setCity(String cityName) {
        searchInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates(String checkInDate, String checkOutDate) {
        checkInInput.sendKeys(checkInDate);
        checkOutInput.sendKeys(checkOutDate);
    }

    private void addTraveller(WebElement traveller, int btnClickTimes) {
        for(int i = 0; i < btnClickTimes; i++) {
            wait.until(ExpectedConditions.visibilityOf(traveller));
            traveller.click();
        }
    }

    public void setTravellers(int adultBtnClickTimes, int childBtnClickTimes) {
        travellersInput.click();
        wait.until(ExpectedConditions.visibilityOf(adultPlusBtn));
        addTraveller(adultPlusBtn, adultBtnClickTimes);
        addTraveller(childPlusBtn, childBtnClickTimes);
    }

    public void performSearch() {
        searchBtn.click();
    }

    public List<String> findHotelNames() {
        return hotelNamesLocator.stream().map(el -> el.getAttribute("textContent")).collect(Collectors.toList());
    }

    public void SearchWithNoLocation(String checkInDate, String checkOutDate, int adultBtnClickTimes, int childBtnClickTimes) {
        setDates(checkInDate, checkOutDate);
        setTravellers(adultBtnClickTimes, childBtnClickTimes);
        performSearch();
        Assert.assertTrue(noResults.isDisplayed());
    }
}
