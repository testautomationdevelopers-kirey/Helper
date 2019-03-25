package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import org.json.JSONObject;

public class WriterHelper {

	private String resultDataPath = "\\src\\test\\java\\it\\avivaitalia\\sis\\test\\testdata\\ResultsData.json";
	private String claimDataPath  = "\\src\\test\\java\\it\\avivaitalia\\sis\\test\\testdata\\ClaimsData.json";
	
	public static String claimVault;

	// for HANDLING ALL ABOVE METHODS AND WRITING RESULTS JSON OBJECTS TO JSON FILE
	public void ResultWriter(String className, String result) throws Exception {
		String localFilePath = getLocalFilePath(resultDataPath);
		String strFileJson = getStringFromFile(localFilePath);
		
		File fileJson = new File(localFilePath);

		JSONObject PreviousJsonObj = new JSONObject(strFileJson);
		JSONObject jsonObject = PreviousJsonObj.getJSONObject("results");
		jsonObject.put(className,
					   result);

		JSONObject currentJsonObject = new JSONObject();
		currentJsonObject.put("results",
							  jsonObject);

		writeJsonFile(fileJson,
					  currentJsonObject.toString());
	}

	// for HANDLING ALL ABOVE METHODS AND WRITING CLAIMS JSON OBJECTS TO JSON FILE
	public void ClaimWriter(String className, String claim) throws Exception {
		claimVault = claim;

		String localFilePath = getLocalFilePath(claimDataPath);
		String strFileJson = getStringFromFile(localFilePath);
		
		File fileJson = new File(localFilePath);

		JSONObject PreviousJsonObj = new JSONObject(strFileJson);

		JSONObject jsonObject = PreviousJsonObj.getJSONObject("claims");
		jsonObject.put(className,
					   claim);

		JSONObject currentJsonObject = new JSONObject();
		currentJsonObject.put("claims",
							  jsonObject);

		writeJsonFile(fileJson,
					  currentJsonObject.toString());
	}
	
	public static String getLocalFilePath(String path) {
		String workingDir = System.getProperty("user.dir");
		String filePath = Paths.get(workingDir + File.separator + path).toString();
		
		return filePath;
	}

	// for LOADING JSON FILE AS FILE INPUT STREAM
	public static String getStringFromFile(String filePath) throws Exception {
		File fl = new File(filePath);
		FileInputStream fin = new FileInputStream(fl);
		String ret = convertStreamToString(fin);
		fin.close();
		
		return ret;
	}

	// for CONVERTING STREAM TO STRING
	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			sb.append(line)
			  .append("\n");
		}
		
		return sb.toString();
	}

	// for WRITING NEW TEST RESULTS TO JSON FILE
	public static void writeJsonFile(File file, String json) {
		BufferedWriter bufferedWriter = null;
		try {

			if (!file.exists()) {
				System.out.println("File doesn't exist");
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(json);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
