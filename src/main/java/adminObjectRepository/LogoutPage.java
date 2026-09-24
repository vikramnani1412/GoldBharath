package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	// Rule-1:-Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//button[@class='logout']")private WebElement LogoutBtn;
    
    @FindBy(xpath="//button[.='Yes']")private WebElement YesBtn;
    
    @FindBy(xpath="//button[.='No']/following-sibling::button[.='No']")private WebElement NoBtn;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public LogoutPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	

	public WebElement getLogoutBtn() {
		return LogoutBtn;
	}


	public WebElement getYesBtn() {
		return YesBtn;
	}


	public WebElement getNoBtn() {
		return NoBtn;
	}

	// Business Library

	public void logoutOfApplication() throws Exception
	{
		Thread.sleep(2000);
		LogoutBtn.click();
		Thread.sleep(2000);
		YesBtn.click();
		Thread.sleep(2000);
	}
	
}
