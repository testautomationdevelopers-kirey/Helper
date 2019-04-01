package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagesBase {
	
	protected WebDriver driver = null;
	protected String pageName = getClass().getSimpleName();
	protected JSONHelper jHelper = new JSONHelper();
	private ScreenshotTaker screenShotTaker = new ScreenshotTaker();
	
	public enum Status { 
		FINAL, 
		FAIL, 
		NORMAL 
	}; 

	/**
	 * Take screenshot
	 * @param status	- FINAL, FAIL OR NORMAL depending on test stage
	 * @param driver	- WebDriver of the subclass
	 * @param testName	- current @Test name
	 * @param pageName	- current page object name
	 */
	protected void takeScreenshot(Status status, WebDriver driver, String testName, String pageName)
	{
		screenShotTaker.screenshot(status, driver, testName, pageName);
	}
	
	/**
	 * Writes test result to a ResultsData.json
	 * @param className - Current Test Case
	 * @param result	- result to write into JSON
	 */
	protected void writeResult(String className, String result)
	{
		try {
			jHelper.writeResult(className, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes claim result to a ClaimsData.json
	 * @param className - Current Test Case
	 * @param claim		- claim to write into JSON
	 */
	protected void writeClaim(String className, String claim)
	{
		try {
			jHelper.writeClaim(className, claim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait for the given WebElement to be visible
	 * @param waitThis	- wanted element in question
	 */
	protected void waitForVisible(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.visibilityOf(waitThis));
	}
	
	/**
	 * Wait for the given WebElement to be clickable
	 * @param waitThis	- element to check
	 */
	protected void waitForClickable(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.elementToBeClickable(waitThis));
	}
	
	/**
	 * Wait for the given WebElement dropdown to be expanded.
	 * Checks 'aria-expended' property
	 * @param waitThis	- dropdown to check
	 */
	protected void waitForDropdown(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.attributeToBe(waitThis, "aria-expanded", "true"));
	}
	
	/**
	 * Wait for the given WebElement to have specified value.
	 * @param waitThis	-	element to check
	 * @param text		- 	text to be present
	 */
	protected void waitForText(WebElement waitThis, String text){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.textToBePresentInElementValue(waitThis, text));
	}
	
	/**
	 * Return pageobject WebDriver
	 * 
	 */
	protected WebDriver getDriver() {
		return driver;
	}
	
	
	/**
	 * Return pageobject simple name
	 */
	protected String getName()
	{
		return this.getClass().getSimpleName();
	}
	
}
