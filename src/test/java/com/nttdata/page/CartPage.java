package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {

    // Modal/Popup que aparece al agregar al carrito
    public static By modal           = By.id("blockcart-modal");
    public static By modalTotalPrice = By.cssSelector("#blockcart-modal span.value");
    public static By checkoutButton  = By.xpath("//*[@id='blockcart-modal']/div/div/div[2]/div/div[2]/div/div/a");

    // Pagina del carrito
    public static By cartPageTitle   = By.xpath("//*[@id='main']/div/div[1]/div/div[1]/h1");
    public static By cartTotalPrice  = By.xpath("//*[@id='main']/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");
}
