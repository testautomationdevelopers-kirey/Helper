package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClickButtonByCheckingTableValues {
	
	public ClickButtonByCheckingTableValues clickExecuteTest(String testCycle, String version){
		WebElement searchTestCyle;
		String actualTestCycle;
		String expectedTestCycle = testCycle;
		
		WebElement searchVersion;
		String actualVersion;
		String expectedVersion = version;
		
		waitForClickable(this.clickableE);
		
		try {
			int rowCount = driver.findElements(By.xpath("//div[@data-columnid='versionName']")).size();
			
			for(int i = 1; i<=rowCount;i++) {
				searchVersion = driver.findElement(By.xpath("(//div[@data-columnid='versionName'])[position()="+i+"]"));
				actualVersion = searchVersion.getText();	

				if(actualVersion.equals(expectedVersion)) {
					System.out.println("Wanted VERSION located: "+actualVersion+".");
						for(int j = i; j<15;j++) {
							searchTestCyle = driver.findElement(By.xpath("(//div[@data-columnid='cycleName'])[position()="+j+"]"));
							actualTestCycle = searchTestCyle.getText();	

							if(actualTestCycle.equals(expectedTestCycle)) {
								System.out.println("Wanted TestCycle name located: "+actualTestCycle+". Click on 'E'.");
								driver.findElement(By.xpath("(//a[@data-action='Execute'])[position()="+j+"]")).click();
								break;					      
							} 
						}
				      
				} 
			}
		} catch (Exception e) {
				
		}
		return this;
	}

}
