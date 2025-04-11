package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.Login;
import pages.Patient;
import testbase.TestBase;

public class PatientTest extends TestBase {
	Login login;
	HomePage homepage;
	Patient addPatient;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		initialization();
		login=new Login();
		homepage = new HomePage();
		login.logintoapplication();
		homepage.clickPatientMenu();
		addPatient = new Patient();
		
		
	}
	@Test
	public void test()
	{
		addPatient.clickAddPatientBtn();
		addPatient.addPatientContactDetails();
		addPatient.clickGeneralDetailsBtn();
		addPatient.addSecondaryDetails();
		addPatient.clickAddTestsBtn();
	}

	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
}
