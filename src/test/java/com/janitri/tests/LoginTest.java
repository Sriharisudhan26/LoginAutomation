package com.janitri.tests;

import com.janitri.base.BaseTest;
import com.janitri.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.UUID;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginButtonDisabledWhenFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.testLoginButtonDisabledWhenFieldAreEmpty(),
                "Login button enabled with empty fields.");
    }

    @Test
    public void testInvalidLoginErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        String randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 5) + "@test.com";
        String randomPassword = "pass" + UUID.randomUUID().toString().substring(0, 5);
        String error = loginPage.testInvalidLoginShowErrorMsg(randomEmail, randomPassword);
        Assert.assertNotNull(error, "No error message for invalid login.");
    }

    @Test
    public void testPasswordEyeIconToggle() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.testPasswordMaskedbutton(), "Eye icon not found or toggle failed.");
    }

    @Test
    public void testPageElementsPresence() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isElementPresent(loginPage.getUserIdLocator()), "User ID field missing.");
        Assert.assertTrue(loginPage.isElementPresent(loginPage.getPasswordLocator()), "Password field missing.");
        Assert.assertTrue(loginPage.isElementPresent(loginPage.getLoginButtonLocator()), "Login button missing.");
        Assert.assertTrue(loginPage.isElementPresent(loginPage.getEyeIconLocator()), "Eye icon missing.");
    }
}
