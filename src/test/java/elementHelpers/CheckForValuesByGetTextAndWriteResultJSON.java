package elementHelpers;

import pageObjects.OperationMenuPage;

public class CheckForValuesByGetTextAndWriteResultJSON {
	
	public OperationMenuPage checkLiquidatorGroupCanalization(String liquidatorGroup, String Signature, String expectedPl, String className) throws Exception {
		String expectedSignature = Signature; 
		String expectedLiquidatorGroup = liquidatorGroup;
		String expectedPL = expectedPl;

		String actualLiquidatorGroup = liquidatorGroupText.getText();
		Boolean checkLiquidatorGroup = actualLiquidatorGroup.contains(expectedLiquidatorGroup);

		/*String actualPL = pl.getText();
		Boolean checkPL = expectedPL.equals(actualPL);*/

		if(expectedSignature == "" && expectedPL == "") {

			if(checkLiquidatorGroup) {
				System.out.println("Liquidator group is correct.");
				resultWriter(className, "PASS");
			} else {
				System.out.println("ERROR - incorrect values.");
				System.out.println("LIQUIDATOR GROUP correct? " + checkLiquidatorGroup);
				resultWriter(className, "FAIL");
			}
		}  else if (expectedSignature == "") {
			String actualPL = pl.getText();
			Boolean checkPL = expectedPL.equals(actualPL);

			if(checkLiquidatorGroup && checkPL) {
				System.out.println("Liquidator group and PL are all correct.");
				resultWriter(className, "PASS");
			} else {
				System.out.println("ERROR - incorrect values.");
				System.out.println("LIQUIDATOR GROUP correct? " + checkLiquidatorGroup);
				System.out.println("PL correct? " + checkPL);
				resultWriter(className, "FAIL");
			}
		}  else if (expectedPL == "") {
			String actualSignature = signature.getText();	
			Boolean checkSignature = expectedSignature.equals(actualSignature);

			if(checkLiquidatorGroup && checkSignature) {
				System.out.println("Liquidator group and signature are all correct.");
				resultWriter(className, "PASS");
			} else {
				System.out.println("ERROR - incorrect values.");
				System.out.println("LIQUIDATOR GROUP correct? " + checkLiquidatorGroup);
				System.out.println("SIGNATURE correct? " + checkSignature);
				resultWriter(className, "FAIL");
			}
		} else {
			String actualSignature = signature.getText();	
			Boolean checkSignature = expectedSignature.equals(actualSignature);

			String actualPL = pl.getText();
			Boolean checkPL = expectedPL.equals(actualPL);

			if(checkLiquidatorGroup && checkSignature && checkPL) {
				System.out.println("Liquidator group, signature and PL are all correct.");
				resultWriter(className, "PASS");
			} else {
				System.out.println("ERROR - incorrect values.");
				System.out.println("LIQUIDATOR GROUP correct? " + checkLiquidatorGroup);
				System.out.println("SIGNATURE correct? " + checkSignature);
				System.out.println("PL correct? " + checkPL);
				resultWriter(className, "FAIL");
			}
		}
		return this;
	}

}
