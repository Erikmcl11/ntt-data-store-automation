package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {

    // Boton "Sign in" en la home page
    public static By signInButton   = By.xpath("//*[@id='_desktop_user_info']/div/a/span");

    // Formulario de login
    public static By emailInput     = By.id("field-email");
    public static By passwordInput  = By.id("field-password");
    public static By loginButton    = By.id("submit-login");

    // Mensaje de error cuando el login falla
    public static By errorMessage   = By.xpath("//*[@id='content']/section/div/ul/li");

    // Elemento que aparece SOLO cuando el login es exitoso (nombre de cuenta)
    public static By accountName    = By.xpath("//*[@id='_desktop_user_info']/div/a[2]/span");
}
