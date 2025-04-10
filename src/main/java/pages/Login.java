package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbase.TestBase;
import utility.ReadData;

public class Login extends TestBase {

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailTxtBox;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordTxtBox;

	@FindBy(xpath = "//button/span[text()='Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath = "//div[@class='MuiAlert-message']")
	private WebElement errorMessage;

	public Login() {
		PageFactory.initElements(driver, this);
	}

	public String logintoapplication() throws IOException {

		emailTxtBox.sendKeys(ReadData.readProperty("UserName"));
		passwordTxtBox.sendKeys(ReadData.readProperty("Password"));
		loginBtn.click();

		return driver.getCurrentUrl();
	}

	public void enterEmail(String emailId)
	{
		emailTxtBox.sendKeys(emailId);
	}
	
	public void enterPassword(String password)
	{
		passwordTxtBox.sendKeys(password);
	}
	
	public void clickLoginBtn() {
		loginBtn.click();
	}
	public String verifyURLOfApplication() {
		return driver.getCurrentUrl();
	}

	public String verifyTitleOfApplication() {
		return driver.getTitle();
	}
	
	public boolean isErrorMessageDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		return errorMessage.isDisplayed();
	}
}
