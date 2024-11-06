package puma.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@name='search']") // Example locator, replace with actual Puma search bar ID
    private WebElement searchBar;

    @FindBy(xpath = "//button[@data-test-id='search-button-nav']") // Example, replace with actual locator
    private WebElement searchButton;

    @FindBy(xpath = "//input[@data-test-id='search-flyout-form-input']")
    private WebElement popupSearchBar;

    @FindBy(xpath = "//span[@data-test-id='product-results']")
    private WebElement productResultsText;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String productName) {
        clickElement(searchButton); // Click to open the search popup
        wait.until(ExpectedConditions.visibilityOf(popupSearchBar)); // Wait for the popup input to appear
        enterText(popupSearchBar, productName); // Enter the search text
        popupSearchBar.submit(); // Submit
    }
    public boolean isSearchResultsCorrect(String searchTerm) {
        try {
            String resultsText = wait.until(ExpectedConditions.visibilityOf(productResultsText)).getText();
            System.out.println(resultsText.toLowerCase());
            System.out.println(searchTerm.toLowerCase());
            System.out.println(resultsText.toLowerCase().contains(searchTerm.toLowerCase()));
            return resultsText.toLowerCase().contains(searchTerm.toLowerCase());
        } catch (Exception e) {
            System.out.println("Error locating search results element: " + e.getMessage());
            return false;
        }
    }

    public boolean isSearchResultsVisible() {
        return isElementVisible(productResultsText); // `searchResultsText` should be defined in your page object for the message element
    }


    public void navigateToCategory(String categoryName) {
        // Placeholder: Add logic to navigate to a specific category
    }
}