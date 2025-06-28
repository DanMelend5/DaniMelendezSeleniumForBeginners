package com.practicetestautomation.tests.login;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessfulLoginPage;
import com.practicetestautomation.tests.BaseTestPage;
import org.testng.Assert;
import org.testng.annotations.*;



public class LoginTests extends BaseTestPage {

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