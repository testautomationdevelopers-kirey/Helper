package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickOnValueIfNotPresentAlready {
	
	@FindBy(xpath="//span[contains(@id, 'executionStatus')]")
	private WebElement executionStatusChange;
	
	@FindBy(xpath="//span[contains(@id, 'current-execution-status')]")
	private WebElement executionStatusChanged;
	
	@FindBy(xpath="//div[contains(@id,'exec_status')]")
	private WebElement executionStatus;
	
	@FindBy(xpath="//span[@class='icon icon-save']")
	private WebElement saveStatus;
	
	public void waitForClickable(WebElement waitThis){
		WebDriverWait wait = new WebDriverWait(driver, 150); 
		wait.until(ExpectedConditions.elementToBeClickable(waitThis));
	}
	
	public void waitForTextToAppear(WebElement waitThis, String textToAppear) {
		WebDriverWait wait = new WebDriverWait(driver, 200);
	    wait.until(ExpectedConditions.textToBePresentInElement(waitThis, textToAppear));
	}
	
	public ClickOnValueIfNotPresentAlready setExecutionStatus(String executionStatus){
		waitForClickable(this.executionStatusChange);
	
		if (executionStatusChanged.getText().contains(executionStatus)){
			System.out.println("This test STATUS is already " + executionStatus + ".");
		} else {
			this.executionStatusChange.click();
			driver.findElement(By.xpath("(//li[@data-str='"+executionStatus+"'])")).click();
		}
		
		waitForTextToAppear(executionStatusChanged, executionStatus);
		return this;

	}

}
