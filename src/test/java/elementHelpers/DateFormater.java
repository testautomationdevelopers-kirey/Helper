package elementHelpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
	
public DateFormater checkDocumentPresence() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		//System.out.println("Today's date is: "+todaysDate);
		
		return this;
	}
}
