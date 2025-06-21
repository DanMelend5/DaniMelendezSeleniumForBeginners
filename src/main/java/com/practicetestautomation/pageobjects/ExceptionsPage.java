package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {
private By row1InputLocator = By.id("row1");
private By addButtonLocator = By.xpath("//div[@id='row1']/button[@name='Add']");
private By row2InputLocator = By.xpath("//div[@id='row2']/input");

    public ExceptionsPage(WebDriver driver) {  // we send 'driver' to login page and driver to basePage
        super(driver);
    }

    public void exceptionPageNavigate(){
        super.navigate("https://practicetestautomation.com/practice-test-exceptions/");
    }

}
