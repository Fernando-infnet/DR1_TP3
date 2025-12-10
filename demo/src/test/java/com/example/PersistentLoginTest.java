package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersistentLoginTest extends BaseTest {

    @Test
    void loginPersistenteECheckout() throws Exception {

        driver.get("https://demowebshop.tricentis.com");

        CookieUtils.loadCookies(driver);

        driver.navigate().refresh();

        boolean estaLogado;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-logout")));
            estaLogado = true;
        } catch (Exception e) {
            estaLogado = false;
        }

        if (!estaLogado) {
            driver.findElement(By.className("ico-login")).click();
            driver.findElement(By.id("Email")).sendKeys("testeselenium@example.com");
            driver.findElement(By.id("Password")).sendKeys("123456");
            driver.findElement(By.cssSelector("input.button-1.login-button")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-logout")));
            
            CookieUtils.saveCookies(driver);
        }

        driver.findElement(By.linkText("Books")).click();
        driver.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
        driver.findElement(By.className("ico-cart")).click();

        assertTrue(driver.getPageSource().contains("Shopping cart"));
    }
}
