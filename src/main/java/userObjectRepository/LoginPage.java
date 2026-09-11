package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class LoginPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//input[@placeholder='Enter your mobile no.']")private WebElement MobileEdt;
    
    @FindBy(xpath="//div[.=' Mobile number is required. ']")private WebElement MobileNumRequiredError;
    
    @FindBy(xpath="//div[.=' Please enter a valid mobile number starting with 6, 7, 8, or 9. ']")private WebElement EnterValidMobileNumError;
    
    @FindBy(xpath="//button[.='Login']")private WebElement LoginBtn;
    
    @FindBy(xpath="//span[.='Register']")private WebElement RegisterBtn;
    
    @FindBy(xpath="(//input[@autocomplete='one-time-code'])[1]")private WebElement OTPfrstBox;
    
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getMobileEdt() {
		return MobileEdt;
	}



	public WebElement getEnterValidMobileNumError() {
		return EnterValidMobileNumError;
	}


	public WebElement getMobileNumRequiredError() {
		return MobileNumRequiredError;
	}



	public WebElement getLoginBtn() {
		return LoginBtn;
	}



	public WebElement getOTPfrstBox() {
		return OTPfrstBox;
	}


	public WebElement getRegisterBtn() {
		return RegisterBtn;
	}

	// Business Library
	
	public void LoginToApplication(WebDriver driver, String MobileNumber) throws Exception
	{
		WebDriverUtility wUtil = new WebDriverUtility();
		
		WelcomePage wPage = new WelcomePage(driver);
		wPage.clickOnLoginLink();
		
		Thread.sleep(2000);
		MobileEdt.sendKeys(MobileNumber);
		Thread.sleep(2000);
		LoginBtn.click();
		Thread.sleep(2000);
		wUtil.waitForElementToBeClickable(null, OTPfrstBox);
		OTPfrstBox.sendKeys("123456");
		
	}
	
}
