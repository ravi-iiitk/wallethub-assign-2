package commonlibrary;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Properties;

public class Utility {

	final static Logger logger = Logger.getLogger(Utility.class.getName());
	static Logger log;
    static {
        try {
            log = Utility.getTheLogger("Utility");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFullPath(String halfPath)
    {
        halfPath = "/"+halfPath;
    	String currentDir = System.getProperty("user.dir");
    	currentDir=currentDir.replace("\\", "\\\\");
    	return  currentDir+halfPath;
    }

    public static void takeScreenshot(WebDriver driver,String stepName)
    {
        String message;
        try {
            message = "Screen Shot Captured";
            Utility.captureScreenShot(driver,stepName);
            log.info(message);
        } catch (IOException e) {
            message = "Screen Shot could not be Captured";
            e.printStackTrace();
            log.error(message,e);
        }
    }

    public static void captureScreenShot(WebDriver driver,String stepName) throws IOException
    {
        // Take screenshot and store as a file format
        ReadPropertyValues rpDrv = new ReadPropertyValues();
        String scrnShotFldrPath="";
        scrnShotFldrPath = rpDrv.getPropValues("ScreenshotFolderPath");
        scrnShotFldrPath = getFullPath(scrnShotFldrPath);
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(src, new File(scrnShotFldrPath+stepName+System.currentTimeMillis()+".jpeg"));
        }
        catch (IOException e)
        {
            logger.error(e.getMessage()) ;
        }
    }

	public static String randomString( int len ){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ )
		{
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
			if(i>=5&&i%5==0)
				sb.append(" ");
		}
			
		return sb.toString();
	}

    public static String removeLast(String s,int start, int end) {
        if (s!=null && s!="" && s.length()>0) {
            s = s.substring(start,end);
        }
        return s;
    }

    public static Logger getTheLogger(String className) throws IOException {
        InputStream inputStream;
        System.setProperty("testexecution.log",new File("reports\\log\\test-execution.log").getAbsolutePath());
        Properties props = new Properties();
        inputStream = new FileInputStream(new File("src\\test\\resources\\configuration\\log4j.properties").getAbsolutePath());
        props.load(inputStream);
        PropertyConfigurator.configure(props);
        return Logger.getLogger(className);
    }

   
  
}
