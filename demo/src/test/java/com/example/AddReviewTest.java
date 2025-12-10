package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddReviewTest extends BaseTest {

    @Test
        void adicionarReviewProduto() {
            

            driver.get("https://demowebshop.tricentis.com");

            

            // LOGA
            driver.findElement(By.className("ico-login")).click();
            driver.findElement(By.id("Email")).sendKeys("testeselenium@example.com");
            driver.findElement(By.id("Password")).sendKeys("123456");
            driver.findElement(By.cssSelector("input.button-1.login-button")).click();

            // GARANTE LOGIN
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ico-logout")));

            // BUSCA PRODUTO
            driver.findElement(By.id("small-searchterms"))
                    .sendKeys("$25 Virtual Gift Card");
            driver.findElement(By.cssSelector("input.button-1.search-box-button"))
                    .click();

            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("$25 Virtual Gift Card")))
                    .click();

            driver.findElement(By.linkText("Add your review")).click();

            // AGORA OS CAMPOS ESTÃO HABILITADOS
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AddProductReview_Title")))
                    .sendKeys("Ótimo Produto");

            driver.findElement(By.id("AddProductReview_ReviewText"))
                    .sendKeys("Excelente qualidade!");

            driver.findElement(By.id("addproductrating_5")).click();
            driver.findElement(By.name("add-review")).click();

            WebElement mensagem = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".result"))
            );

            assertTrue(mensagem.getText().contains("successfully added"));
    }

}
