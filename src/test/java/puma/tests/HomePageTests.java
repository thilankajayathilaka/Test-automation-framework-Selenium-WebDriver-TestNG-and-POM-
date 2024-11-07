package puma.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import puma.pageobjects.HomePage;
import puma.pageobjects.ProductPage;
import puma.utilities.BrowserFactory;

public class HomePageTests {
    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;

    @Parameters("browserType")
    @BeforeClass
    public void setUp(String browserType) {
        driver = BrowserFactory.startBrowser(browserType);
        driver.get("https://us.puma.com/us/en"); // Open Puma website
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        homePage.acceptCookies();
    }

    @Test
    public void testSearchFunctionality() {
        homePage.searchProduct("shoes"); // Example search term
        Assert.assertTrue(homePage.isSearchResultsCorrect("shoes"), "Search results page did not display the correct term.");

        // Click the first product and initialize ProductPage
        homePage.clickFirstItem();

        // Try to add to cart and verify the error message appears
        productPage.addToCart();
        Assert.assertTrue(productPage.isProductAddedToCartError(), "Product added to cart without selecting a size. Error message should be displayed.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}