package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="(//li[.='Home']/ancestor::div//img[@class='goldbharathimg'])[1]")private WebElement GoldBharathImg;
    
    @FindBy(xpath="//li[.='Home']")private WebElement HomeLnk;
    
    @FindBy(xpath="//li[.='About Us']")private WebElement AboutUsLnk;
    
    @FindBy(xpath="//li[.='Products']")private WebElement ProductsLnk;
    
    @FindBy(xpath="//li[.='Privacy Policy']")private WebElement PrivacyLnk;
    
    @FindBy(xpath="//li[.='Contact Us']")private WebElement ContactUsLnk;
    
    @FindBy(xpath="//div[contains(@routerlink,'notifications')]")private WebElement NotificationsIcon;
    
    @FindBy(xpath="//span[@class='userDetails']")private WebElement UserNameEle;
    
    //////
    
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
    
    ///////////// Print gold and silver rate with this date and time element for getting exact time and date
    
    @FindBy(xpath="//div[@class='date-time']")private WebElement CurrentDateAndTime;
  
    @FindBy(xpath="//div[.='SPOT GOLD']/following-sibling::div[contains(normalize-space(.), '$')]")private WebElement SpotGoldOnlyLivePriceTxt;
    
    @FindBy(xpath="//div[.='SPOT SILVER']/following-sibling::div[contains(normalize-space(.), '$')]")private WebElement SpotSilverOnlyLivePriceTxt;
    
    @FindBy(xpath="//div[.='SPOT RUPEE']/following-sibling::div[contains(normalize-space(.), '₹')]")private WebElement SpotRupeeOnlyLivePriceTxt;
  
    @FindBy(xpath="//div[@class='commodity-card gold-card']")private WebElement GoldPriceTotalSection;
    
    @FindBy(xpath="//p[normalize-space()='Live Price Per Gram']/following-sibling::div[1]")private WebElement GoldOnlyLivePriceTxt;
    
    @FindBy(xpath="//button[.='Book Gold']")private WebElement BookGoldBtn;
    
    @FindBy(xpath="//button[.='Buy Gold']")private WebElement BuyGoldBtn;
  
    @FindBy(xpath="//div[@class='commodity-card silver-card']")private WebElement SilverPriceTotalSection;
    
    @FindBy(xpath="//p[normalize-space()='Live Price Per Kg']/following-sibling::div[1]")private WebElement SilverOnlyLivePriceTxt;
    
    @FindBy(xpath="//button[.='Book Silver']")private WebElement BookSilverBtn;
    
    @FindBy(xpath="//button[.='Buy Silver']")private WebElement BuySilverBtn;
    
    
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public DashboardPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	
	public WebElement getGoldBharathImg() {
		return GoldBharathImg;
	}

	public WebElement getHomeLnk() {
		return HomeLnk;
	}

	public WebElement getAboutUsLnk() {
		return AboutUsLnk;
	}

	public WebElement getProductsLnk() {
		return ProductsLnk;
	}

	public WebElement getPrivacyLnk() {
		return PrivacyLnk;
	}

	public WebElement getContactUsLnk() {
		return ContactUsLnk;
	}

	public WebElement getNotificationsIcon() {
		return NotificationsIcon;
	}

	public WebElement getUserNameEle() {
		return UserNameEle;
	}

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

	public WebElement getCurrentDateAndTime() {
		return CurrentDateAndTime;
	}

	public WebElement getSpotGoldOnlyLivePriceTxt() {
		return SpotGoldOnlyLivePriceTxt;
	}

	public WebElement getSpotSilverOnlyLivePriceTxt() {
		return SpotSilverOnlyLivePriceTxt;
	}

	public WebElement getSpotRupeeOnlyLivePriceTxt() {
		return SpotRupeeOnlyLivePriceTxt;
	}

	public WebElement getGoldPriceTotalSection() {
		return GoldPriceTotalSection;
	}

	public WebElement getGoldOnlyLivePriceTxt() {
		return GoldOnlyLivePriceTxt;
	}

	public WebElement getBookGoldBtn() {
		return BookGoldBtn;
	}

	public WebElement getBuyGoldBtn() {
		return BuyGoldBtn;
	}

	public WebElement getSilverPriceTotalSection() {
		return SilverPriceTotalSection;
	}

	public WebElement getSilverOnlyLivePriceTxt() {
		return SilverOnlyLivePriceTxt;
	}

	public WebElement getBookSilverBtn() {
		return BookSilverBtn;
	}

	public WebElement getBuySilverBtn() {
		return BuySilverBtn;
	}
	
	// Business Library
	
	public void clickOnUserProfileImageAndLogoutLink() throws Exception
	{
		Thread.sleep(2000);
		UserProfileImg.click();
		Thread.sleep(2000);
		LogoutLnk.click();
	}
}
