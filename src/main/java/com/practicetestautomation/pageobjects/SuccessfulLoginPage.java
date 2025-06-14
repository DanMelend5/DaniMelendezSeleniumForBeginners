package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessfulLoginPage extends BasePage {

    private By logOutButtonLocator = By.linkText("Log out");


    public SuccessfulLoginPage(WebDriver driver) {
        super(driver);
    }


    public boolean isLogoutButtonDisplay() {
        try {
            return driver.findElement(logOutButtonLocator).isDisplayed();  //IF the element is present throws true
        } catch (NoSuchElementException e) {   //the handles no such element exeption
            return false;
        }

    }
}
