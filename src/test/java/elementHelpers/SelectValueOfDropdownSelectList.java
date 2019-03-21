package elementHelpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SelectValueOfDropdownSelectList {
	
	@FindBy(name="codLiqSel")
	private WebElement choseLiquidator;
	
	public SelectValueOfDropdownSelectList setLiquidator(String liquidator) {
		Select liquidatorList = new Select(choseLiquidator); 
		liquidatorList.selectByVisibleText(liquidator);
		return this;
	}

}
