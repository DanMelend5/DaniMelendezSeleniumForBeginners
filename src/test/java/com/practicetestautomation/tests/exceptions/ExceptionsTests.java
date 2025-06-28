package com.practicetestautomation.tests.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import com.practicetestautomation.tests.BaseTestPage;
import org.testng.Assert;
import org.testng.annotations.*;


public class ExceptionsTests extends BaseTestPage {

    @Test //
    public void noSuchElementExceptionTest() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        logger.info("locate second row");
        Assert.assertTrue(exceptionsPage.isRow2DisplayedAfterWait(), "row 2 field is not display");
    }

   /* public void timeOutExceptionTest() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        logger.info("locate second row");
        Assert.assertTrue(exceptionsPage.isRow2DisplayedAfterWait(), "row 2 field is not display");
    }*/

    @Test
    public void ElementNotInteractableException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        exceptionsPage.isRow2DisplayedAfterWait();
        exceptionsPage.enterFoodRow2Input("Asado");
        exceptionsPage.clickRow2SaveButton();
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), "Row 2 was saved", "message is not expected");
    }

    @Test
    public void InvalidElementStateException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("clearing input field");
        exceptionsPage.exceptionPageNavigate();
        String currentRow1Text = exceptionsPage.getRow1Text();
        exceptionsPage.clickEditButton();

        System.out.println(currentRow1Text + "is the current value in row 1");
        exceptionsPage.enterFoodRow1InputField("Asado");
        exceptionsPage.clickRow1SaveButton();
        String updatedRow1Text = exceptionsPage.getRow1Text();

        // Verify text saved
        Assert.assertEquals(exceptionsPage.getSuccessMessage(), "Row 1 was saved", "message is not expected");

//        Verify text changed

         Assert.assertEquals(currentRow1Text, updatedRow1Text, "there is no favorite food");

    }

    @Test
    public void StaleElementReferenceException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        exceptionsPage.exceptionPageNavigate();
        exceptionsPage.clickAddButton();
        exceptionsPage.isInstructionsHiddenAfterWait();

        // Handing when an element is no longer present
        Assert.assertTrue(exceptionsPage.isInstructionsHiddenAfterWait(), "instructions is still displayed");

        //  Assert.assertFalse(instructions.isDisplayed(), "instructions is still displayed");
    }
}

