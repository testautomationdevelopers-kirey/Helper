package elementHelpers;

public class CheckIfPresent {
	
	public CheckIfPresent checkIfMenuIsPresent(String className) throws Exception {
		String actualState = this.WEBELEMENT.getText();
		String expectedState = "Sinistri";
		Boolean checkAccident = actualState.contains(expectedState);
		if (checkAccident) {
			resultWriter(className, "PASS");
			System.out.println("Test PASSED!");
		} else {
			resultWriter(className, "FAIL");
			System.out.println("Test FAILED!");
		}
		return this;
	}	

}
