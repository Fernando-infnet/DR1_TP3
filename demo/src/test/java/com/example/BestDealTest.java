package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BestDealTest extends BaseTest {

    @Test
    void screenshotCheapestProduct() {

        driver.get("https://demowebshop.tricentis.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));

        List<WebElement> produtos = driver.findElements(By.cssSelector(".product-item"));
        WebElement maisBarato = null;
        double menorPreco = Double.MAX_VALUE;

        for (WebElement produto : produtos) {
            try {
                String precoTxt = produto.findElement(By.cssSelector(".prices")).getText();
                double preco = Double.parseDouble(precoTxt.replace("$", "").trim());

                if (preco < menorPreco) {
                    menorPreco = preco;
                    maisBarato = produto;
                }

            } catch (Exception ignored) {}
        }

        assertTrue(maisBarato != null);

        try {
            File dir = new File("screenshots");
            if (!dir.exists()) dir.mkdirs();
            File src = maisBarato.getScreenshotAs(OutputType.FILE);

            File dest = new File("screenshots/produto-mais-barato.png");
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot salvo em: " + dest.getAbsolutePath());
            System.out.println("Tamanho da imagem: " + dest.length() + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
