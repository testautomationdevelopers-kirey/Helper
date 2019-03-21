package elementHelpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClickButton {
	
	@FindBy(name="button")
	private WebElement button;
	
	public ClickButton clickButton() {
		button.click();
		return this;
	}

}
