package pageobjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import commonlibrary.SeleniumCommonLib;
import commonlibrary.Utility;


public class MyProfilePage {
	private static Logger log;
    private WebDriver driver;
    private static String message;
    private static String screenName = "MyProfilePage";
    
    static {
        try {
            log = Utility.getTheLogger("MyProfilePage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public MyProfilePage(WebDriver driver)
    {
    	this.driver = driver;
    }
	 
    @FindBy(how = How.CLASS_NAME, using = "user")
    public WebElement user_menu;
    
    @FindBy(how = How.XPATH, using = "//nav[@id='m-user']//li[4]/a")
    public WebElement profile_link;
    
    @FindBy(how = How.CLASS_NAME, using = "username")
    public WebElement userName;
    
    public void navigateToReviewPageByUser(String userName)
    {
    	String review_url = "https://wallethub.com/profile/"+userName+"/reviews/";
    	driver.get(review_url);
    }
    
    public void navigateToMyProfilePage()
    {
    	Actions action = new Actions(driver);
    	action.moveToElement(user_menu).click().moveToElement(profile_link).click(profile_link).build().perform();
    }
    
    public void verifyOnMyProfilePage()
    {

        log.info("verifyOnInsurancePage function called");
        if(SeleniumCommonLib.verifyElmentVisibleHard(userName)==SeleniumCommonLib.ASSERT_STATUS.FAIL)
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
    
    
    public void verifyReviewListedInActivity(String reviewText)
    {
    	screenName = "Rating Page";
    	boolean ratingSubmited = false;
    	String activity_list_locator = "//div[@class='feed-content']//p[@class='feeddesc']";
    	List<WebElement> allActivityList = driver.findElements(By.xpath(activity_list_locator));
    	Iterator<WebElement> itr = allActivityList.iterator();
    	WebElement currentListItem = null ;
    	while(itr.hasNext())
    	{
    		currentListItem = itr.next();
    		if(currentListItem.getText().trim().equalsIgnoreCase(reviewText.trim()))
    		{
    			ratingSubmited = true;
    			break;
    		}
    	}
    	
    	Assert.assertEquals(ratingSubmited, true);
    	if(currentListItem!=null)
    		Assert.assertEquals(currentListItem.getText().trim(), reviewText.trim());
    	
    }
    
    public void verifyReviewListedInReviews(String reviewText)
    {
    	screenName = "Rating Page";
    	boolean ratingSubmited = false;
    	String activity_list_locator = "//div[@class='reviews']//p";
    	List<WebElement> allActivityList = driver.findElements(By.xpath(activity_list_locator));
    	Iterator<WebElement> itr = allActivityList.iterator();
    	WebElement currentListItem=null;
    	while(itr.hasNext())
    	{
    		currentListItem = itr.next();
    		if(currentListItem.getText().trim().equalsIgnoreCase(reviewText.trim()))
    		{
    			ratingSubmited = true;
    			break;
    		}
    	}
    	
    	Assert.assertEquals(ratingSubmited, true);
    	if(currentListItem!=null)
        	Assert.assertEquals(currentListItem.getText().trim(), reviewText.trim());
    }   
    
}
