package testCases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.Login;
import pages.Patient;
import testbase.TestBase;
import utility.Screenshot;

public class PatientTest extends TestBase {
	Login login;
	HomePage homepage;
	Patient addPatient;

	@BeforeMethod
	public void setup() throws IOException {
		initialization();
		login = new Login();
		homepage = new HomePage();
		login.logintoapplication();
		homepage.clickPatientMenu();
		addPatient = new Patient();

	}
    @Test
	public void verifyAddPatientContactDetail() throws InterruptedException
	{
		addPatient.clickAddPatientBtn();
		addPatient.addPatientContactDetails();
		addPatient.clickGeneralDetailsBtn();

		Assert.assertTrue(addPatient.getPageTitle("Secondary details"));
	}
	
    @Test
	public void verifyAddSecondaryDetail() throws InterruptedException
	{
		addPatient.clickAddPatientBtn();
		addPatient.addPatientContactDetails();
		addPatient.clickGeneralDetailsBtn();
		addPatient.addSecondaryDetails();
		addPatient.clickAddTestsBtn();
		Assert.assertTrue(addPatient.getPageTitle("Test Cost Calculator"));
	}
	
	@Test
	public void verifyPatientrecordCreationforTest() throws InterruptedException {
		addPatient.clickAddPatientBtn();
		addPatient.addPatientContactDetails();
		addPatient.clickGeneralDetailsBtn();
		addPatient.addSecondaryDetails();
		addPatient.clickAddTestsBtn();
		String[] patientTests = { "AFP (ALPHA FETO PROTEINS)" };
		int[] testPrices = { 350 };
		double discountPer = 10;

		addPatient.addTestDetails(patientTests, testPrices, discountPer);

	}

	@AfterMethod
	public void closeBrowser(ITestResult it) throws IOException {
		if (it.FAILURE == it.getStatus())
			Screenshot.takeScreenshot(it.getName());
		driver.close();
	}
}
