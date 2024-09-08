package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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
        ExtentTest test = extentReports.createTest("SignUpTest");
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        test.log(Status.PASS, "Setting first and last name");
        user.setPhoneNum("123456789");
        test.log(Status.PASS, "Setting phone number");
        user.setEmail(randomEmailSeed()+"@gmail.com");
        test.log(Status.PASS, "Setting email");
        user.setPassword("admin1");
        user.setConfirmPassword("admin1");
        test.log(Status.PASS, "Setting password");

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterSignUpPage();
        signUpPage.fillSignUpForm(user);
        signUpPage.performSignUp();
        test.log(Status.PASS, "Performing signing up");
        signUpPage.checkNameAfterSignUp();
    }

    @Test
    public void signUpWithNoDataInTheForm() {
        ExtentTest test = extentReports.createTest("SignUpWithNoDataTest");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterSignUpPage();
        test.log(Status.PASS, "Entering page");
        List<String> errorList = signUpPage.signUpWithNoDataInTheForm();
        System.out.println(errorList);
    }
}
