package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.signInButton)).click();
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.emailInput));
        driver.findElement(StorePage.emailInput).clear();
        driver.findElement(StorePage.emailInput).sendKeys(email);
        driver.findElement(StorePage.passwordInput).clear();
        driver.findElement(StorePage.passwordInput).sendKeys(password);
        driver.findElement(StorePage.loginButton).click();
    }

    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.accountName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clearCart() {
        driver.get("https://qalab.bensg.com/store/en/cart");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        List<WebElement> deleteButtons = driver.findElements(
            By.cssSelector("a[data-link-action='delete-from-cart']")
        );
        while (!deleteButtons.isEmpty()) {
            WebElement firstButton = deleteButtons.get(0);
            firstButton.click();
            try {
                wait.until(ExpectedConditions.stalenessOf(firstButton));
            } catch (Exception ignored) {}
            deleteButtons = driver.findElements(
                By.cssSelector("a[data-link-action='delete-from-cart']")
            );
        }
    }
}
