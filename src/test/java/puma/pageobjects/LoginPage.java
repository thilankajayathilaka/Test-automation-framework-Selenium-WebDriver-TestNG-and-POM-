package puma.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Elements
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-test-id='auth-button-login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@data-test-id='login-form-error']")
    private WebElement loginErrorMessage;

    @FindBy(xpath = "//h1[@data-test-id='account-greeting']")
    private WebElement accountGreeting;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Enter email
    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // Enter password
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // Click login button
    public void clickLoginButton() {
        loginButton.click();
    }

    // Check if login failed by waiting for the error message
    public boolean isLoginFailed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
            return loginErrorMessage.isDisplayed();
        } catch (Exception e) {
            return false; // Error message not found, login likely succeeded
        }
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOf(accountGreeting));
            return accountGreeting.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

