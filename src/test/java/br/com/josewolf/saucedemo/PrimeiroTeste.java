package br.com.josewolf.saucedemo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeiroTeste {

    @Test
    void deveAbrirSauceDemo() {

        FirefoxOptions options = new FirefoxOptions();

        options.setBinary(
                "/snap/firefox/current/usr/lib/firefox/firefox"
        );

        options.addArguments("--width=1920");
        options.addArguments("--height=1000");

        WebDriver navegador = new FirefoxDriver(options);

        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(2));
        try {
            navegador.get("https://www.saucedemo.com/");

            WebElement campoUsuario = navegador.findElement(By.id("user-name"));
            WebElement campoPassword = navegador.findElement(By.id("password"));

            campoUsuario.sendKeys("standard_user");
            campoPassword.sendKeys("secret_sauce");

            WebElement botao =
                    navegador.findElement(By.id("login-button"));

            botao.click();

            WebElement tituloProducts = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("title")
                    )
            );

            assertEquals("Products", tituloProducts.getText());
        } finally {
            navegador.quit();
        }
    }
}