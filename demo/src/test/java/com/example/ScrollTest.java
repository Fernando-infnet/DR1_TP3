package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ScrollTest {

    @Test
    public void verificarScrollUpEDown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement header = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".main-header"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", header);
        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
    }

}
