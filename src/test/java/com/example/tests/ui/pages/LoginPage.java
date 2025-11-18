package com.example.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

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
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
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
