package elementHelpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickButton {
	
	@FindBy(name="button")
	private WebElement button;
	
	@FindBy(name="button2")
	private WebElement button2;
	
	public ClickButton clickButton() {
		button.click();
		return this;
	}
	
	public ClickButton clickButton2() {
		WebDriverWait wait = new WebDriverWait(driver, 50); 
		wait.until(ExpectedConditions.elementToBeClickable(button2));
		
		button2.click();
		return this;
	}

}
