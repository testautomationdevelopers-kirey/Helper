package elementHelpers;

import org.openqa.selenium.By;

public class HandlePopup {
	
	public void popup() throws InterruptedException {
		Thread.sleep(1000);
		try {
			driver.findElement(By.cssSelector("ul.borderFrame")).findElement(By.id("msgLettiId")).click();
			System.out.println("Pop up message marked as read.");
		} catch (Exception e) {

		}
	}

}
