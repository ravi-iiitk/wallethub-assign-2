package commonlibrary;

import driver.DriverInstance;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class SeleniumCommonLib {
    /*Common Code for:
1. Find a Element with error handling - Return Element
2. Click a element with error handling - Return if it could
3. Enter a data in edit field with error handling - Return Yes not if Could
4. To see if element exist - Return yes no if
5. Verify an element exist -Assertion (Soft)
6. Verify Text of an element - Assertion (Soft)
7. Verify a element must exist - Assertion (Hard)

*/
    static WebDriver driver = DriverInstance.driver;
    static Logger Log;
    static StringBuffer stepLog;

    static {
        try {
            Log = Utility.getTheLogger("AppiumCommLib");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public static boolean enterDataInFieldWeb(WebElement webElement, String dataToEnter,String fieldName, String screenName,int timeout)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try
        {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }
        catch (TimeoutException timeOut)
        {
            Log.error(timeOut);
            return false;
        }
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }
        catch (TimeoutException timeOut)
        {
            Log.error(timeOut);
            return false;
        }
        webElement.clear();
        webElement.sendKeys(dataToEnter);
        return true;
    }



    public static boolean clickOnWebElement(WebElement webElement,String fieldName, String screenName, int timeout)
    {

        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }
        catch (TimeoutException timeOut)
        {
            Log.error(timeOut.getMessage());
            return false;
        }
        webElement.click();
        return true;
    }


    public static void verifyElmentVisibleSoft(WebElement webElement)
    {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getElementStatus(webElement,ElementStatus.VISIBLE),ElementStatus.VISIBLE);
    }

    public static ASSERT_STATUS verifyElmentVisibleHard(WebElement webElement)
    {
        Log.info("Function verifyElmentVisibleHard called ");
        try
        {
            Assert.assertEquals(getElementStatus(webElement,ElementStatus.VISIBLE),ElementStatus.VISIBLE);
        }
        catch (AssertionError assertionError)
        {
            Log.error("Element is not visible",assertionError);
            return ASSERT_STATUS.FAIL;
        }
        Log.info("Element is vissible and Assertion passed");
        Log.info("Function verifyElmentVisibleHard call finished ");
        return ASSERT_STATUS.PASS;
    }

    public static void verifyElmentEnabledSoft(WebElement webElement)
    {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getElementStatus(webElement,ElementStatus.ENABLED),ElementStatus.ENABLED);
    }

    public static void verifyElmentEnabledHard(WebElement webElement)
    {
        Assert.assertEquals(getElementStatus(webElement,ElementStatus.ENABLED),ElementStatus.ENABLED);
    }

    public static void verifyWebElmentExistSoft(WebElement webElement)
    {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getElementStatus(webElement,ElementStatus.PRESENT),ElementStatus.PRESENT);
    }

    public static void verifyWebElmentExistHard(WebElement webElement)
    {
        Assert.assertEquals(getElementStatus(webElement,ElementStatus.PRESENT),ElementStatus.PRESENT);
    }


    public static void verifyWebElementTexttSoft(WebElement webElement, String actualText)
    {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(webElement.getText(),actualText);
    }

    public static void verifyWebElementTexttHard(WebElement webElement, String actualText)
    {
        Assert.assertEquals(webElement.getText(),actualText);
    }

    public enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    public enum ASSERT_STATUS
    {
        PASS,
        FAIL,
    }

    public static ElementStatus isElementVisible(WebDriver driver, By by, ElementStatus getStatus){

        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(driver.findElement(by).isEnabled())

                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;

            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(driver.findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }


    public static ElementStatus getElementStatus( WebElement webElement, ElementStatus getStatus){

        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(webElement.isEnabled())

                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;

            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(webElement.isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public static boolean existsElement(String xpath,WebDriver driver) {
        try {
            driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


    public static void driverLetsHoldForSometime(long forTime)
    {
        driver.manage().timeouts().implicitlyWait(forTime,TimeUnit.SECONDS);
    }

}
