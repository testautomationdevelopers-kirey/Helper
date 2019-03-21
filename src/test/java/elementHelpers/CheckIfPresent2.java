package elementHelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckIfPresent2 {
	
	public CheckIfPresent2 checkAccidentNotePresence(String SISuser, String noteDate) {
		WebDriverWait wait = new WebDriverWait(driver,200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@id,'titolo')]")));
		
		WebElement searchAccidentNote;
		String accidentNote;
		String wantedSISuser = SISuser;
		String wantedNoteDate = noteDate;
		
		try {
			int tagCount = driver.findElements(By.xpath("//td[contains(@id,'titolo_')]")).size();
			//System.out.println("Tag count is equal to: "+tagCount);
			
			for(int i = 0; i<tagCount; i++) {
				searchAccidentNote = driver.findElement(By.id("titolo_"+i));
				accidentNote = searchAccidentNote.getText();
				
				if(accidentNote.contains(wantedNoteDate) && accidentNote.contains(wantedSISuser)) {
					System.out.println("Accident note added on the day "+wantedNoteDate+" by SIS user "+wantedSISuser+" present!");
				} 
			}
		} catch (Exception e) {
			System.out.println("Wanted accident note NOT present!");
		}
		
		return this;
	}

}
