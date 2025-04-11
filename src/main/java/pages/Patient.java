package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v113.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;
import testbase.TestBase;

public class Patient extends TestBase {

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
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public void addPatientContactDetails()
	{
		nameField.sendKeys("Pushpa");
		emailField.sendKeys("pushpa@gmail.com");
		phoneField.sendKeys("9696969696");
				
	}
	public void addSecondaryDetails()
	{
		heightField.sendKeys("167");
		weightField.sendKeys("50");
		genderDropdown.click();
		femaleGender.click();
		ageField.sendKeys("35");
		systolicBP.sendKeys("120");
		diastolicBP.sendKeys("80");
		
				
	}
	
	public void clickGeneralDetailsBtn()
	{
		generalDetailBtn.click();
	}
	public void clickAddTestsBtn()
	{
		
		addTestsBtn.click();
	}
	
	public Patient() {

		PageFactory.initElements(driver, this);
	}

	public void clickAddPatientBtn() {
		addPatientBtn.click();
	}

	public void addPatientTest(List<String> list) throws InterruptedException {

		for (String test : list) {
			addPatientTestField.sendKeys(test);
			Thread.sleep(3000);
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
}
