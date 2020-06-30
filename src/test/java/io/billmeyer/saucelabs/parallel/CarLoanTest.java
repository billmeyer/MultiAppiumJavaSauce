package io.billmeyer.saucelabs.parallel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class CarLoanTest extends TestBase
{
    /**
     * Runs a simple test verifying if the comment input is functional.
     *
     * @throws InvalidElementStateException
     */
    @Test(dataProvider = "hardCodedDevices")
    public void calculateCarLoan(String platformName, String platformVersion, String deviceName, Method method)
    throws MalformedURLException
    {
        AppiumDriver driver = createDriver(platformName, platformVersion, deviceName, method.getName());

        WebElement etLoanAmount = driver.findElement(By.id("io.billmeyer.loancalc:id/etLoanAmount"));
        WebElement etEditInterest = driver.findElement(By.id("io.billmeyer.loancalc:id/etEditInterest"));
        WebElement etSalesTax = driver.findElement(By.id("io.billmeyer.loancalc:id/etSalesTax"));
        WebElement etTerm = driver.findElement(By.id("io.billmeyer.loancalc:id/etTerm"));
        WebElement etDownPayment = driver.findElement(By.id("io.billmeyer.loancalc:id/etDownPayment"));
        WebElement etTradeIn = driver.findElement(By.id("io.billmeyer.loancalc:id/etTradeIn"));
        WebElement etFees = driver.findElement(By.id("io.billmeyer.loancalc:id/etFees"));
        WebElement btnCalculate = driver.findElement(By.id("io.billmeyer.loancalc:id/btnCalculate"));
        WebElement tvLoanTotal = driver.findElement(By.id("io.billmeyer.loancalc:id/tvLoanTotal"));

        // Set the input values for our loan calculation...
        etLoanAmount.sendKeys("25000");
        etEditInterest.sendKeys("3.42");
        etSalesTax.sendKeys("8");
        etTerm.sendKeys("60");
        etDownPayment.sendKeys("500");
        etTradeIn.sendKeys("7500");
        etFees.sendKeys("300");
        driver.getScreenshotAs(OutputType.FILE);

        btnCalculate.click();
        driver.getScreenshotAs(OutputType.FILE);

        // Check if within given time the correct result appears in the designated field.
        ExpectedCondition<Boolean> expected = ExpectedConditions.textToBePresentInElement(tvLoanTotal, "20,370.97");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        try
        {
            wait.until(expected);
        }
        catch (Throwable t)
        {
            System.err.println("Expected Condition Not Met: " + t.getMessage());
        }
    }
}