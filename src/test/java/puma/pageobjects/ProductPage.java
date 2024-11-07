package puma.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[contains(@data-test-id, 'add-to-cart-button')]") // Locator for the 'Add to Cart' button
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(text(), 'Added to Cart')]") // Locator to confirm product added to cart
    private WebElement confirmationMessage;

    @FindBy(xpath = "//h1[contains(@data-test-id, 'pdp-title')]") // Locator for the 'Add to Cart' button
    private WebElement productTitle;

    @FindBy(xpath = "//div[@data-test-id = 'pdp-add-to-cart-error']") // Locator for the 'Add to Cart' button
    private WebElement addToCartErrorMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        try {
            // Scroll to the 'Add to Cart' button
            scrollToElement(addToCartButton);

            // Wait until the button is clickable
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

            // Click the button using JavaScript if the regular click fails
            try {
                addToCartButton.click();
            } catch (ElementClickInterceptedException e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", addToCartButton);
            }
        } catch (Exception e) {
            System.out.println("Failed to add to cart: " + e.getMessage());
        }
    }


    public boolean isProductAddedToCart() {
        return isElementVisible(confirmationMessage,Duration.ofSeconds(5));
    }

    public boolean isProductAddedToCartError() {
        return isElementVisible(addToCartErrorMessage,Duration.ofSeconds(5));
    }
}
