package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HandlePopupInMiddleOfHandling {
	
	public static TemplatePage using(WebDriver driver) { 
		//popup message that appears in the middle of the page management
		try {
			driver.findElement(By.cssSelector("ul.borderFrame")).findElement(By.cssSelector("div.toolbar")).click();
			System.out.println("Message closed.");
		} catch (Exception e) {

		}
		
		return new TemplatePage(driver);
	}

}
