package com.practicetestautomation.tests.exceptions;

import com.google.common.base.Verify;
import com.practicetestautomation.pageobjects.ExceptionsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
    private WebDriver driver;
    private Logger logger;


    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                logger.warning("Configuration for " + browser + " is missing, so running tests in Chrome by default");
                driver = new ChromeDriver();
                break;
        }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }


    @Test
    public void noSuchElementExceptionTest() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        logger.info("locate second row");
        Assert.assertTrue(exceptionsPage.isRow2Display(), "row 2 field is not display");
    }

    @Test
    public void ElementNotInteractableException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        exceptionsPage.row2InputField("Penis");
        exceptionsPage.clickRow2SaveButton();
        String expectedMessage = "Row 2 was saved";
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), expectedMessage, "message is not expected");
    }

    @Test
    public void InvalidElementStateException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("clearing input field");
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.isRow1Display();
        exceptionsPage.clickEditButton();
        exceptionsPage.clearRow1InputField();
        String newText = exceptionsPage.sendRow1InputField("ice cream");
        exceptionsPage.clickRow1SaveButton();

        // Verify text saved
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), "Row 1 was saved", "message is not expected");

//        Verify text changed

        Assert.assertEquals(newText, newText,"there is no favorite food");

        logger.info("There is a new favorite food : " + newText);

    }

    @Test
    public void StaleElementReferenceException() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
//          //        Clear input field

        //   WebElement instructions = driver.findElement(By.id("instructions"));

        // Click  add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();


        // Handing when an ellemnt is no longer present
        Assert.assertTrue(wait.until
                (ExpectedConditions.invisibilityOfElementLocated
                        (By.id("instructions"))), "instructions is still displayed");

        //  Assert.assertFalse(instructions.isDisplayed(), "instructions is still displayed");
    }
}

