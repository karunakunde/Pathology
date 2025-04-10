package utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HandleDropDown {
	
	public static void applyFilter(WebElement dropdown,String filterBy) 
	{
		Select dropd  = new Select(dropdown);
		dropd.selectByVisibleText(filterBy);
		
	}
	
	

}
