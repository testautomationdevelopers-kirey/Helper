package javaResources;

public class WindowSwitchToNew {
	
	public void newWinHandleSwitch() {
		//SessionId session = ((ChromeDriver)driver).getSessionId();
		//System.out.println("Session id switching: " + session.toString());
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}

}
