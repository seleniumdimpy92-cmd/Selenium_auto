package com.example.tests.ui.tests;

import com.example.tests.ui.base.BaseTest;
import com.example.tests.ui.pages.LoginPage;
import com.example.utils.CsvDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDataDrivenTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = CsvDataProvider.class)
    public void loginDataDriven(String user, String pass, String expected) {
        LoginPage page = new LoginPage(driver);
        page.open("https://the-internet.herokuapp.com/login");
        page.login(user, pass);
        if ("success".equalsIgnoreCase(expected)) {
            Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
        } else {
            Assert.assertTrue(page.isErrorDisplayed());
        }
    }
}
