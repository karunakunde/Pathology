package pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import testbase.TestBase;
import utility.ReadData;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//div[@class='title']")
	private WebElement pageTitle;
	
	@FindBy(xpath = "//*[contains(text(),'TODO') || contains(text(),'todo')]")
	WebElement todoText;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle()
	{
		return pageTitle.getText();
	}
	
	public boolean istodoListPresent()
	{
		return todoText.isDisplayed();
	}
	

}
