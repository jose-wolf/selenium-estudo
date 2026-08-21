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


            Cookie cookie = new Cookie("MeuCookie", "teste123");
            navegador.manage().addCookie(cookie);

            Set<Cookie> cookies = navegador.manage().getCookies();

            for(Cookie cookieListAntes : cookies) {
                System.out.println(cookieListAntes);
            }

            Cookie encontrado =
                    navegador.manage()
                            .getCookieNamed("MeuCookie");

            assertEquals(
                    "teste123",
                    encontrado.getValue()
            );

            System.out.println(cookies.size());

            navegador.manage().deleteCookieNamed("session-username");

            Set<Cookie> cookiesDepois =
                    navegador.manage().getCookies();

            for(Cookie cookieListDepois : cookiesDepois) {
                System.out.println(cookieListDepois);
            }
            System.out.println(cookiesDepois.size());


        } finally {
            navegador.quit();
        }
    }

}