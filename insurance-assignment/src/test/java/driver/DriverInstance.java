package driver;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import commonlibrary.ReadPropertyValues;

public class DriverInstance
{
	public static WebDriver driver;
	public static Driver driverInstance;
	
	public static void createDriverInstance() throws IOException, InterruptedException
	{
		driverInstance = new Driver();
		ReadPropertyValues rpDrv = new ReadPropertyValues();
        String brwoser = rpDrv.getPropValues("broswer");
        driverInstance.setUp(brwoser);	
        driver = driverInstance.getWebDriver();
	}
	
	public static void goToWebSite() throws IOException
	{
		ReadPropertyValues rpDrv = new ReadPropertyValues();
        String webURL = rpDrv.getPropValues("URL");
        if(driver!=null)
        	driver.get(webURL);
	}
	
	public static void destroyDriverInstance()
	{
		driverInstance.tearDown();
	}
	
	public static void verifyDriverDestroyed()
	{
		driverInstance.verifyDriverClosed();
	}
}