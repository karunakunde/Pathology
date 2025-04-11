package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReadData {

	
	public static String readProperty(String value) throws IOException
	{
		Properties	prop = new Properties();
		String path = System.getProperty("user.dir");  // Gets the project root path
		String filePath = path + "/src/main/java/config/config.properties";
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		return prop.getProperty(value);
	}
	
	
}
