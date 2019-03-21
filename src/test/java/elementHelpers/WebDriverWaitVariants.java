package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitVariants {
	
	public void waitForClickable(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 150); 
		wait.until(ExpectedConditions.elementToBeClickable(waitThis));
	}
	
	public void waitForVisible(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 200); 
		wait.until(ExpectedConditions.visibilityOf(waitThis));
	}
	
	public void waitForTextToAppear(WebElement waitThis, String textToAppear) {
		WebDriverWait wait = new WebDriverWait(driver, 200);
	    wait.until(ExpectedConditions.textToBePresentInElement(waitThis, textToAppear));
	}
	
	public void changeFrame() {
		WebDriverWait wait = new WebDriverWait(driver,200);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("lightviewContent")));
	}
	
	public void waitForAttribute(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 150); 
		// aria-expanded daje informaciju o tome je li element expandan ili ne
		// aria-expanded: true -> expanded; false -> not expanded
		wait.until(ExpectedConditions.attributeToBe(ELEMENT, "aria-expanded", "true"));
	}
	
	public void ThreadSleep(int miliSeconds){
		try {
			Thread.sleep(miliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
