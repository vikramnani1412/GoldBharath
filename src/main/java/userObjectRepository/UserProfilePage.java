package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//a[@class='user-logo-letter']")private WebElement UserProfileImg;
    
    @FindBy(xpath="//div[@class='my-profiles-div']")private WebElement UserProfileDetailsEle;
    
    @FindBy(xpath="//div[@class='my-profiles-div']//button[@aria-label='Close']")private WebElement UserProfileDetailsCloseBtn;
    
    @FindBy(xpath="//div[.='KYC']")private WebElement KycLnk;
    
    @FindBy(xpath="//div[.='Coin Orders']")private WebElement CoinOrdersLnk;
    
    @FindBy(xpath="//div[.='Orders']")private WebElement OrdersLnk;
    
    @FindBy(xpath="//div[.='Margin']")private WebElement MarginLnk;
    
    @FindBy(xpath="//div[.='Rate Alert']")private WebElement RateAlertLnk;
    
    @FindBy(xpath="//div[.='Gold History']")private WebElement GoldHistoryLnk;
    
    @FindBy(xpath="//div[.='Log Out']")private WebElement LogoutLnk;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public UserProfilePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
    

	public WebElement getUserProfileImg() {
		return UserProfileImg;
	}


	public WebElement getUserProfileDetailsEle() {
		return UserProfileDetailsEle;
	}


	public WebElement getUserProfileDetailsCloseBtn() {
		return UserProfileDetailsCloseBtn;
	}


	public WebElement getKycLnk() {
		return KycLnk;
	}


	public WebElement getCoinOrdersLnk() {
		return CoinOrdersLnk;
	}


	public WebElement getOrdersLnk() {
		return OrdersLnk;
	}


	public WebElement getMarginLnk() {
		return MarginLnk;
	}


	public WebElement getRateAlertLnk() {
		return RateAlertLnk;
	}


	public WebElement getGoldHistoryLnk() {
		return GoldHistoryLnk;
	}


	public WebElement getLogoutLnk() {
		return LogoutLnk;
	}
	
	// Business Library
	
	public void clickOnKycLink() throws Exception
	{
		Thread.sleep(2000);
		KycLnk.click();
	}
	
	public void clickOnLogoutLink() throws Exception
	{
		Thread.sleep(2000);
		LogoutLnk.click();
	}
	
}
