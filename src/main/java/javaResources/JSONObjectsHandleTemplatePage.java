package javaResources;


import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONObjectsHandleTemplatePage {

	@JsonProperty("browser")
	String browser;
	public String getBrowser() {
		return browser;
	}
	
	public static JSONObjectsHandleTemplatePage get(String filename) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new File(filename), JSONObjectsHandleTemplatePage.class);
	}
}