package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import it.avivaitalia.sis.base.BaseTestNRT;
import it.avivaitalia.sis.base.JSONHelper;
import it.avivaitalia.sis.base.PagesBase.Status;
import it.avivaitalia.sis.base.ScreenshotTaker;


public class TestListener implements ITestListener { 

	public void onTestFailure(ITestResult result) {
		JSONHelper jHelper = JSONHelper.getInstance();
		ScreenshotTaker screenshotTaker = new ScreenshotTaker();
			
		String TestName = result.getTestContext().getName();
		String PageName = result.getName();
		String Exception = result.getThrowable().toString();
		
		WebDriver webDriver = ((BaseTestNRT)result.getInstance()).getDriver();
		
		if(Exception.contains("TimeoutException")) {
			try {
				jHelper.writeResult(TestName, "BLOCKED");
				jHelper.writeClaim(TestName, "Test blocked by SIS system problems.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				jHelper.writeResult(TestName, "FAIL");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			try {
				jHelper.writeClaim(TestName, "Error message.");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (webDriver != null)
		{
			screenshotTaker.screenshot(Status.FAIL, webDriver, TestName, PageName);
		}
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("FINISHED: " + context.getName());

	}

	public void onStart(ITestContext context) {
		String Testname = context.getName();
		System.out.println("RUNNING THIS TEST: " + Testname);

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