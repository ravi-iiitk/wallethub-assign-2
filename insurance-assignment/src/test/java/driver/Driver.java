package driver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import commonlibrary.ReadPropertyValues;
import commonlibrary.Utility;


public class Driver {

 private WebDriver driver ;
 static Logger log;
 static String message;
 
 public WebDriver getWebDriver()
 {
	 if(driver!=null)
		 return driver;
	 else
		 return null;
 }

 public void setUp(String browser) throws  InterruptedException, IOException {

     try {
          log = Utility.getTheLogger("Driver");
     } catch (IOException e) {
         e.printStackTrace();
     }
     log.info("Fucntion SetUp called");
     //DesiredCapabilities capabilities = new DesiredCapabilities();

     try {
    	 ReadPropertyValues rpDrv = new ReadPropertyValues();
    	 if(browser.equalsIgnoreCase("chrome"))
    	 {
    		 String chromeDriverPath = rpDrv.getPropValues("chromeDriverPath");
    		 String absolutePathDriverPath = Utility.getFullPath(chromeDriverPath);
    		 System.setProperty("webdriver.chrome.driver", absolutePathDriverPath);
    		 Map<String, Object> prefs = new HashMap<String, Object>();
    		 prefs.put("profile.default_content_setting_values.notifications", 2);
    		 ChromeOptions options = new ChromeOptions();
    		 options.setExperimentalOption("prefs", prefs);
    		 driver =  new ChromeDriver(options);
    	 }
    	 
    	 if(browser.equalsIgnoreCase("firefox"))
    	 {
    		 String firefoxDriverPath = rpDrv.getPropValues("firefoxDriverPath");
    		 String absolutePathDriverPath = Utility.getFullPath(firefoxDriverPath);
    		 System.setProperty("webdriver.gekodriver.driver", absolutePathDriverPath);
    		 driver =  new FirefoxDriver();
    	 }
    	 
    	 if(browser.equalsIgnoreCase("ie"))
    	 {
    		 String ieDriverPath = rpDrv.getPropValues("ieDriverPath");
    		 String absolutePathDriverPath = Utility.getFullPath(ieDriverPath);
    		 System.setProperty("webdriver.iedriver.driver", absolutePathDriverPath);
    		 driver =  new InternetExplorerDriver();
    	 }
    	 driver.manage().window().maximize();
     }
     catch(org.openqa.selenium.WebDriverException webDriverException)
     {
         log.error("Driver object could not be created",webDriverException);
     }
     finally {
        if(driver!=null) {
        	message = "Driver object created successfully";
            log.info(message);
            Utility.takeScreenshot(driver,"Initialize_Driver");
        }           
     }   
     log.info("Function SetUp call finished");
}

 public  void tearDown()
 {
     log.info("TeadDown function called");
     if(driver!=null)
     {
        if(driver!=null)
         {
             message = "Driver Could be closed successfully";
             driver.quit();
             driver=null;
             log.info(message);
         }
         else
         {
             message = "Driver is already closed, so can not close again";
             log.error(message);
         }
     }
     log.info("TeadDown function call finished");
 }

 public  void verifyDriverClosed()
 {
     log.info("verifyDriverClosed function called");

         if(driver==null)
         {
             message = "Driver Could be closed successfully";
             log.info(message);
         }
         else
         {
             message = "Driver is already closed, hence can not close it again";
             log.error(message);
         }
     log.info("verifyDriverClosed function call finished");
 }
}