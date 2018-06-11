package pageobjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonlibrary.SeleniumCommonLib;
import commonlibrary.Utility;


public class InsurancePage {
	private static Logger log;
    private WebDriver driver;
    private static String message;
    private static String screenName = "InsurancePage";
    
    static {
        try {
            log = Utility.getTheLogger("InsurancePage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public InsurancePage(WebDriver driver)
    {
    	this.driver = driver;
    }
	 

    @FindBy(how = How.XPATH, using = "//div[@id='wh-body-inner']//div[contains(@class,'reviewinfo')]//span[contains(@class,'wh-rating')]")
    public WebElement ratings;
    
    @FindBy(how = How.XPATH, using = "//div[@class='wh-rating-choices-holder']/a[5]")
    public WebElement new_ratings;
    
    @FindBy(how = How.CLASS_NAME, using = "dropdown-title")
    public WebElement policy_dropdown;
    
    @FindBy(how = How.ID, using = "review-content")
    public WebElement review_editfield;
    
    
    @FindBy(how = How.XPATH, using = "//form[@id='reviewform']//input[@class='btn blue' and @type='submit']")
    public WebElement submit_btn;
  
    @FindBy(how = How.XPATH, using = "//div[@id='content']/div[@id='review']//span/a")
    public WebElement submit_confirm;
    

    @FindBy(how = How.CLASS_NAME, using = "af-icon-cross")
    public WebElement cross_button;
    

  
    public void verifyOnInsurancePage()
    {

        log.info("verifyOnInsurancePage function called");
        if(SeleniumCommonLib.verifyElmentVisibleHard(ratings)==SeleniumCommonLib.ASSERT_STATUS.FAIL)
        {
            message = "Assertion failed while checking visibility of element -:";
            log.error(message);
            Utility.takeScreenshot(driver,"verifyOnInsurancePage");
        }
        else
        {
            message = "Assertion PASSED while checking visibility of element -:";
            log.info(message);
            Utility.takeScreenshot(driver,"verifyOnInsurancePage");
        }
        log.info("verifyOnInsurancePage function ended execution");
    }
    
    public void verifyOnInsuranceRatingPage()
    {

        log.info("verifyOnInsuranceRatingPage function called");
        if(SeleniumCommonLib.verifyElmentVisibleHard(review_editfield)==SeleniumCommonLib.ASSERT_STATUS.FAIL)
        {
            message = "Assertion failed while checking visibility of element -:";
            log.error(message);
            Utility.takeScreenshot(driver,"verifyOnInsuranceRatingPage");
        }
        else
        {
            message = "Assertion PASSED while checking visibility of element -:";
            log.info(message);
            Utility.takeScreenshot(driver,"verifyOnInsuranceRatingPage");
        }
        log.info("verifyOnInsuranceRatingPage function ended execution");
    }
  
    
    public void naviagetToInsurancePage(String insruanceURL)
    {
    	WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("af-icon-cross")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("af-icon-cross")));
        SeleniumCommonLib.clickOnWebElement(cross_button, "Cross-Button", screenName, 15);
    	if(driver!=null)
    		driver.get(insruanceURL);
    }
    
    public void navigateToReviewPage(String starRating)
    {
    	String locator = "//div[@class='wh-rating-choices-holder']/a["+starRating.trim()+"]";
    	WebElement new_ratings = driver.findElement(By.xpath(locator));
    	Actions action = new Actions(driver);
    	action.moveToElement(ratings).clickAndHold().click(new_ratings).build().perform();
    }
    

    public void selectThePolicy(String policyType)
    {
    	screenName = "Rating Page";
    	log.info("selectThePolicy function called");
    	WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dropdown-title")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-title")));
        SeleniumCommonLib.clickOnWebElement(policy_dropdown, "Policy-Dropdown", screenName, 15);
        String optionLocator = "//a[@data-target='"+policyType.trim()+"']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(optionLocator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionLocator)));
        WebElement option = driver.findElement(By.xpath(optionLocator));
        SeleniumCommonLib.clickOnWebElement(option, "Dropdown-Option", screenName, 15);
        log.info("selectThePolicy function called ended");
        
    }
    
    public void enterTheReview(String reviewText)
    {
    	screenName = "Rating Page";
    	WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("review-content")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-content")));
       // SeleniumCommonLib.clickOnWebElement(review_editfield, "Review-TextBox", screenName, 15);
        SeleniumCommonLib.enterDataInFieldWeb(review_editfield, reviewText, "Review-TextBox", screenName, 15);
     
    }
    
    public void enterTheStarRating(String starRating)
    {
    	screenName = "Rating Page";
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	String ratingLocator = "//span[@id='overallRating']/a["+starRating+"]";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ratingLocator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ratingLocator)));
        WebElement starRatingElm = driver.findElement(By.xpath(ratingLocator));
        Actions action = new Actions(driver);
    	action.moveToElement(starRatingElm).click(starRatingElm).build().perform();
    }
    
    public void submitReview()
    {
    	screenName = "Rating Page";
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	String submit_locator  = "//form[@id='reviewform']//input[@class='btn blue' and @type='submit']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(submit_locator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(submit_locator)));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
        Actions action = new Actions(driver);
    	action.moveToElement(submit_btn).click(submit_btn).build().perform();
    }
    
    public void verifyReviewSubmited()
    {
    	screenName = "Rating Page";
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	String submit_locator  = "//div[@id='content']/div[@id='review']//span/a";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(submit_locator)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(submit_locator)));
        Assert.assertEquals("has been posted.".trim(), submit_confirm.getText().trim());
    }
    
}
