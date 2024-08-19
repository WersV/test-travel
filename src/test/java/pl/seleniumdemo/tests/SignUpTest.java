package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    public String randomEmailSeed() {
        double randomNum = Math.random()*1000;
        int floorRandNum = (int)Math.floor(randomNum);
        return String.valueOf(floorRandNum);
    }

    @Test
    public void signUp() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterSignUpPage();
        signUpPage.fillSignUpForm("John", "Doe", "123456789", randomEmailSeed()+"@gmail.com", "admin1", "admin1");
        signUpPage.performSignUp();
        signUpPage.checkNameAfterSignUp();
    }
}
