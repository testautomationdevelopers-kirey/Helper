package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PagesBase {
	
	protected WebDriver driver = null;
	protected String pageName = getClass().getSimpleName();
	protected WriterHelper fileWriter = new WriterHelper();
	protected ScreenshotTaker screenShotTaker = new ScreenshotTaker();
	
	public enum Status { FINAL, FAIL, NORMAL }; 
	
	/**
	 * Take screenshot
	 * @param status	- FINAL, FAIL OR NORMAL depending on test stage
	 * @param driver	- WebDriver of the subclass
	 * @param testName	- current @Test name
	 * @param pageName	- current page object name
	 */
	public void TakeScreenshot(Status status, WebDriver driver, String testName, String pageName)
	{
		screenShotTaker.TakeScreenshot(status, driver, testName, pageName);
	}
	
	/**
	 * Writes test result to a ResultsData.json
	 * @param className - Current Test Case
	 * @param result	- result to write into JSON
	 */
	public void WriteResult(String className, String result)
	{
		try {
			fileWriter.ResultWriter(className, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes claim result to a ClaimsData.json
	 * @param className - Current Test Case
	 * @param claim		- claim to write into JSON
	 */
	public void WriteClaim(String className, String claim)
	{
		try {
			fileWriter.ClaimWriter(className, claim);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait for the given WebElement to be visible
	 * @param waitThis	- wanted element in question
	 */
	public void WaitForVisible(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.visibilityOf(waitThis));
	}
	
	/**
	 * Wait for the given WebElement to be clickable
	 * @param waitThis	- wanted element in question
	 */
	public void WaitForClickable(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.elementToBeClickable(waitThis));
	}
	
	/**
	 * Wait for the given WebElement dropdown to be expanded.
	 * Checks 'aria-expended' property
	 * @param waitThis	- dropdown to check
	 */
	public void WaitForDropdown(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 120); 
		wait.until(ExpectedConditions.attributeToBe(waitThis, "aria-expanded", "true"));
	}
	
	/**
	 * Returns pageobject's WebDriver
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	
	public String GetName()
	{
		return this.getClass().getSimpleName();
	}

}
