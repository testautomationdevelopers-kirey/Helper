package javaResources;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLicensePlate {

	public String GenerateLicensePlate()
	{
		int min = 100;
		int max = 999;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		Random r = new Random();
		char turn = (char) (r.nextInt(26) + 'A');
		char river = (char) (r.nextInt(26) + 'A');
		
		return "EM" + randomNum + turn + river;
	}
	
	public String GenerateLicensePlate2()
	{
		Random rand = new Random();
		int  n = rand.nextInt(899) + 100;
		String licensePlate = String.valueOf(n);
		licensePlate = "EM" + n + "AB";
		return licensePlate;
	}

}
