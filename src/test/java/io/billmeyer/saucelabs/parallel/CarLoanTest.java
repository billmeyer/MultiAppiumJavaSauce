package io.billmeyer.saucelabs.parallel;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class CarLoanTest extends TestBase
{
    @FindBy(id = "io.billmeyer.loancalc:id/etLoanAmount")
    MobileElement etLoanAmount;

    @FindBy(id = "io.billmeyer.loancalc:id/etEditInterest")
    MobileElement etEditInterest;

    @FindBy(id = "io.billmeyer.loancalc:id/etSalesTax")
    MobileElement etSalesTax;

    @FindBy(id = "io.billmeyer.loancalc:id/etTerm")
    MobileElement etTerm;

    @FindBy(id = "io.billmeyer.loancalc:id/etDownPayment")
    MobileElement etDownPayment;

    @FindBy(id = "io.billmeyer.loancalc:id/etTradeIn")
    MobileElement etTradeIn;

    @FindBy(id = "io.billmeyer.loancalc:id/etFees")
    MobileElement etFees;

    @FindBy(id = "io.billmeyer.loancalc:id/btnCalculate")
    MobileElement btnCalculate;

    @FindBy(id = "io.billmeyer.loancalc:id/tvLoanTotal")
    MobileElement tvLoanTotal;

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

        // Initialize our MobileElement variable bindings...
        PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);

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