package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpTest extends BaseTest {

    public String randomEmailSeed() {
        double randomNum = Math.random()*1000;
        int floorRandNum = (int)Math.floor(randomNum);
        return String.valueOf(floorRandNum);
    }

    @Test
    public void signUp() {
        //sign up
        driver.findElements(By.xpath("//li[@id='li_myaccount']//a[contains(text(),' My Account ')]")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        driver.findElements(By.xpath("//ul[@class='dropdown-menu']//a[text()='  Sign Up']")).stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());

        String firstName = "John";
        String lastName = "Doe";
        String emailRandomPart = randomEmailSeed();

        //fill sign up form
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(emailRandomPart + "@gmail.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("secretpass1");
        driver.findElement(By.xpath("//input[@name='confirmpassword']")).sendKeys("secretpass1");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Hi, ')]")));

        //check name after sign up
        String userNameCheck = driver.findElement(By.xpath("//h3[contains(text(), 'Hi, ')]")).getText();
        System.out.println(userNameCheck);

        Assert.assertTrue(userNameCheck.contains(firstName));
        Assert.assertTrue(userNameCheck.contains(lastName));
    }
}
