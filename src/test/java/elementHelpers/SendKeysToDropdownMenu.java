package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendKeysToDropdownMenu {
	
	public SendKeysToDropdownMenu setSomething(String something) {
		this.ELEMENT.sendKeys(something);

		WebDriverWait wait_element = new WebDriverWait(driver,200);
		wait_element.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dijitReset dijitMenuItem']")));

		this.ELEMENT.sendKeys(Keys.TAB);
		return this;
	}

}
