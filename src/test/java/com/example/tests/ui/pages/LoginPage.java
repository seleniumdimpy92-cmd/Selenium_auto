package com.example.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class LoginPage {
    private WebDriver driver;
    private By username = By.id("username");
    private By password = By.id("password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By flash = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void login(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).clear();
        driver.findElement(username).sendKeys(user);

        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).clear();
        driver.findElement(password).sendKeys(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

        // wait for flash message or redirect
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("/secure"),
                ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
            ));
        } catch (Exception e) {
            // ignore
        }
    }

    public boolean isErrorDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(flash));
            String txt = driver.findElement(flash).getText();
            return txt.toLowerCase().contains("invalid") || txt.toLowerCase().contains("your username is invalid");
        } catch (Exception e) {
            return false;
        }
    }
}
