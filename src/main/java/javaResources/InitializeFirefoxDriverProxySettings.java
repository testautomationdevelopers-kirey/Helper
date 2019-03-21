package javaResources;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InitializeFirefoxDriverProxySettings {
	
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

}
