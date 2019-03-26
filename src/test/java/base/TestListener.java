package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import it.avivaitalia.sis.base.BaseTestNRT;
import it.avivaitalia.sis.base.WriterHelper;
import it.avivaitalia.sis.base.PagesBase.Status;
import it.avivaitalia.sis.base.ScreenshotTaker;


public class TestListener implements ITestListener { 

	//for CAPTURING SCREENSHOT ON TEST FAILURE TO "screenshotFolderPath"
	//for WRITING EXECUTION STATUS AS "FAIL" WHEN TEST STOPS BEFORE END
	public void onTestFailure(ITestResult result) {
		WriterHelper writerHelper = new WriterHelper();
		ScreenshotTaker screenshotTaker = new ScreenshotTaker();
			
		String TestName = result.getTestContext().getName();
		String PageName = result.getName();
		String Exception = result.getThrowable().toString();
		
		WebDriver webDriver = ((BaseTestNRT)result.getInstance()).getDriver();
		
		if(Exception.contains("TimeoutException")) {
			try {
				writerHelper.ResultWriter(TestName, "BLOCKED");
				writerHelper.ClaimWriter(TestName, "Test blocked by SIS system problems.");
			} catch (Exception e1) {
				// 
				e1.printStackTrace();
			}
		} else {
			try {
				writerHelper.ResultWriter(TestName, "FAIL");
			} catch (Exception e1) {
				// 
				e1.printStackTrace();
			}
			
			try {
				writerHelper.ClaimWriter(TestName, "Error message.");
			} catch (Exception e1) {
				// 
				e1.printStackTrace();
			}
		}

		if (webDriver != null)
		{
			screenshotTaker.TakeScreenshot(Status.FAIL, webDriver, TestName, PageName);
		}
	}
	
	public void onFinish(ITestContext result) {
		System.out.println("FINISHED: " + result.getName());
	}

	public void onStart(ITestContext arg0) {
		//skip

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		//skip

	}


	public void onTestSkipped(ITestResult arg0) {
		//skip

	}

	public void onTestStart(ITestResult result) {
		
		

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("SUCCESS: " + result.getName());
	}

}