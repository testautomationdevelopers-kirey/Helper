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
	private String resultsFilePath;
	private String claimsFilePath;
	private String environmentDataFilePath;
	private String testDataFilePath;
	
	
	// testirati sa provjerom ID-a svakog procesa
	
	/******** SINGLETON ********/
	/*
	 * Only one object of this class is necessary 
	 * */
	
	// static because it needs to be accessible globally
	private static JSONHelper firstInstance = null;
	
	// private constructor assures that and object can't be created outside of this class
	private JSONHelper() {}
	
	public static JSONHelper getInstance()
	{
		// Create an object only if it doesn't exist already
		if (firstInstance == null)
		{
			// Create new object and store it as a variable
			// lazy instantiation = if the object isn't needed it will never be created
			firstInstance = new JSONHelper();
		}
		
		return firstInstance;
	}
	
	/******** SINGLETON ********/
	
	
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
			Object oldObj = parser.parse(new FileReader(resultsFilePath));
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
			
			file = new FileWriter(resultsFilePath);
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
			Object oldObj = parser.parse(new FileReader(resultsFilePath));
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
	
	/**
	 * Return value for a given environment key
	 * @param key	-	environment key for which are getting the value
	 * @return	(String) Value
	 */
	public String getEnvironmentAttribute(String key)
	{
		
		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(environmentDataFilePath));
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
		
		return "No value for environemnet found: " + key;
	}
	
	
	
	/**
	 * Used to store the last index when automating iteration over multiple WebElements
	 * @param index	-	index to be written
	 */
	@SuppressWarnings("unchecked")
	public void writeLastIndex(int index)
	{
		FileWriter file;
		

		try {	
			JSONParser parser = new JSONParser();
			Object oldObj = parser.parse(new FileReader(testDataFilePath));
			JSONObject jsonObject = (JSONObject) oldObj;
			
			String indexStr = String.valueOf(index);
			jsonObject.put("lastIndex", indexStr);
			System.out.println("Entry added-> lastIndex: " + index);

			
			file = new FileWriter(testDataFilePath);
			file.write(jsonObject.toString());
			file.flush();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
	}
	
	
	/**
	 * Used to get the last index when automating iteration over multiple WebElements
	 * @return	-2	-	valuer returned if an index hasn't been found
	 */
	public int getLastIndex()
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
				if (keyFromSet.equals("lastIndex"))
				{
					String lastIndex = (String)jsonObject.get(keyFromSet);
					
					return Integer.parseInt(lastIndex);
				}
			}
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		catch (ParseException e) { e.printStackTrace(); }
		catch (Exception e) { e.printStackTrace(); }
		
		return -2;
	}
	
	
	/**
	 * Set data file path for the current test. Used with test parameters.
	 * @param filePath	-	path of the test data file
	 */
	public void setTestDataFilePath(String filePath)
	{
		this.testDataFilePath = filePath;
	}
	
	/**
	 * Set data file path. Used with test parameters.
	 * @param filePath	-	path of the results file
	 */
	public void setResultsFilePath(String filePath)
	{
		this.resultsFilePath = filePath;
	}
	
	/**
	 * Set claims file path. Used with test parameters.
	 * @param filePath	-	path of the claims file
	 */
	public void setClaimsFilePath(String filePath)
	{
		this.claimsFilePath = filePath;
	}
	
	/**
	 * Set environment file path. Used with test parameters.
	 * @param filePath	-	path of the claims file
	 */
	public void setEnvironmentFilePath(String filePath)
	{
		this.environmentDataFilePath = filePath;
	}
	
	
}
