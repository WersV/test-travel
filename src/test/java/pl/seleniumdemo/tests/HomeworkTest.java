package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HomeworkTest extends BaseTest{

    @Test
    public void searchWithEmptyLocation() {
        //homework - test search with no location
//        driver.findElement(By.xpath("//input[@name='checkin']")).click();
//        driver.findElement(By.xpath("//td[@class='day ' and text()='22']")).click();
//        driver.findElements(By.xpath("//td[@class='day ' and text()='31']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
//        driver.findElement(By.id("travellersInput")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("childPlusBtn")));
//        driver.findElement(By.id("childPlusBtn")).click();
//        driver.findElement(By.xpath("//button[text()=' Search']")).click();
//        driver.findElement(By.xpath("//h2[text()='No Results Found']"));

        //homework - try to sign up without passing data in the form
        driver.findElements(By.xpath("//li[@id='li_myaccount']//a[contains(text(),' My Account ')]")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        driver.findElements(By.xpath("//ul//a[text()='  Sign Up']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        // Set up an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Wait for the elements to be present
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'alert-danger')]//p")
        ));

        // Collect the text content of each element
        List<String> alertTexts = elements.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        // Print the resulting list
        System.out.println(alertTexts);

        Assert.assertEquals(alertTexts.get(0), "The Email field is required.");
        Assert.assertEquals(alertTexts.get(1), "The Password field is required.");
        Assert.assertEquals(alertTexts.get(2), "The Password field is required.");
        Assert.assertEquals(alertTexts.get(3), "The First name field is required.");
        Assert.assertEquals(alertTexts.get(4), "The Last Name field is required.");
    }
}
