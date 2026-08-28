package br.com.josewolf.saucedemo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        WebDriverWait wait = new WebDriverWait(navegador, Duration.ofSeconds(5));
        try {
            navegador.get("https://www.saucedemo.com/");

            WebElement campoUsuario = navegador.findElement(By.id("user-name"));
            WebElement campoPassword = navegador.findElement(By.id("password"));

            campoUsuario.sendKeys("standard_user");
            campoPassword.sendKeys("secret_sauce");

            WebElement botao =
                    navegador.findElement(By.id("login-button"));


            botao.click();

            String janelaAtual = navegador.getWindowHandle();
            System.out.println(janelaAtual);


            WebElement linkLinkedin = navegador.findElement(By.cssSelector("[data-test='social-linkedin']"));
            linkLinkedin.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> janelas = navegador.getWindowHandles();
            for (String janela : janelas) {
                if(!janela.equals(janelaAtual)) {
                    System.out.println(janela);
                    navegador.switchTo().window(janela);
                    break;
                }
            }

            wait.until(
                    ExpectedConditions.urlContains(
                            "https://www.linkedin.com/company/sauce-labs/"
                    )
            );

            String urlAtual = navegador.getCurrentUrl();

            assertTrue(
                    urlAtual.contains("https://www.linkedin.com/company/sauce-labs/")
            );

            navegador.close();

            navegador.switchTo().window(janelaAtual);
            String urlVolta = navegador.getCurrentUrl();
            assertEquals("https://www.saucedemo.com/inventory.html", urlVolta);

        } finally {
            navegador.quit();
        }
    }

}