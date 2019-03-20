package elementHelpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckDropdownExpanded {

	public void waitForAttribute(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 150); 
		// aria-expanded daje informaciju o tome je li element expandan ili ne
		// aria-expanded: true -> expanded; false -> not expanded
		wait.until(ExpectedConditions.attributeToBe(ELEMENT, "aria-expanded", "true"));
	}
	
	public PAGEOBJECT SetLuogoDropdown(String text)
	{
		ELEMENT.sendKeys(text);
		waitForAttribute(ELEMENT);
		ELEMENT.sendKeys(Keys.TAB);
		return this;
	}
}
