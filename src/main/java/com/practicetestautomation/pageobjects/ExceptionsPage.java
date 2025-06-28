package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExceptionsPage extends BasePage {
    private By row1InputLocator = By.xpath("//div[@id='row1']/input");
    private By row2InputLocator = By.xpath("//div[@id='row2']/input");
    private By addButtonLocator = By.xpath("//div[@id='row1']/button[@name='Add']");
    private By Row1SaveButtonLocator = By.xpath("//div[@id='row1']/button[@name='Save']");
    private By Row2SaveButtonLocator = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By row1EditButtonLocator = By.xpath("//div[@id='row1']/button[@name='Edit']");
    private By successMessageLocator = By.id("confirmation");
    private By intructionsLocator = By.id("instructions");

    public ExceptionsPage(WebDriver driver) {  // we send 'driver' to login page and driver to basePage
        super(driver);
    }

    public void exceptionPageNavigate() {
        super.navigate("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public boolean isRow1DisplayedAfterWait() {
        return waitForIsDisplay(row1InputLocator);
    }

    public boolean isRow2DisplayedAfterWait() {
        return waitForIsDisplay(row2InputLocator);
    }

    public String getRow1Text() {
        WebElement Row1Value = driver.findElement(row1InputLocator);
        return Row1Value.getText();
    }

    public void enterFoodRow1InputField(String input) {
        WebElement row1Input = driver.findElement(row1InputLocator);
        row1Input.clear();
        row1Input.sendKeys(input);
    }

    public void enterFoodRow2Input(String input) {
        driver.findElement(row2InputLocator).sendKeys(input);
    }

    public void clickAddButton() {
        driver.findElement(addButtonLocator).click();
    }

    public void clickEditButton() {
        driver.findElement(row1EditButtonLocator).click();
    }

    public void clickRow1SaveButton() {
        driver.findElement(Row1SaveButtonLocator).click();
    }

    public void clickRow2SaveButton() {
        driver.findElement(Row2SaveButtonLocator).click();
    }

    public String getSuccessMessage() {
        return waitForElement(successMessageLocator).getText();
    }
    public boolean isInstructionsHiddenAfterWait() {
        return waitForIsHidden(intructionsLocator);
    }

}
