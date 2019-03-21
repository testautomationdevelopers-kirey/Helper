package elementHelpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClickButtonJavaScriptExecutor {
	
	
		@FindBy(id="authorPay")
		private WebElement authorizationForPayments;
		
		@FindBy(id="authorPay2")
		private WebElement authorizationForPayments2;
	
	public ClickButtonJavaScriptExecutor clickPaymentsAuthorization() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", authorizationForPayments);
		return this;
	}
	
	public ClickButtonJavaScriptExecutor clickPaymentsAuthorization2() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", this.authorizationForPayments2);
		js.executeScript("arguments[0].click();", this.authorizationForPayments2);
		return this;
	}
	
	

}
