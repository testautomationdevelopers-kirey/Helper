package elementHelpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendKeysToTextBox {
	

	@FindBy(name="textBox")
	private WebElement textBox;
	
	public SendKeysToTextBox setText(String text) {
		this.textBox.sendKeys(text);
		return this;
	}

}
