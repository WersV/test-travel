package pl.seleniumdemo.tests;

import org.testng.annotations.*;
import pl.seleniumdemo.model.User;
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

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhoneNum("123456789");
        user.setEmail(randomEmailSeed()+"@gmail.com");
        user.setPassword("admin1");
        user.setConfirmPassword("admin1");

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterSignUpPage();
        signUpPage.fillSignUpForm(user);
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
