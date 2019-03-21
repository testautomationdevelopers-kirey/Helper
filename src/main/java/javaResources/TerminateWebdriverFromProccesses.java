package javaResources;

import java.io.IOException;

public class TerminateWebdriverFromProccesses {
	
	//for TERMINATING WEBDRIVERS FROM PROCCESSES 
		public void driverKiller() throws IOException {
			if(environment.getBrowser().equals("firefox")){
				Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
			}
			else
			{
				Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			}
		}


}
