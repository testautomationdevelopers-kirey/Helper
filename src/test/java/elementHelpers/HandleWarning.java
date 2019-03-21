package elementHelpers;

import org.openqa.selenium.By;

public class HandleWarning {
	
		public void warning() {
			try {
				driver.findElement(By.id("warning")).click();
				System.out.println("Warning closed.");
			} catch (Exception e) {
				System.out.println("Warning not present.");
			}
		}

}
