package elementHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindByWebElementVariants {
	
	public WebDriver driver; 
	
	@FindBy(id="login-form-username")
	private WebElement userJira;
	
	@FindBy(name="login")
	private WebElement login;
	
	@FindBy(xpath="//*[contains(text(),'Assigned to Me')]")
	private WebElement assignText;
	
	@FindBy(xpath="//b[text()='Sinistri']") 
	private WebElement accidents2;
	
	@FindBy(xpath="//span[contains(@id, 'executionStatus')]")
	private WebElement executionStatusChange;
	
	@FindBy(xpath="//td[@onclick=\"clessidra();location.href='request/SisSinistriLight'\"]")
	private WebElement accidents;
	
	@FindBy(xpath="//td[@class='zephyr-test-execution-entry-cycle']")
	private WebElement testCycle;
	
	@FindBy(xpath="(//img[@data-action='Execute data-customevent='])[position()=1]")
	private WebElement clickableE;
	
}
