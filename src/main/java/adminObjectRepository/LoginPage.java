package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	// Rule-1:-Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//input[@placeholder='Enter User Name']")private WebElement UserNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Password']")private WebElement PasswordEdt;
    
    @FindBy(xpath="//i[@class='fa fa-eye-slash']")private WebElement PasswordViewIcon;
    
    @FindBy(xpath="//button[.='Login']")private WebElement LoginBtn;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	

	public WebElement getUserNameEdt() {
		return UserNameEdt;
	}


	public WebElement getPasswordEdt() {
		return PasswordEdt;
	}


	public WebElement getPasswordViewIcon() {
		return PasswordViewIcon;
	}


	public WebElement getLoginBtn() {
		return LoginBtn;
	}
	
	// Business Library
	
	public void loginToAdmin(String Username, String Password) throws Exception
	{
		Thread.sleep(2000);
		UserNameEdt.sendKeys(Username);
		Thread.sleep(2000);
		PasswordEdt.sendKeys(Password);
		Thread.sleep(2000);
		LoginBtn.click();
	}
}
