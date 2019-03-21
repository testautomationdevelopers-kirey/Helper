package elementHelpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendKeysText {
	

	@FindBy(name="textBox")
	private WebElement textBox;
	
	public SendKeysText setText(String text) {
		this.textBox.sendKeys(text);
		return this;
	}

}
