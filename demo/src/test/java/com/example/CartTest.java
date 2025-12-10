package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CartTest extends BaseTest {

    @Test
    public void adicionarGiftCardCarrinho() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/25-virtual-gift-card");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("giftcard_2_RecipientName")).sendKeys("John Doe");
        driver.findElement(By.id("giftcard_2_RecipientEmail")).sendKeys("john@example.com");
        driver.findElement(By.id("giftcard_2_SenderName")).sendKeys("Me");
        driver.findElement(By.id("giftcard_2_SenderEmail")).sendKeys("me@example.com");
        driver.findElement(By.id("giftcard_2_Message")).sendKeys("Enjoy!");

        WebElement qty = driver.findElement(By.id("addtocart_2_EnteredQuantity"));
        qty.clear();
        qty.sendKeys("1");
        driver.findElement(By.id("add-to-cart-button-2")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("cart-qty"),
                "(1)"
        ));

        String cartQty = driver.findElement(By.className("cart-qty")).getText();
        Assertions.assertEquals("(1)", cartQty, "Cart quantity should be (1)");

        driver.quit();
    }
}
