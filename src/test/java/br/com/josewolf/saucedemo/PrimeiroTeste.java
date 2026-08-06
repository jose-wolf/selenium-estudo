package br.com.josewolf.saucedemo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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


        try {
            navegador.get("https://www.saucedemo.com/");


            String tituloDaPagina = navegador.getTitle();

            navegador.findElement(By.name("user-name"));
            navegador.findElement(By.name("login-button"));


            assertEquals("Swag Labs", tituloDaPagina);
        } finally {
            navegador.quit();
        }
    }
}