package elementHelpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClickButtonJavaScriptExecutor {
	
	//@FindBy(xpath="//b[text()='Autorizzazioni per Livelli Pagamenti']") 	
		@FindBy(xpath="//td[@onclick=\"clessidra();location.href='SISServletPagamentiSospesiPerLivelli'\"]")
		private WebElement authorizationForPayments;
	
	public ClickButtonJavaScriptExecutor clickPaymentsAuthorization() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", authorizationForPayments);
		return this;
	}

}
