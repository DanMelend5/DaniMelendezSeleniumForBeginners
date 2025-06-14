package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;  //WebDriver here is a class level variable
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) { //WebDriver here is a parameter, constructor have name of the class
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    protected WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  // uses a general locator
    }

}
