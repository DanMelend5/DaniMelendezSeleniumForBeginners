package com.practicetestautomation.tests.exceptions;

import com.google.common.base.Verify;
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
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // tells selenium to wait for 10 second on the screen

        // Open page

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        logger.info("Browser is closed");
    }

    @Test
    public void noSuchElementExceptionTest() {
        //       EXPETECT CONDITIONS
//        elementToBeClickable
//        visibilityOfElementLocated
//        PresenceOfElementLocated
//        alertIsPresent
//        tittleContains
//        FrameToBeAvailableAndSwitchTilt
//        invisibilityOfElementLocated


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


//        // Locate first Row element
//        logger.info("locate first row");
        WebElement row1 = driver.findElement(By.id("row1"));
//        //WebElement row1FieldXpath = driver.findElement(By.xpath("//input[@class='input-field']"));


        // push add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        WebElement row2InputFieldXpath = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='row2']/input")));

        // locate second Row element
        logger.info("locate second row");
        //WebElement row2 = driver.findElement(By.id("row2"));
//        Assert.assertTrue(row2.isDisplayed());
//       in devtools console type $x("(//input [@class='input-field'])[2]") -- ("//div[@id='row2']/input")
//        WebElement row2InputFieldXpath = driver.findElement(By.xpath("//div[@id='row2']/input"));
        Assert.assertTrue(row2InputFieldXpath.isDisplayed(), "row 2 field is not display");

    }

 /*   @Test
         create time out test
        public void timeoutExceptionTest() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            // push add button
            WebElement addButton = driver.findElement(By.id("add_btn"));
            addButton.click();
            WebElement row2InputFieldXpath = wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//div[@id='row2']/input")));
*/

    @Test
    public void ElementNotInteractableException() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        // Click  add button
        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();
        WebElement row2InputFieldXpath = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@id='row2']/input")));

        row2InputFieldXpath.sendKeys("Grilled Meat");

        // Click Save Button

        //WebElement saveButton = driver.findElement (By.xpath("//div[@id='row2']/button") -- creates no interactable exception

        WebElement saveButton = driver.findElement(By.xpath
                                                   ("//div[@id='row2']/button[@name='Save']"));
        saveButton.click();

        // Verify text saved

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 2 was saved";
        Assert.assertEquals(actualMessage, expectedMessage, "message is not expected");

    }

    @Test
    public void InvalidElementStateException() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
//          //        Clear input field
        logger.info("clearing input field");
        WebElement row1InputFieldXpath = driver.findElement((By.xpath("//div[@id='row1']/input")));
        String currentText = row1InputFieldXpath.getText();
        WebElement editButton = driver.findElement(By.id("edit_btn"));
        editButton.click();
        row1InputFieldXpath.clear();

//        Type text into the input field
        logger.info("changing text");
        row1InputFieldXpath.sendKeys("ice cream");
        WebElement saveButton = driver.findElement(By.xpath("//div[@id='row1']/button[@name='Save']"));
        saveButton.click();

        // Verify text saved

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("confirmation")));
        String actualMessage = successMessage.getText();
        String expectedMessage = "Row 1 was saved";
        Assert.assertEquals(actualMessage, expectedMessage, "message is not expected");

//        Verify text changed
        String newText = row1InputFieldXpath.getText();
        Assert.assertEquals(currentText, newText, "there is no favorite food");


        logger.info("There is a new favorite food");

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
                         (By.id("instructions"))),"instructions is still displayed");

        //  Assert.assertFalse(instructions.isDisplayed(), "instructions is still displayed");
    }
}

