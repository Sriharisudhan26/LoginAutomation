package com.janitri.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By userIdInput = By.id("formEmail");
    private By passwordInput = By.id("formPassword");
    private By loginButton = By.cssSelector("button.login-button");
    private By eyeIcon = By.cssSelector("img.passowrd-visible");
    private By errorMessage = By.cssSelector(".invalid-credential-div");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean testLoginButtonDisabledWhenFieldAreEmpty() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return !driver.findElement(loginButton).isEnabled();
    }

    public boolean testPasswordMaskedbutton() {
        if (driver.findElements(eyeIcon).isEmpty()) {
            return false;
        }
        String before = driver.findElement(passwordInput).getAttribute("type");
        driver.findElement(eyeIcon).click();
        String after = driver.findElement(passwordInput).getAttribute("type");
        return !before.equals(after);
    }

    public String testInvalidLoginShowErrorMsg(String userId, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput)).sendKeys(userId);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        if (driver.findElements(errorMessage).isEmpty()) {
            return null;
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public By getUserIdLocator() { return userIdInput; }
    public By getPasswordLocator() { return passwordInput; }
    public By getLoginButtonLocator() { return loginButton; }
    public By getEyeIconLocator() { return eyeIcon; }
}
