package commonlibrary;

import java.io.*;
import java.util.Properties;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class ReadPropertyValues {
	String result = "";
	InputStream inputStream=null;
 
	public String getPropValues(String property) throws IOException {

		String result =null;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = new FileInputStream(new File("src\\test\\resources\\configuration\\config.properties").getAbsolutePath());
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			// get the property value and print it out
			result = prop.getProperty(property);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
}