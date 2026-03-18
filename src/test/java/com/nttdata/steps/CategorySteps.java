package com.nttdata.steps;

import com.nttdata.page.CategoryPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategorySteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private double unitPrice;

    public CategorySteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToCategory(String category, String subcategory) {
        // Espera explicita: espera que el link de categoria sea clickeable
        WebElement catLink = wait.until(
                ExpectedConditions.elementToBeClickable(CategoryPage.categoryLink(category))
        );
        catLink.click();

        // Espera explicita: espera que aparezca la subcategoria
        WebElement subCatLink = wait.until(
                ExpectedConditions.elementToBeClickable(CategoryPage.subcategoryLink(subcategory))
        );
        subCatLink.click();
    }

    public double addFirstProductToCart(int quantity) {
        // Click en el primer producto para ir a su pagina de detalle
        wait.until(ExpectedConditions.elementToBeClickable(CategoryPage.firstProduct)).click();

        // Leer precio desde la pagina de detalle
        WebElement priceEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CategoryPage.detailPagePrice)
        );
        String priceText = priceEl.getAttribute("content");
        if (priceText == null || priceText.isEmpty()) {
            priceText = priceEl.getText();
        }
        this.unitPrice = parsePrice(priceText);

        // Ajustar la cantidad
        WebElement qtyInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(CategoryPage.quantityInput)
        );
        qtyInput.sendKeys(Keys.CONTROL + "a");
        qtyInput.sendKeys(String.valueOf(quantity));

        // Agregar al carrito
        wait.until(
                ExpectedConditions.elementToBeClickable(CategoryPage.addToCartButton)
        ).click();

        return this.unitPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    private double parsePrice(String priceText) {
        // Limpia el texto: quita simbolos de moneda, espacios, etc.
        String cleaned = priceText.replaceAll("[^0-9.,]", "");
        // Si tiene coma (formato europeo ej: "18,99") la convierte a punto
        if (cleaned.contains(",") && !cleaned.contains(".")) {
            cleaned = cleaned.replace(",", ".");
        } else {
            // Si tiene ambas (ej: "1,234.56") quita la coma de miles
            cleaned = cleaned.replace(",", "");
        }
        return Double.parseDouble(cleaned);
    }
}
