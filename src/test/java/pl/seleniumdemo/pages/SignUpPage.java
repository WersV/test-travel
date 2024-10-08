package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage extends SeleniumHelper {

    @FindBy(xpath = "//li[@id='li_myaccount']//a[contains(text(),' My Account ')]")
    private List<WebElement> myAccMenu;

    @FindBy(xpath = "//ul[@class='dropdown-menu']//a[text()='  Sign Up']")
    private List<WebElement> signUpOption;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneNum;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@name='confirmpassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpBtn;

    @FindBy(xpath = "//h3[contains(text(), 'Hi, ')]")
    private WebElement userName;

    private final WebDriver driver;

//    private final WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
//        this.wait = new WebDriverWait(driver, 10);
    }

    public void enterSignUpPage() {
        myAccMenu.stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        signUpOption.stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
    }

    public void fillSignUpForm(User user) {
        this.firstName.sendKeys(user.getFirstName());
        this.lastName.sendKeys(user.getLastName());
        this.phoneNum.sendKeys(user.getPhoneNum());
        this.email.sendKeys(user.getEmail());
        this.password.sendKeys(user.getPassword());
        this.confirmPassword.sendKeys(user.getConfirmPassword());
    }

    public void performSignUp() {
        signUpBtn.click();
    }

    public void checkNameAfterSignUp() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), 'Hi, ')]")));
        waitForElementToExist(this.driver,By.xpath("//h3[contains(text(), 'Hi, ')]"), 10);
        Assert.assertTrue(userName.getText().contains(this.firstName.getAttribute("value")));
        Assert.assertTrue(userName.getText().contains(this.lastName.getAttribute("value")));
    }

    public List<String> signUpWithNoDataInTheForm() {
        myAccMenu.stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        signUpOption.stream().filter(el -> el.isDisplayed()).findFirst().ifPresent(el -> el.click());
        performSignUp();
        List<WebElement> elements = presenceOfAllElementsLocatedBy(this.driver, By.xpath("//div[contains(@class, 'alert-danger')]//p"), 10);
        List<String> alertTexts = elements.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        return alertTexts;
    }
}
