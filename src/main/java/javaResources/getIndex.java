package javaResources;

public class getIndex {
	
	private int getInjuredMenuPosition(String injuredName) {
		
		WebElement searchPosition;
		int wantedPosition = 1;
		String injuredOnPosition;
		String wantedInjured = injuredName;

		try {            
			
			int tagCount = driver.findElements(By.xpath("//a[@name='DannInfo']")).size();
			System.out.println("Tag count is equal to: "+tagCount);
			int limit = tagCount+1;
			System.out.println("Limit is equal to: "+limit);
			
			for(int i = 2; i<=limit; i++) {                       
				searchPosition = driver.findElement(By.xpath("//*[@id=\"main\"]/table[2]/tbody/tr["+i+"]/td[2]/a"));
				injuredOnPosition = searchPosition.getText();
				System.out.println("Injured Name on position "+i+" is: " +injuredOnPosition);
				wantedPosition++;
				if(wantedInjured.equals(injuredOnPosition)) {
					System.out.println("Wanted injured position located: "+i+".");
					break;	
				} 
			}
		} catch (Exception e) {
			System.out.println("Wanted Injured NOT located!");
			
		}
		return wantedPosition-1;
	}
	
	public OperationMenuPage clickVisualisePrint(String injuredPerson) {
		WebDriverWait wait = new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("DannInfo")));

		int MenuPositionOfInjured;
		MenuPositionOfInjured = getInjuredMenuPosition(injuredPerson);
		driver.findElement(By.xpath("(//img[@title='Visualizza Stampe'])[position()="+MenuPositionOfInjured+"]")).click();
		
		return this;
	}

	public OperationMenuPage clickViewDetails() {
		viewDetails.click();
		return this;
	}
	

}
