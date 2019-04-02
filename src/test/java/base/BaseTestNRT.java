package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import it.avivaitalia.sis.test.testdata.TestData;


public class BaseTestNRT {

	public WebDriver driver;
	public static String claimVault;
	
	protected TestData environment;
	protected String className;
	protected String testName = getClass().getSimpleName();
	
	protected String suiteTestName;
	protected JSONHelper jHelper = new JSONHelper();
	
	@BeforeClass
	@Parameters({"environment-data","results-data", "claims-data"})
	public void setUpEnvironment(String environmentData, String resultsData, String claimsData) throws Exception {
		//jenkinsJob("RestoreDB");
		System.out.println("BeforeClass");
		environment = TestData.get(environmentData);
		driver = initializeDriver();
	}
	
	
	/*//for CLOSING BROWSERS AFTER TEST IS DONE
	@AfterTest
	public void cleanUp() {
		driver.close();
		driver.quit();
	}*/

	//for CHECHKING FOR POPUP AFTER EACH METHOD AND TO CLOSE IT
	@AfterMethod	
	public void checkForPopupAndKill() throws InterruptedException {
		popup();
	}

	//for WEBDRIVER SETUP
	public WebDriver initializeDriver() throws IOException {

		if(environment.getBrowser().equals("chrome")){
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",this.getClass().getSuperclass().getResource("chromedriver.exe").getPath());
			chromeOptions.addArguments("--lang=it");
			driver = new ChromeDriver(chromeOptions);
		}
		else if(environment.getBrowser().equals("firefox")){
			System.setProperty("webdriver.gecko.driver",this.getClass().getSuperclass().getResource("geckodriver.exe").getPath());
			driver = new FirefoxDriver();
		}
		else if(environment.getBrowser().equals("headlessChrome")){
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",this.getClass().getSuperclass().getResource("chromedriver.exe").getPath());
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
		}
		else 
		{
			System.out.println("Wrong browser value.");
		}

		driver.manage().window().maximize();
		//SessionId session = ((ChromeDriver)driver).getSessionId();
		//System.out.println("Session id: " + session.toString());

		return driver;
	}
	
	//for HTTP POST request to Jenkins Server
	private void jenkinsJob(String jobName) throws Exception {

		//RestoreDB
		String url = "http://auto:113f18b72446f2147fbe1d2a0bd9029d0f@it-dv-sissrv-01:8080/job/"+ jobName +"/build";
		String user = "auto"; // username
		String pass = "auto"; // password or API token
		String authStr = user +":"+  pass;
		String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Authorization", "BASIC " + new String(encoding));

		//String urlParameters = "token=SIS_NRT";
			
		// Send post request
		con.setDoOutput(true);
		//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		//wr.writeBytes(urlParameters);
		//wr.flush();
		//wr.close();
		

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
			
		//print result
		System.out.println(response.toString());
	}

	//for "TestFailureListener.java"
	public WebDriver getDriver() {
		return driver;
	}

	//for FIREFOX DRIVER WITH MANUAL PROXY SETTINGS 
	public WebDriver setUpProxyDriver(String proxyData) {
		String proxyName = "172.21.7.8:3128";
		Proxy proxy = new Proxy();

		proxy.setProxyType(Proxy.ProxyType.MANUAL)
		.setHttpProxy(proxyName)
		.setFtpProxy(proxyName)
		.setSslProxy(proxyName);

		DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
		FirefoxOptions options = new FirefoxOptions(desiredCapabilities);

		System.setProperty("webdriver.gecko.driver","C:\\SeleniumGecko\\geckodriver.exe");
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();

		return driver;
	}

	//for WINDOW HANDLES MANAGEMENT
	public void newWinHandleSwitch() {
		//SessionId session = ((ChromeDriver)driver).getSessionId();
		//System.out.println("Session id switching: " + session.toString());
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}
	
	//for OPENING NEW WINDOW
		public void openNewWindow() {
			//driver.switchTo().window(parentHandle);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.open();");
			newWinHandleSwitch();		
		}

	//for CLOSING WINDOW
		public void closeWindow() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.close();");	
		}

	//for ALERT MANAGEMENT
	public void alert() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 50); 
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			System.out.println("Alert present & closed.");
		} catch(Exception e) {
			System.out.println("Alert NOT present.");
		}
	}

	//for POPUP MANAGEMENT
	public void popup() throws InterruptedException {
		Thread.sleep(1000);
		try {
			driver.findElement(By.cssSelector("ul.borderFrame")).findElement(By.id("msgLettiId")).click();
			System.out.println("Pop up message marked as read.");
		} catch (Exception e) {

		}
	}

	//for WARNING MANAGEMENT
	public void warning() {
		try {
			driver.findElement(By.id("warning")).click();
			System.out.println("Warning closed.");
		} catch (Exception e) {
			System.out.println("Warning not present.");
		}
	}


	//for PRINTING IN CONSOLE WHICH TEST IS RUNNING
	public void printTestRunning(String className) {	
		System.out.println(className + " is running.");
	}
	
	
	//get todays date
	public String getTodaysDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		System.out.println("Todays date is: "+todaysDate);
		return todaysDate;
		}
	
	
	
	/**
	 * Set necessary test parameters
	 * @param context
	 */
	@BeforeTest
	public void setTestParameteres(ITestContext context)
	{
		suiteTestName = context.getName();
	}
	
	/**
	 * Get this test's result
	 * @return	(String) Test result
	 */
	public String getTestResult()
	{
		return jHelper.getTestResult(suiteTestName);
	}

	/**
	 * Get this test's claim
	 * @return	(String) Test claim
	 */
	public String getTestClaim()
	{
		return jHelper.getTestClaim(suiteTestName);
	}
	
	/**
	 * Get value for a given key
	 * @param key	-	key for which are getting the value
	 * @return	(String) Value
	 */
	public String getJSONAttribute(String key)
	{
		return jHelper.getJSONAttribute(key);
	}
}
