package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

import testbase.TestBase;

public class Screenshot extends TestBase {
	
	public static String getDate()
	{
	  return (new SimpleDateFormat("dd-MM-YYYY ss-mm-HH").format(new Date()));
	}

	public static void takeScreenshot(String methodName) throws IOException
	{
		TakesScreenshot takesScreenshot   = (TakesScreenshot)driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/"+methodName+"--"+getDate()+".jpeg");
		FileHandler.copy(source, dest);
		
	}
}
