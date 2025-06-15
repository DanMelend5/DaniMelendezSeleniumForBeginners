package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
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

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFunctionality() {
        logger.info("Starting testLoginFunctionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        //executeLogin() returns successful login page. here we save the return value into a new variable
        SuccessfulLoginPage successfulLoginPage = loginPage.executeLogin("student", "Password123");
        successfulLoginPage.load();

        //Verifies login functionality the logging page
        logger.info("Verify the login functionality");
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplay());
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negativeLoginTest");
        // Type username incorrectUser into Username field
        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Typing username: " + username);
        usernameInput.sendKeys(username);

        // Type password Password123 into Password field
        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Typing password");
        passwordInput.sendKeys(password);

        // Push Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Click Submit button");
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Verify the expected error message: " + expectedErrorMessage);
        // Verify error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        // Verify error message text is Your username is invalid!
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}