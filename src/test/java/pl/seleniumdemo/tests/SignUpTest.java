package pl.seleniumdemo.tests;

import org.testng.annotations.*;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

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

    @Test
    public void signUpWithNoDataInTheForm() {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterSignUpPage();
        List<String> errorList = signUpPage.signUpWithNoDataInTheForm();
        System.out.println(errorList);
    }
}
