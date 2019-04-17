package base;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagesBase {
	
	protected WebDriver driver = null;
	protected String pageName = getClass().getSimpleName();
	protected JSONHelper jHelper =JSONHelper.getInstance();
	private ScreenshotTaker screenShotTaker = new ScreenshotTaker();
	
	private int waitSeconds = 5;
	
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
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.visibilityOf(waitThis));
	}
	
	
	/**
	 * Wait for the given WebElement to have specific attribute value
	 * 
	 * @param waitThis
	 * @param attribute
	 * @param value
	 */
	protected void waitForAttribute(WebElement waitThis, String attribute, String value){
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.attributeToBe(waitThis, attribute, value));
	}
	
	
	/**
	 * Wait for the given WebElement to be clickable
	 * @param waitThis	- element to check
	 */
	protected void waitForClickable(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.elementToBeClickable(waitThis));
	}
	
	
	/**
	 * Wait for the given WebElement dropdown to be expanded.
	 * Checks 'aria-expended' property
	 * @param waitThis	- dropdown to check
	 */
	protected void waitForDropdown(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 60); 
		wait.until(ExpectedConditions.attributeToBe(waitThis, "aria-expanded", "true"));
	}
	
	
	/**
	 * Wait for the given WebElement to have specified value.
	 * @param waitThis	-	element to check
	 * @param text		- 	text to be present
	 */
	protected void waitForText(WebElement waitThis, String text){
		WebDriverWait wait = new WebDriverWait(driver, 60); 
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
	 * Check for alert presence and close it if it exists
	 * @param seconds
	 */
	protected void alert(int seconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds); 
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			System.out.println("Alert present and closed.");
		} catch(Exception e) {
			System.out.println("Alert NOT present.");
		}
	}
	
	
	/**
	 * Check if element exists by it's ID and NAME. Check overloaded function for xpath
	 * @param element	-	element to check
	 * @return	boolean
	 */
	protected boolean checkElementExists(WebElement element)
	{
		boolean bExists = false;
		
		try {
			if (bExists == false)
			{
				WebDriverWait wait = new WebDriverWait(driver, waitSeconds); 
				wait.until(ExpectedConditions.visibilityOf(element));
				driver.findElement(By.name(element.getAttribute("name")));
				bExists = true;
			}	
		}
		catch (NoSuchElementException e) {}
		catch (ElementNotVisibleException e) {}
		catch (Exception e) {}
		
		try {
			if (bExists == false)
			{
				WebDriverWait wait = new WebDriverWait(driver, waitSeconds); 
				wait.until(ExpectedConditions.visibilityOf(element));
				driver.findElement(By.id(element.getAttribute("id")));
				bExists = true;
			}	
		}
		catch (NoSuchElementException e) {}
		catch (ElementNotVisibleException e) {}
		catch (Exception e) {}
		
		if (bExists)
		{
			System.out.println("Found element!");
			return true;
		}
		else
		{
			System.out.println("Element not found!");
			return false;
		}
	}
	
	
	/**
	 * Check if element exists by it's XPATH or CSS SELECTOR. Check overloaded function for ID and NAME
	 * @param element	-	element to check
	 * @param xpath		-	xpath for that element
	 * @return	boolean
	 */
	protected boolean checkElementExists(WebElement element, String string)
	{
		boolean bExists = false;
		
		try {
			if (bExists == false)
			{
				WebDriverWait wait = new WebDriverWait(driver, waitSeconds); 
				wait.until(ExpectedConditions.visibilityOf(element));
				driver.findElement(By.xpath(string));
				bExists = true;
			}	
		}
		catch (NoSuchElementException e) {}
		catch (ElementNotVisibleException e) {}
		catch (Exception e) {}
		
		try {
			if (bExists == false)
			{
				WebDriverWait wait = new WebDriverWait(driver, waitSeconds); 
				wait.until(ExpectedConditions.visibilityOf(element));
				driver.findElement(By.cssSelector(string));
				bExists = true;
			}	
		}
		catch (NoSuchElementException e) {}
		catch (ElementNotVisibleException e) {}
		catch (Exception e) {}
		
		
		if (bExists)
		{
			System.out.println("Found element!");
			return true;
		}
		else
		{
			System.out.println("Element not found!");
			return false;
		}
	}
	
	
	/**
	 * Return pageobject simple name
	 */
	protected String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	
	protected int getLastIndex(String key)
	{
		return jHelper.getLastIndex(key);
	}
	
	protected void writeLastIndex(String key, int index)
	{
		jHelper.writeLastIndex(key, index);;
	}
	
}
