package com.nttdata.page;

import org.openqa.selenium.By;

public class CategoryPage {

    // Menu de navegacion superior
    public static By categoryLink(String category) {
        return By.linkText(category.toUpperCase());
    }

    // Subcategoria en el menu
    public static By subcategoryLink(String subcategory) {
        return By.linkText(subcategory);
    }

    // Primer producto en el listado
    public static By firstProduct      = By.xpath("//*[@id='js-product-list']/div[1]/div/article/div/div[2]/h2/a");

    // Precio en pagina de DETALLE del producto
    public static By detailPagePrice   = By.cssSelector("span.current-price-value");

    public static By quantityInput      = By.id("quantity_wanted");
    public static By addToCartButton    = By.xpath("//*[@id='add-to-cart-or-refresh']/div[2]/div/div[2]/button");
}
