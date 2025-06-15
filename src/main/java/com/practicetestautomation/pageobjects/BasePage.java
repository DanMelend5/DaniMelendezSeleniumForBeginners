package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;  //WebDriver here is a class level variable
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) { //WebDriver here is a parameter, constructor has name of the class
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //
    }

    protected void navigate(String url)  {
        driver.get(url);
    } //opens the url we want

    public String getCurrentUrl(){  //This method find the current page to compare errors
        return driver.getCurrentUrl();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    protected WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  // uses a general locator
    }

    protected boolean isDisplay(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();  //IF the element is present throws true
        } catch (NoSuchElementException e) {   //the handles no such element Exception
            return false;
        }
    }
}

