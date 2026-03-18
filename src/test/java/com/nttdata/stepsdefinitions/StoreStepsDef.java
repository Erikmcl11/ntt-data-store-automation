package com.nttdata.stepsdefinitions;

import com.nttdata.steps.CartSteps;
import com.nttdata.steps.CategorySteps;
import com.nttdata.steps.StoreSteps;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.*;

public class StoreStepsDef {

    private WebDriver driver;
    private StoreSteps storeSteps;
    private CategorySteps categorySteps;
    private CartSteps cartSteps;
    private double unitPrice;
    private static final int QUANTITY = 2;

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_pagina_de_la_tienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/en/");
        storeSteps = new StoreSteps(driver);
        storeSteps.clickSignIn();
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        storeSteps = new StoreSteps(driver);
        storeSteps.login(usuario, clave);
        boolean loginOk = storeSteps.isLoginSuccessful();
        screenShot();
        Assertions.assertTrue(loginOk,
                "El login FALLO. Usuario invalido: " + usuario);
        // Limpiar carrito para que los calculos de precio sean correctos
        storeSteps.clearCart();
        driver.get("https://qalab.bensg.com/store/en/");
    }

    @Cuando("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
        categorySteps = new CategorySteps(driver);
        categorySteps.navigateToCategory(categoria, subcategoria);
        screenShot();
    }

    @Y("agrego 2 unidades del primer producto al carrito")
    public void agrego_2_unidades_del_primer_producto_al_carrito() {
        unitPrice = categorySteps.addFirstProductToCart(QUANTITY);
        cartSteps = new CartSteps(driver);
        screenShot();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        cartSteps.validatePopupVisible();
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        cartSteps.validatePopupTotal(unitPrice, QUANTITY);
        screenShot();
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
        cartSteps.proceedToCheckout();
        screenShot();
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String title = cartSteps.getCartPageTitle();
        Assertions.assertFalse(title.isEmpty(),
                "El titulo de la pagina del carrito esta vacio");
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        cartSteps.validateCartTotal(unitPrice, QUANTITY);
        screenShot();
    }
}
