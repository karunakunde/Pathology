package testCases;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Login;
import testbase.TestBase;
import utility.ReadData;
import utility.Screenshot;

public class LoginTest extends TestBase{
	Login lg;
	
	@BeforeMethod
	public void setup() throws IOException {
		initialization();
		lg = new Login();
		
	}
	
	
	@Test
	public void logintoapplication() throws IOException, InterruptedException  {
		lg.logintoapplication();	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("dashboard"));
		String expUrl = "https://gor-pathology.web.app/dashboard";
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals(actUrl, expUrl,"Login not successfull");
		Reporter.log("Login successful--"+actUrl);
	}
    @Test
	public void loginWithInvalidCredentials() throws InterruptedException
	{
		lg.enterEmail("invalidtest@ct.io");
		lg.enterPassword("inQwerty@1234");
		lg.clickLoginBtn();
		
		Assert.assertTrue(lg.isErrorMessageDisplayed(),"Error message not displayed");
		String expUrl = "https://gor-pathology.web.app/dashboard";
		String actUrl = driver.getCurrentUrl();
		Assert.assertNotEquals(actUrl, expUrl,"User logged with invalid credentials");
		Reporter.log("Login working as expected");
	}
	@AfterMethod
	public void closeBrowser(ITestResult it) throws IOException
	{
		/*
		 * if(it.FAILURE==it.getStatus()) Screenshot.takeScreenshot(it.getName());
		 */
		driver.close();
	}
}
