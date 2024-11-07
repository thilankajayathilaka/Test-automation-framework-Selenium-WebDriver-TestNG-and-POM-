package puma.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import puma.pageobjects.HomePage;
import puma.pageobjects.LoginPage;
import puma.utilities.BrowserFactory;
import puma.utilities.ExcelDataProvider;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.startBrowser("chrome");
        driver.get("https://us.puma.com/us/en/account/login?from=account");
        loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        return ExcelDataProvider.readExcelData("src/test/resources/loginData.xlsx");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, boolean expectedSuccess) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        if (expectedSuccess) {
            Assert.assertTrue(loginPage.isLoggedIn(), "Expected login to succeed but it failed.");
        } else {
            Assert.assertTrue(loginPage.isLoginFailed(), "Expected login to fail but it succeeded.");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
