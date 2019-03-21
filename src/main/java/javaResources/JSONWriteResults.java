package javaResources;

public class JSONWriteResults {
	
	public JSONWriteResults writeResult(String className) throws Exception {
		if (TRUE) {
			resultWriter(className, "PASS");
			System.out.println("Test PASSED!");
		} else {
			resultWriter(className, "FAIL");
			System.out.println("Test FAILED!");
		}
		return this;
	}	

}
