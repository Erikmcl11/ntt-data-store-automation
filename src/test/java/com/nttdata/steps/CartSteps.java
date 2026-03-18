package com.nttdata.steps;

import com.nttdata.page.CartPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void validatePopupVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.modal));
    }

    public void validatePopupTotal(double unitPrice, int quantity) {
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.modalTotalPrice)).getText();
        double expectedTotal = unitPrice * quantity;
        double actualTotal = parsePrice(totalText);
        Assertions.assertEquals(expectedTotal, actualTotal, 0.05,
                "El monto total en el popup no coincide. Esperado: " + expectedTotal + " | Actual: " + actualTotal);
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(CartPage.checkoutButton)).click();
    }

    public String getCartPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.cartPageTitle));
        return driver.findElement(CartPage.cartPageTitle).getText();
    }

    public void validateCartTotal(double unitPrice, int quantity) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CartPage.cartTotalPrice));
        String totalText = driver.findElement(CartPage.cartTotalPrice).getText();
        double expectedTotal = unitPrice * quantity;
        double actualTotal = parsePrice(totalText);
        Assertions.assertEquals(expectedTotal, actualTotal, 0.05,
                "El monto total en el carrito no coincide. Esperado: " + expectedTotal + " | Actual: " + actualTotal);
    }

    private double parsePrice(String priceText) {
        String cleaned = priceText.replaceAll("[^0-9.,]", "");
        if (cleaned.contains(",") && !cleaned.contains(".")) {
            cleaned = cleaned.replace(",", ".");
        } else {
            cleaned = cleaned.replace(",", "");
        }
        return Double.parseDouble(cleaned);
    }
}
