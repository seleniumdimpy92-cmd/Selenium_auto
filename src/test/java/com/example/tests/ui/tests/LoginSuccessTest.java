package com.example.tests.ui.tests;

import com.example.tests.ui.base.BaseTest;
import com.example.tests.ui.pages.LoginPage;
import org.openqa.selenium.By;   // <-- REQUIRED IMPORT
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSuccessTest extends BaseTest {

    @Test
    public void loginSuccess() {
        LoginPage page = new LoginPage(driver);
        page.open("https://the-internet.herokuapp.com/login");
        page.login("tomsmith", "SuperSecretPassword!");

        boolean redirected = driver.getCurrentUrl().contains("/secure");
        String flash = "";

        try { 
            flash = driver.findElement(By.id("flash")).getText(); 
        } catch (Exception ignore) {}

        Assert.assertTrue(
                redirected || flash.contains("You logged into a secure area!"),
                "Login did not succeed. Flash text: " + flash + 
                " | URL: " + driver.getCurrentUrl()
        );
    }
}

