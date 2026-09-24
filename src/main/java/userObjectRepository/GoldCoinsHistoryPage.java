package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoldCoinsHistoryPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="(//td[.='1']/following-sibling::td[contains(.,'g')])[1]")private WebElement OrderHistoryEle;
    
	//Rule-2:Create a constructor to initilise these elements
    
	public GoldCoinsHistoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public void getTotalGoldHistoryWhatWeBuyed() throws Exception
	{
		Thread.sleep(2000);
		String History = OrderHistoryEle.getText();
		System.out.println(History);
	}
	
}
