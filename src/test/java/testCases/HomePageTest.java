package testCases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.Login;
import testbase.TestBase;
import utility.Screenshot;

public class HomePageTest extends TestBase {

	Login login;
	HomePage homepage;

	@BeforeMethod
	public void setup() throws IOException {
		initialization();
		login = new Login();
		homepage = new HomePage();
		login.logintoapplication();

	}

	@Test(enabled = true)
	public void todoListSectionOnHomePage() {

		Assert.assertTrue(homepage.istodoListPresent());
	}

	@Test(enabled = true)
	public void costCalcSectionOnHomePage() {
		Assert.assertTrue(homepage.isCostCalculatorSectionDisplayed());
	}

	@Test(enabled = true)
	public void verifyCalculatorWithoutDiscount() throws InterruptedException {

		// testdata
		String[] patientTests = { "VITAMIN B12, SERUM", "Beans" };
		int[] testPrices = { 900, 250 };

		List<String> list = Arrays.asList(patientTests);
		homepage.addPatientTest(list);

		int expSubTotal = Arrays.stream(testPrices).sum();
		int actSubtotal = Integer.parseInt(homepage.getSubTotal());
		int expTotal = Arrays.stream(testPrices).sum();
		int actTotal = Integer.parseInt(homepage.getTotal());

		Assert.assertEquals(actSubtotal, expSubTotal, "Subtotal not calculated correctly");
		Assert.assertEquals(actTotal, expTotal, "Total not calculated correctly");
		Reporter.log("Cost calculator functions properly without discount applied");

	}

	@Test
	public void verifyCalculateWithDiscount() throws InterruptedException {

		// testdata
		String[] patientTests = { "VITAMIN B12, SERUM", "Beans" };
		int[] testPrices = { 900, 250 };
		double discountPer = 10;
		int expSubTotal = Arrays.stream(testPrices).sum();
		double discount = (discountPer / 100) * expSubTotal;
		double expTotal = expSubTotal - discount;

		List<String> list = Arrays.asList(patientTests);
		homepage.addPatientTest(list);
		homepage.scrollToBottom();
		homepage.clickOnDiscountDropdown();
		homepage.selectDiscount((int) discountPer + "%");

		int actSubtotal = Integer.parseInt(homepage.getSubTotal());
		int actTotal = Integer.parseInt(homepage.getTotal());
		System.out.println(expTotal + "-------" + actSubtotal);

		Assert.assertEquals(actSubtotal, expSubTotal, "Subtotal not calculated correctly");
		Reporter.log("Subtotal calulated correctly");
		Assert.assertEquals(actTotal, expTotal, "Discount not applied correctly");
		Reporter.log("The discount is applied correctly by the cost calculator.");

	}

	@AfterMethod
	public void closeBrowser(ITestResult it) throws IOException {
		if (it.FAILURE == it.getStatus())
			Screenshot.takeScreenshot(it.getName());
		driver.close();
	}

}
