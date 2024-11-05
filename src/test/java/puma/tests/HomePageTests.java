package puma.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import puma.pageobjects.HomePage;
import puma.utilities.BrowserFactory;

public class HomePageTests {
    private WebDriver driver;
    private HomePage homePage;

    @Parameters("browserType")
    @BeforeClass
    public void setUp(String browserType) {
        driver = BrowserFactory.startBrowser(browserType);
        driver.get("https://us.puma.com/us/en"); // Open Puma website
        homePage = new HomePage(driver);
    }

    @Test
    public void testSearchFunctionality() {
        homePage.searchProduct("shoes"); // Example search term
        // Verification could be URL contains the search term or a specific element appears
        Assert.assertTrue(driver.getCurrentUrl().contains("search?q=shoes"), "Search results page did not load correctly.");
    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser();
    }
}