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

    @FindBy(xpath = "//button[@data-test-id='search-button-nav']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@data-test-id='search-flyout-form-input']")
    private WebElement popupSearchBar;

    @FindBy(xpath = "//span[@data-test-id='product-results']")
    private WebElement productResultsText;

    @FindBy(xpath = "//ul[@data-test-id='product-list-items']/li[1]" )
    private WebElement firstItem;

    @FindBy(xpath = "//*[@id=\"puma-skip-here\"]/div[2]/div/div/div/button[2]")
    private WebElement acceptCookiesButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String productName) {
        clickElement(searchButton);
        wait.until(ExpectedConditions.visibilityOf(popupSearchBar));
        enterText(popupSearchBar, productName);
        popupSearchBar.submit();
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
}