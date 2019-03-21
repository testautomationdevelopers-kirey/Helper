package elementHelpers;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenshotOfPage {
	
	public GetScreenshotOfPage getScreenshotOfPageWithoutClaimWriter(String className, WebDriver webdriver) throws Exception {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + className + "_HomePage.png"));
		return this;
	}

}
