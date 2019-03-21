package elementHelpers;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;

import pageObjects.JiraPage;

public class UploadScreenshotsVariant1 {
	
	public JiraPage getScreenshotsUpload(String className) {
		waitForClickable(uploadButton);
		this.uploadButton.click();
		waitForClickable(selectFile);
		
		try {
			Thread.sleep(500);
			this.selectFile.sendKeys(System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + className + "_HomePage.png");
			System.out.println("HomePage screenshot is uploaded.");
		} catch(Exception e) {
			System.out.println("HomePage screenshot is not present.");
		}
		
		try {
			Thread.sleep(500);
			this.selectFile.sendKeys(System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + className + "_ModifyInjuredDataPageFirst.png");
			System.out.println("ModifyInjuredDataPageFirst screenshot is uploaded.");
		} catch(Exception e) {
			System.out.println("ModifyInjuredDataPageFirst screenshot is not present.");
		}
		
		try {
			Thread.sleep(500);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById(\"attach-file-submit1\").click()");

		} catch(Exception e) {
			System.out.println("Couldn't click attach button by using standard ID.");
		}	
		return this;
	}

}
