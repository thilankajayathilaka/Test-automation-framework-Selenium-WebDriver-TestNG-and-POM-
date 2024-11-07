package puma.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puma.pageobjects.LoginPage;
import puma.utilities.BrowserFactory;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.startBrowser("chrome"); // Use your browser type
        driver.get("https://us.puma.com/us/en/account/login?from=account");
        loginPage = new LoginPage(driver);
    }

    // Data provider for login test data
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"testuser1@example.com", "password1"},
                {"testuser2@example.com", "password2"},
                {"invaliduser@example.com", "wrongpassword"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        // Add assertions based on expected outcome
        // Example: Verify login success/failure message
        Assert.assertTrue(loginPage.isLoginButtonVisible(), "Login button should be visible if login failed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
