package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import commonlibrary.SeleniumCommonLib;
import commonlibrary.Utility;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.io.IOException;


public class LoginPage {

    private static Logger log;
    private static String message;
    private static String screenName = "LoginPage";
    final WebDriver driver;
    
    @FindBy(how = How.NAME, using = "em")
    public WebElement userid_editField;
    
    @FindBy(how = How.NAME, using = "pw")
    public WebElement passwd_editField;
    
    @FindBy(how = How.XPATH, using = "//button[@class='btn blue touch-element-cl' and @type='button' and contains(@data-hm-tap,'doLogin')]")
    public WebElement login_btn;
    
    @FindBy(how = How.CLASS_NAME, using = "follow follow-myprofile")
    public WebElement main_content;
    
    @FindBy(how = How.CLASS_NAME, using = "user")
    public WebElement triangle_menu;
    
    @FindBy(how = How.ID, using = "logout-link")
    public WebElement logout_link;
    
  
    static {
        try {
            log = Utility.getTheLogger("LoginScreen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public LoginPage(WebDriver driver)
    {
    	this.driver = driver;
    }

    public void verifyUserOnLoginScreen()
    {

            log.info("verifyUserOnLoginScreen function called");
            if(SeleniumCommonLib.verifyElmentVisibleHard(userid_editField)==SeleniumCommonLib.ASSERT_STATUS.FAIL)
            {
                message = "Assertion failed while checking visibility of element -:";
                log.error(message);
                Utility.takeScreenshot(driver,"verifyUserOnLoginScreen");
            }
            else
            {
                message = "Assertion PASSED while checking visibility of element -:";
                log.info(message);
                Utility.takeScreenshot(driver,"verifyUserOnLoginScreen");
            }
            log.info("verifyUserOnLoginScreen function ended execution");

    }
    
    public void logOut()
    {
    	if(triangle_menu!=null)
    	{
    		SeleniumCommonLib.clickOnWebElement(triangle_menu, "Traingle Meue", screenName, 10);
    		SeleniumCommonLib.clickOnWebElement(logout_link, "Log-out-Link", screenName, 15);
    	}
    }

    public void doLogin(String userId,String password)
    {
            log.info("verifyUserOnLoginScreen function called");
            if(userid_editField!=null && passwd_editField !=null)
            {
                boolean editFiledDataEntered = SeleniumCommonLib.enterDataInFieldWeb(userid_editField,userId,"User id field",screenName,15);
                boolean pwdFiledDataEntered = SeleniumCommonLib.enterDataInFieldWeb(passwd_editField,password,"Password Filed",screenName,15);
                if(editFiledDataEntered&&pwdFiledDataEntered)
                {
                    message="Data could be sucessfully entered in fields-: on screen: -"+screenName;
                    log.info(message);
                    Utility.takeScreenshot(driver,"doLogin");
                   boolean btnClicked= SeleniumCommonLib.clickOnWebElement(login_btn,"LoginButton",screenName,15);
                   if(btnClicked)
                   {
                       message="Button could be clicked - on screen-:"+screenName;
                       log.info(message);
                       Utility.takeScreenshot(driver,"doLogin");
                   }
                   else
                   {
                       message="Login Button could not be clicked -on screen-:"+screenName;
                       log.error(message);
                       Utility.takeScreenshot(driver,"doLogin");
                   }

                }else
                {
                    message="Data can not be entered in fields-:user id and in password on screen: -"+screenName;
                    log.error(message);
                    Utility.takeScreenshot(driver,"doLogin");
                }
            }
            else
            {
                message="Elements could not be located"+" on screen: -"+screenName;
                log.error(message);
            }

    }

    public void verifyUserLoggedInSucessfully()
    {
            log.info("verifyUserLoggedInSucessfully function called");
            if(main_content!=null)
            {
                if(SeleniumCommonLib.verifyElmentVisibleHard(main_content)==SeleniumCommonLib.ASSERT_STATUS.PASS)
                {
                    message = "Assertion Passed for Element Visibilit of element on screen:-"+screenName;
                    log.info(message);
                    Utility.takeScreenshot(driver,"verifyUserLoggedInSucessfully");
                }
                else
                {
                    message = "Assertion FAILED for Element Visibilit of element  on screen:-"+screenName;
                    log.error(message);
                    Utility.takeScreenshot(driver,"verifyUserLoggedInSucessfully");
                }
            }
            else
            {
                message = "Element  could not be found on screen:-"+screenName;
                log.error(message);
                Utility.takeScreenshot(driver,"verifyUserLoggedInSucessfully");
            }
            log.info("verifyUserLoggedInSucessfully function ended execution");
        

    }
}