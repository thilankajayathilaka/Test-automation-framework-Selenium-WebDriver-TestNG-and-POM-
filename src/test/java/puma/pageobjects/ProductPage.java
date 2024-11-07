package puma.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//button[contains(@data-test-id, 'add-to-cart-button')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(text(), 'Added to Cart')]")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//h1[contains(@data-test-id, 'pdp-title')]")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@data-test-id = 'pdp-add-to-cart-error']")
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
            addToCartButton.click();
        } catch (Exception e) {
            System.out.println("Failed to add to cart: " + e.getMessage());
        }
    }

    public boolean isProductAddedToCartError() {
        return isElementVisible(addToCartErrorMessage,Duration.ofSeconds(5));
    }
    public boolean isProductPageLoaded() {
        return isElementVisible(productTitle,Duration.ofSeconds(5));
    }
}