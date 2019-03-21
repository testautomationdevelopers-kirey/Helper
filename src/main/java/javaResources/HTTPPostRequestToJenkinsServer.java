package javaResources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPPostRequestToJenkinsServer {
	
	private void jenkinsJob(String jobName) throws Exception {

		//RestoreDB
		String url = "http://auto:113f18b72446f2147fbe1d2a0bd9029d0f@it-dv-sissrv-01:8080/job/"+ jobName +"/build";
		String user = "auto"; // username
		String pass = "auto"; // password or API token
		String authStr = user +":"+  pass;
		String encoding = DatatypeConverter.printBase64Binary(authStr.getBytes("utf-8"));
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Authorization", "BASIC " + new String(encoding));

		//String urlParameters = "token=SIS_NRT";
			
		// Send post request
		con.setDoOutput(true);
		//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		//wr.writeBytes(urlParameters);
		//wr.flush();
		//wr.close();
		

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
			
		//print result
		System.out.println(response.toString());
	}

}
