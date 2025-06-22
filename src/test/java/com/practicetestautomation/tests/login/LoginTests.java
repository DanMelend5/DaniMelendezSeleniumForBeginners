package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;

import org.openqa.selenium.WebDriver;

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
        successfulLoginPage.islogOutButtonDisplay();

        //Verifies login functionality the logging page
        logger.info("Verify the login functionality");
        Assert.assertEquals(successfulLoginPage.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        Assert.assertTrue(successfulLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));
        Assert.assertTrue(successfulLoginPage.isLogoutButtonDisplay());
    }


    // Incorrect Username
    @Parameters({"invalidUsername", "validPassword", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeUsernameLoginTest(String invalidUsername,
                                          String validPassword,
                                          String expectedErrorMessage) {
        logger.info("Starting testLoginFunctionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();

        //executeLogin() returns successful login page. here we save the return value into a new variable
        logger.info("Typing username: " + invalidUsername);
        loginPage.executeLogin(invalidUsername, validPassword);
        loginPage.getErrorMessage();

        // Verify error message is displayed
        logger.info("Verify the expected error message: " + expectedErrorMessage);
        Assert.assertTrue(loginPage.isErrorMessageDisplay());

        // Verify error message text is Your username is invalid!
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }

    // Incorrect Password
    @Parameters({"validUsername", "invalidPassword", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativePasswordLoginTest(String validUsername,
                                          String invalidPassword,
                                          String expectedErrorMessage) {
        logger.info("Starting testLoginFunctionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        //executeLogin() returns successful login page. here we save the return value into a new variable
        logger.info("Typing username: " + validUsername);
        loginPage.executeLogin(validUsername, invalidPassword);
        loginPage.getErrorMessage();

        // Verify error message is displayed
        logger.info("Verify the expected error message: " + expectedErrorMessage);
        Assert.assertTrue(loginPage.isErrorMessageDisplay());

        // Verify error message text is Your username is invalid!
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
    }
}