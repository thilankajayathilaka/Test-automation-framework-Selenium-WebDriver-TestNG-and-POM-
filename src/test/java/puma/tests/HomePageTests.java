package puma.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
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
    public void setUp(@Optional("chrome") String browserType) { // Default to Chrome if not specified
        driver = BrowserFactory.startBrowser(browserType);
        driver.get("https://us.puma.com/us/en"); // Open Puma website
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        homePage.acceptCookies();
    }

    @Test
    public void testProductSearch() {
        // Search for a product
        homePage.searchProduct("shoes");
        Assert.assertTrue(homePage.isSearchResultsCorrect("shoes"), "Search results page did not display the correct term.");
    }

    @Test(dependsOnMethods = "testProductSearch")
    public void testSelectFirstProduct() {
        // Click on the first product in the search results
        homePage.clickFirstItem();

        // Verify navigation to product page
        Assert.assertTrue(productPage.isProductPageLoaded(), "Product page did not load correctly.");
    }

    @Test(dependsOnMethods = "testSelectFirstProduct")
    public void testAddToCartWithoutSizeSelection() {
        // Try to add product to cart without selecting a size
        productPage.addToCart();

        // Verify that an error message appears
        Assert.assertTrue(productPage.isProductAddedToCartError(), "Error message did not appear when adding product to cart without selecting a size.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
