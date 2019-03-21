package javaResources;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleAlert {
	
		public void alert() {

			try {
				WebDriverWait wait = new WebDriverWait(driver, 50); 
				wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
				System.out.println("Alert present & closed.");
			} catch(Exception e) {
				System.out.println("Alert NOT present.");
			}
		}

}
