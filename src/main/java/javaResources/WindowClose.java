package javaResources;

import org.openqa.selenium.JavascriptExecutor;

public class WindowClose {
	
			public void closeWindow() {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.close();");	
			}

}
