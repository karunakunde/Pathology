package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.TestBase;

public class Patient extends TestBase {
	@FindBy(xpath = "//h5")
	private WebElement pagesubTitle;
	
	@FindBy(xpath = "//div[text()='Patients']/following-sibling::a")
	WebElement addPatientBtn;

	@FindBy(xpath = "//input[@name='name']")
	WebElement nameField;

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailField;

	@FindBy(xpath = "//input[@name='phone']")
	WebElement phoneField;

	@FindBy(xpath = "//span[text()='General Details']/parent::button")
	WebElement generalDetailBtn;

	@FindBy(xpath = "//input[@name='height']")
	WebElement heightField;

	@FindBy(xpath = "//input[@name='weight']")
	WebElement weightField;

	@FindBy(xpath = "//label[text()='Gender']/parent::div")
	WebElement genderDropdown;

	@FindBy(xpath = "//input[@name='age']")
	WebElement ageField;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	WebElement gendeList;

	@FindBy(xpath = "//li[@data-value='female']")
	WebElement femaleGender;

	@FindBy(xpath = "//li[@data-value='female']")
	WebElement maleGender;

	@FindBy(xpath = "//li[@data-value='']")
	WebElement noneGender;

	@FindBy(xpath = "//input[@name='systolic']")
	WebElement systolicBP;

	@FindBy(xpath = "//input[@name='diastolic']")
	WebElement diastolicBP;

	@FindBy(xpath = "//span[text()='Add Tests']/parent::button")
	WebElement addTestsBtn;

	@FindBy(xpath = "//input[@id='patient-test']")
	WebElement addPatientTestField;

	@FindBy(xpath = "//div[@id='patient-test-popup']//li")
	List<WebElement> testsDropdownList;

	@FindBy(xpath = "//label[text()='Discount']/following-sibling::div")
	WebElement discountDropdown;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> discountList;

	@FindBy(xpath = "//input[@id='patient-tests-labs']")
	WebElement labsInputfield;

	@FindBy(xpath = "//ul[@id='patient-tests-labs-popup']/li[1]")
	WebElement firstLabName;

	@FindBy(xpath = "//input[@name='doctor_name']")
	WebElement doctorNameField;

	@FindBy(xpath = "//ul[@id='mui-37988-popup']/li")
	List<WebElement> doctorNameList;
	
	@FindBy(xpath = "//ul[@id='mui-37988-popup']/li[1]")
	WebElement firstDocName;
	
	@FindBy(xpath = "//div[@id='mui-component-select-doctor_commission']")
	WebElement doctorCommField;

	@FindBy(xpath = "//li[@role='option']")
	List<WebElement> doctorCommisionList;
	
	@FindBy(xpath = "//button[@title='Add equipment']")
	WebElement addEquipmentBtn;
	
	@FindBy(xpath = "//div[@aria-label='Eqipment Name']")
	WebElement equipmentDropdown;
	
	

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public String getPageTitle() {
		return pagesubTitle.getText();
	}
	public void addPatientContactDetails() {
		nameField.sendKeys("Pushpa");
		emailField.sendKeys("pushpa@gmail.com");
		phoneField.sendKeys("9696969696");

	}

	public void addSecondaryDetails() {
		heightField.sendKeys("167");
		weightField.sendKeys("50");
		genderDropdown.click();
		femaleGender.click();
		ageField.sendKeys("35");
		systolicBP.sendKeys("120");
		diastolicBP.sendKeys("80");

	}

	public void addTestDetails(String[] patientTests, int[] testPrices, double discountPer)
			throws InterruptedException {

		List<String> list = Arrays.asList(patientTests);

		addPatientTest(list);
		clickOnDiscountDropdown();
		selectDiscount((int) discountPer + "%");
		selectLabFromDropdown("HEALTHCARE PATHOLOGY (Sion) - AFP (ALPHA FETO PROTEINS) - 180₹");
	    
		clickOnDoctorCommission();
		selectCommission((int) discountPer + "%");
		testRecommendedBy("Beans");
		addEquipment();

	}

	public void clickGeneralDetailsBtn() {
		generalDetailBtn.click();
	}

	public void clickAddTestsBtn() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addTestsBtn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addTestsBtn); // JS click
	}

	public Patient() {

		PageFactory.initElements(driver, this);
	}

	public void clickAddPatientBtn() {
		addPatientBtn.click();
	}

	public void addPatientTest(List<String> list) throws InterruptedException {

		Thread.sleep(2000);
		for (String test : list) {
			addPatientTestField.sendKeys(test);
			Thread.sleep(2000);
			selectTestFromDropdown(test);

		}
	}

	public void selectTestFromDropdown(String test) throws InterruptedException {
		testsDropdownList = driver.findElements(By.xpath("//div[@id='patient-test-popup']//li"));
		for (WebElement testElement : testsDropdownList) {

			WebElement testText = testElement.findElement(By.xpath("//div[text()='" + test + "']"));

			if (test.equalsIgnoreCase((testText.getText().trim()))) {
				testElement.click();
				break;

			}

		}
	}

	public void clickOnDiscountDropdown() throws InterruptedException {
		Thread.sleep(3000);
		discountDropdown.click();
	}

	public void selectDiscount(String userDiscount) {

		System.out.println("userdicount" + userDiscount);

		for (WebElement discount : discountList) {

			String discountText = discount.getText();

			if (userDiscount.equalsIgnoreCase(discountText)) {
				discount.click();
				break;

			}

		}
	}

	public void selectLabFromDropdown(String labName) throws InterruptedException {

		labsInputfield.sendKeys(labName);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='patient-tests-labs-popup']/li[1]")));
		firstLabName.click();
	}

	public void testRecommendedBy(String doctorName) throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement w = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Doctor who recommended this test']/following::button[1]")));
		w.click();
		
		for (WebElement docName : doctorNameList) {

			String discountText = docName.getText();

			if (doctorName.equalsIgnoreCase(discountText)) {
				docName.click();
				break;

			}

		}

	}

	public void clickOnDoctorCommission() throws InterruptedException {
		Thread.sleep(3000);
		doctorCommField.click();
	}

	public void selectCommission(String doctorcomm) {

		for (WebElement commission : doctorCommisionList) {

			String commissionText = commission.getText();

			if (doctorcomm.equalsIgnoreCase(commissionText)) {
				commission.click();
				break;

			}

		}
	}
	
	public void addEquipment() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addEquipmentBtn);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addEquipmentBtn); // JS click

		WebElement equipmentDropdown =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Eqipment Name']/parent::div")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", equipmentDropdown);
        Thread.sleep(4000);
        equipmentDropdown.sendKeys(Keys.ENTER);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//li[@role='option' and text()='Glove']") // exact match
		));
		
		option.click();
	}

	
}
