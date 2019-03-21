package javaResources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InitializeDriver {
	
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
		
		if(environment.getBrowser().equals("chrome")){
			ChromeOptions chromeOptions = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",this.getClass().getSuperclass().getResource("chromedriver.exe").getPath());
			chromeOptions.addArguments("--lang=it");
			driver=new ChromeDriver(chromeOptions);
		}
		else if(environment.getBrowser().equals("firefox")){
			System.setProperty("webdriver.gecko.driver",this.getClass().getSuperclass().getResource("geckodriver.exe").getPath());
			driver=new FirefoxDriver();
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
	
	public void DriverUse() throws IOException {
		driver = initializeDriver();
	}

}
