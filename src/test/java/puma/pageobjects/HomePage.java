package puma.pageobjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@name='search']") // Example locator, replace with actual Puma search bar ID
    private WebElement searchBar;

    @FindBy(xpath = "//button[@data-test-id='search-button-nav']") // Example, replace with actual locator
    private WebElement searchButton;

    @FindBy(xpath = "//input[@data-test-id='search-flyout-form-input']")
    private WebElement popupSearchBar;

    @FindBy(xpath = "//span[@data-test-id='product-results']")
    private WebElement productResultsText;

    @FindBy(xpath = "//ul[@data-test-id='product-list-items']/li[1]" )
    private WebElement firstItem;

    @FindBy(xpath = "//h1[contains(@data-test-id, 'pdp-title')]") // Locator for the 'Add to Cart' button
    private WebElement productTitle;

    @FindBy(xpath = "//*[@id=\"puma-skip-here\"]/div[2]/div/div/div/button[2]") // Replace with actual locator
    private WebElement acceptCookiesButton;

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
            return resultsText.toLowerCase().contains(searchTerm.toLowerCase());
        } catch (Exception e) {
            System.out.println("Error locating search results element: " + e.getMessage());
            return false;
        }
    }

    public boolean isSearchResultsVisible() {
        return isElementVisible(productResultsText, Duration.ofSeconds(10)); // `searchResultsText` should be defined in your page object for the message element
    }

    public void clickFirstItem() {
        scrollToElement(firstItem);
        clickElement(firstItem);
    }

    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int retries = 3;

        for (int i = 0; i < retries; i++) {
            try {
                WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
                cookieButton.click();
                break; // Exit loop if click is successful
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException encountered. Retrying...");
            }
        }
    }



    public void navigateToCategory(String categoryName) {
        // Placeholder: Add logic to navigate to a specific category
    }
}