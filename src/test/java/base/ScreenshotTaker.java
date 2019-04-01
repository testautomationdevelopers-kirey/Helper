package base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import it.avivaitalia.sis.base.PagesBase.Status;

public class ScreenshotTaker {

	private String screenshotFolderPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
	
	public void screenshot(Status status, WebDriver driver, String testName, String pageName)
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			if (status == Status.FINAL)
			{
				String pathname = screenshotFolderPath + "FINAL_" + testName + "_" + pageName + ".png";
				FileUtils.copyFile(scrFile, new File(pathname));
				System.out.println("Screenshot: " + pathname);
			}	
			else if (status == Status.FAIL)
			{
				String pathname = screenshotFolderPath + "FAILED_" + testName + "_" + pageName + ".png";
				FileUtils.copyFile(scrFile, new File(pathname));
				System.out.println("Screenshot: " + pathname);
			}		
			else if (status == Status.NORMAL)
			{
				String pathname = screenshotFolderPath + testName + "_" + pageName + ".png";
				FileUtils.copyFile(scrFile, new File(pathname));
				System.out.println("Screenshot: " + pathname);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
