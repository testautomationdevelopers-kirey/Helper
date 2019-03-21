package javaResources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetClassName {
	

	//for GETTING TEST CLASSNAME
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	//get todays date
		public String getTodaysDate() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
			System.out.println("Todays date is: "+todaysDate);
			return todaysDate;
			}//get todays date
		public String getTodaysDate() {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String todaysDate = dateFormat.format(date);
			System.out.println("Todays date is: "+todaysDate);
			return todaysDate;
			}

}
