package br.com.josewolf.saucedemo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

            System.out.println("Título da página: " + tituloProducts.getText());
            assertEquals("Products", tituloProducts.getText());
            System.out.println("classe da página: " + tituloProducts.getAttribute("class"));

            navegador.findElement(By.className("shopping_cart_link"));
            navegador.findElement(By.id("react-burger-menu-btn"));
            navegador.findElement(By.cssSelector("[data-test='social-linkedin']"));
            navegador.findElement(By.tagName("a"));
            navegador.findElement(By.linkText("Facebook"));


            List<WebElement> produtos = navegador.findElements(By.cssSelector("[data-test='inventory-item']"));

            System.out.println(produtos.size());
            assertEquals(6, produtos.size());


            WebElement addProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']")
            ));
            boolean visivel = addProduct.isDisplayed();
            System.out.println(visivel);
            boolean botaoInterativo  = addProduct.isEnabled();
            System.out.println(botaoInterativo);
            boolean botaoSelecionado = addProduct.isSelected();
            System.out.println(botaoSelecionado);
            addProduct.click();



            WebElement badgeCarrinho = navegador.findElement(
                    By.cssSelector("[data-test='shopping-cart-badge']")
            );
            System.out.println("Itens no carrinho: " + badgeCarrinho.getText());
            assertEquals("1", badgeCarrinho.getText());

            WebElement removeProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='remove-sauce-labs-backpack']")
            ));
            removeProduct.click();

            WebElement linkedinLink = navegador.findElement(
                    By.cssSelector("[data-test='social-linkedin']")
            );
            String linkedDestino = linkedinLink.getAttribute("href");
            System.out.println(linkedDestino);
            String tagName = linkedinLink.getTagName();
            assertEquals(tagName, "a");

            WebElement facebookLink = navegador.findElement(
                    By.cssSelector("[data-test='social-facebook']")
            );
            String facebookDestino = facebookLink.getAttribute("href");
            System.out.println(facebookDestino);
            tagName = facebookLink.getTagName();
            assertEquals(tagName, "a");
        } finally {
            navegador.quit();
        }
    }
}