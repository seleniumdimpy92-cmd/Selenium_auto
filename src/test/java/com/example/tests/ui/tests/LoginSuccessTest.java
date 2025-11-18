package com.example.tests.ui.tests;

import com.example.tests.ui.base.BaseTest;
import com.example.tests.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSuccessTest extends BaseTest {

    @Test
    public void loginSuccess() {
        LoginPage page = new LoginPage(driver);
        page.open("https://the-internet.herokuapp.com/login");
        page.login("tomsmith", "SuperSecretPassword!");
        // verify successful login by URL or presence of logout button
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), "Expected secure page after login");
    }
}
