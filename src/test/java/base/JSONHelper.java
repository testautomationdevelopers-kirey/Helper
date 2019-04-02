package base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

import org.json.simple.JSONObject;

public class JSONHelper {

	public static String claimVault;
	private String resultFilePath = "C:\\WORK\\AvivaItalia\\src\\test\\java\\it\\avivaitalia\\sis\\test\\testdata\\ResultsData.json";
	private String claimsFilePath = "C:\\WORK\\AvivaItalia\\src\\test\\java\\it\\avivaitalia\\sis\\test\\testdata\\ClaimsData.json";
	private String testDataFilePath = "C:\\WORK\\AvivaItalia\\src\\test\\java\\it\\avivaitalia\\sis\\test\\testdata\\TC740data.json";
	
	/**
	 * Write test result into JSON file
	 * @param testName	-	test that is being run
	 * @param result	-	test result ( PASS, FAIL, BLOCKED)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void writeResult(String testName, String result) throws Exception {
		FileWriter file;
		

		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(resultFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
			JSONObject resultsObject = (JSONObject) jsonObject.get("results");
			
			if (jsonObject.containsKey(testName))
			{
				resultsObject.replace(testName, result);
				System.out.println("RESULTS: entry replaced-> " + testName);
			}
			else
			{
				resultsObject.put(testName, result);
				System.out.println("RESULTS: entry added-> " + testName + ": " + result);
			}
			
			file = new FileWriter(resultFilePath);
			file.write(jsonObject.toString());
			file.flush();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
	}

	/**
	 * Write test claim into JSON file
	 * @param testName	-	test that is being run
	 * @param claim		-	test claim
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void writeClaim(String testName, String claim) throws Exception {
		claimVault = claim;

		FileWriter file;

		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(claimsFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
			JSONObject claimsObject = (JSONObject) jsonObject.get("claims");
			
			if (jsonObject.containsKey(testName))
			{
				claimsObject.replace(testName, claim);
				System.out.println("CLAIMS: entry replaced-> " + testName);
			}
			else
			{
				claimsObject.put(testName, claim);
				System.out.println("CLAIMS: entry added-> " + testName + ": " + claim);
			}
			
			file = new FileWriter(claimsFilePath);
			file.write(jsonObject.toString());
			file.flush();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
	}
	
	
	/*****************************************************
	 ********************** GETTERS **********************
	 */
	
	/**
	 * Returns the result of a test as a string (PASS, FAIL, BLOCKED)
	 * @param 	testName	-	test from which we are getting the result
	 * @return	(String) Test result
	 */
	public String getTestResult(String testName)
	{
		
		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(resultFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
			JSONObject resultsObject = (JSONObject) jsonObject.get("results");
			
			Set<?> testResultSet = resultsObject.keySet();
			Iterator<?> iter = testResultSet.iterator();
			
			while(iter.hasNext())
			{
				Object testFromSet = iter.next();
				if (testFromSet.equals(testName))
				{
					return (String) resultsObject.get(testFromSet);
				}
			}
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		
		return "No results found for " + testName;
	}

	/**
	 * Returns the claim for a given test as a string (info messages)
	 * @param 	testName	-	test from which we are getting the claim
	 * @return	(String) Test claim
	 */
	public String getTestClaim(String testName)
	{
		
		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(claimsFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
			JSONObject claimsObject = (JSONObject) jsonObject.get("claims");
			
			Set<?> testClaimsSet = claimsObject.keySet();
			Iterator<?> iter = testClaimsSet.iterator();
			
			while(iter.hasNext())
			{
				Object testFromSet = iter.next();
				if (testFromSet.equals(testName))
				{
					return (String) claimsObject.get(testFromSet);
				}
			}
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		
		return "No claim found for: " + testName;
	}
	
	/**
	 * Return value for a given key
	 * @param key	-	key for which are getting the value
	 * @return	(String) Value
	 */
	public String getJSONAttribute(String key)
	{
		
		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(testDataFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
				
			Set<?> keyResultSet = jsonObject.keySet();
			Iterator<?> iter = keyResultSet.iterator();
			
			while(iter.hasNext())
			{
				Object keyFromSet = iter.next();
				if (keyFromSet.equals(key))
				{
					return (String) jsonObject.get(keyFromSet);
				}
			}
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		
		return "No value found for: " + key + " key";
	}
}
