package io.billmeyer.saucelabs.parallel;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
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
    public void calculateCarLoan(String platformName, String deviceName, String platformVersion, Method method)
    throws MalformedURLException
    {

        AndroidDriver driver = createDriver(platformName, platformVersion, deviceName, method.getName());

        // Get the Input Elements...
        MobileElement etLoanAmount = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etLoanAmount"));
        MobileElement etEditInterest = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etEditInterest"));
        MobileElement etSalesTax = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etSalesTax"));
        MobileElement etTerm = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etTerm"));
        MobileElement etDownPayment = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etDownPayment"));
        MobileElement etTradeIn = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etTradeIn"));
        MobileElement etFees = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/etFees"));
        MobileElement btnCalculate = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/btnCalculate"));

        // Get the Output Elements...
        MobileElement tvLoanTotal = (MobileElement) driver.findElement(By.id("io.billmeyer.loancalc:id/tvLoanTotal"));

        /* Add two and two. */
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

        /* Check if within given time the correct result appears in the designated field. */
        ExpectedCondition<Boolean> expected = ExpectedConditions.textToBePresentInElement(tvLoanTotal, "20,370.97");

        WebDriverWait wait = new WebDriverWait(driver, 10);
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