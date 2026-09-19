package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//button[.='Ok']")private WebElement OkBtn;
    
    @FindBy(xpath="//button[.='Cancel']")private WebElement CancelBtn;
    
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public LogoutPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getOkBtn() {
		return OkBtn;
	}


	public WebElement getCancelBtn() {
		return CancelBtn;
	}
	
	// Business Library
	
	public void logoutOfApplication(WebDriver driver) throws Exception
	{
//		DashboardPage dbPage = new DashboardPage(driver);
//		dbPage.clickOnUserProfileImageAndLogoutLink();
		Thread.sleep(2000);
		OkBtn.click();
	}
}
