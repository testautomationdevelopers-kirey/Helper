package javaResources;

import org.openqa.selenium.JavascriptExecutor;

public class WindowOpenNew {
	
	public void openNewWindow() {
		//driver.switchTo().window(parentHandle);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open();");
		newWinHandleSwitch();		
	}

}
