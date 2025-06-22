package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {
    private By row1InputLocator = By.xpath("//div[@id='row1']/input");
    private By row2InputLocator = By.xpath("//div[@id='row2']/input");
    private By addButtonLocator = By.xpath("//div[@id='row1']/button[@name='Add']");
    private By Row1SaveButtonLocator = By.xpath("//div[@id='row1']/button[@name='Save']");
    private By Row2SaveButtonLocator = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By row1EditButtonLocator = By.xpath("//div[@id='row1']/button[@name='Edit']");
    private By successMessageLocator = By.id("confirmation");

    public ExceptionsPage(WebDriver driver) {  // we send 'driver' to login page and driver to basePage
        super(driver);
    }

    public void exceptionPageNavigate() {
        super.navigate("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public boolean isRow1Display() {
        return isDisplay(row1InputLocator);
    }

    public boolean isRow2Display() {
        return isDisplay(row2InputLocator);
    }

    public String getRow1Text() {
        return waitForElement(row1InputLocator).getText();
    }

    public void clearRow1InputField() {
        waitForElement(row1InputLocator).clear();
    }

    public String sendRow1InputField(String sendRow1InputField) {
        waitForElement(row1InputLocator).sendKeys(sendRow1InputField);
        return sendRow1InputField;
    }

    public void row2InputField(String row2InputField) {
        waitForElement(row2InputLocator).sendKeys(row2InputField);
    }

    public void clickAddButton() {
        driver.findElement(addButtonLocator).click();
    }

    public void clickEditButton() {
        driver.findElement(row1EditButtonLocator).click();
    }

    public void clickRow1SaveButton() {
        waitForElement(Row1SaveButtonLocator).click();
           }
    public void clickRow2SaveButton() {
        waitForElement(Row2SaveButtonLocator).click();
    }

    public String getSuccessMessage() {
        return waitForElement(successMessageLocator).getText();
    }


}
