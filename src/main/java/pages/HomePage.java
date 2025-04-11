package pages;

import java.io.Console;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.TestBase;
import utility.ReadData;

public class HomePage extends TestBase {

	@FindBy(xpath = "//div[@class='title']")
	private WebElement pageTitle;

	@FindBy(xpath = "//*[contains(text(),'TODO')]")
	WebElement todoText;

	@FindBy(xpath = "//*[contains(text(),'Test Cost Calculator')]")
	WebElement testcostCalulatorText;

	@FindBy(xpath = "//input[@id='patient-test']")
	WebElement addPatientTestField;

	@FindBy(xpath = "//div[@id='patient-test-popup']//li")
	List<WebElement> testsDropdownList;

	@FindBy(xpath = "//div[text()='Subtotal']/following-sibling::div")
	WebElement subtotalText;

	@FindBy(xpath = "//div[text()='Total']/following-sibling::div")
	WebElement totalText;

	@FindBy(xpath = "//label[text()='Discount']/following-sibling::div")
	WebElement discountDropdown;

	@FindBy(xpath = "//ul[@role='listbox']/li")
	List<WebElement> discountList;

	@FindBy(xpath = "//a[@href='/patients']")
	WebElement patientMenu;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isCostCalculatorSectionDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(testcostCalulatorText));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testcostCalulatorText);

		return testcostCalulatorText.isDisplayed();
	}

	public String getPageTitle() {
		return pageTitle.getText();
	}

	public boolean istodoListPresent() {
		wait.until(ExpectedConditions.visibilityOf(todoText));
		return todoText.isDisplayed();
	}

	public String getSubTotal() {
		String result = subtotalText.getText().replaceAll("[^0-9]", "");
		System.out.println("subtotal------" + subtotalText.getText());
		return result;
	}

	public String getTotal() {
		String result = totalText.getText().replaceAll("[^0-9]", "");
		return result;
	}

	public void addPatientTest(List<String> list) throws InterruptedException {
		boolean isCalculatorVisible = isCostCalculatorSectionDisplayed();
		if (isCalculatorVisible) {
			for (String test : list) {
				addPatientTestField.sendKeys(test);
				Thread.sleep(2000);
				selectTestFromDropdown(test);
			}
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

	public void clickPatientMenu() {
		wait.until(ExpectedConditions.visibilityOf(patientMenu));
		patientMenu.click();
	}

	public void scrollToBottom() {
		// TODO Auto-generated method stub
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

}
