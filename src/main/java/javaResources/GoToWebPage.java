package javaResources;

import pageObjects.JiraPage;

public class GoToWebPage {
	
	public JiraPage getJiraDashboardPage() {
		driver.get("https://jira.aviva.co.uk/secure/Dashboard.jspa"); 
		return this;
	}

}
