package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoinsPage {

	//Finding WebElements Using @FindBy Annotations
	
    @FindBy(xpath="//span[.='Dashboard']")private WebElement DashboardLnk;
    
    @FindBy(xpath="//span[.='Manage Distributor']")private WebElement ManageDistributorLnk;
    
    @FindBy(xpath="//span[.='Manage Order']")private WebElement ManageOrderLnk;
    
    @FindBy(xpath="//span[.='Coins']")private WebElement CoinsLnk;
    
    @FindBy(xpath="//i[@class='fa-solid fa-arrow-left icon-vertical ng-star-inserted']")private WebElement DashBoardCloseBtn;
    
    @FindBy(xpath="//i[@class='fa-solid fa-bell user-icon']")private WebElement NotificationsIcon;
    
    @FindBy(xpath="//button[@class='logout']")private WebElement LogoutBtn;
    
    ///////////
    
    @FindBy(xpath="//button[.=' Gold ']")private WebElement GoldBtn;
    
    @FindBy(xpath="//button[.=' Silver ']")private WebElement SilverBtn;
    
    @FindBy(xpath="//select[@class='form-control custom-input save-gold ng-pristine ng-valid ng-touched']")private WebElement MetalTypeDrpDwn;
    
    @FindBy(xpath="//label[.='Coin Weight (gm)']/following-sibling::input[@type='number']")private WebElement CoinWeightEdt;
    
    @FindBy(xpath="//label[.='Making Charge (₹)']/following-sibling::input[@type='number']")private WebElement MakingChargeEdt;
    
    @FindBy(xpath="//button[.=' Save Charge ']")private WebElement SaveChargeBtn;
    
    //Edit Button According to Gram need to dynamic
    @FindBy(xpath="//td[normalize-space()='1 gm']/following-sibling::td/button[' Edit ']")private WebElement EditBtn;
    
  //Rule-2:Create a constructor to initilise these elements
    
  	public CoinsPage(WebDriver driver)
  	{
  		PageFactory.initElements(driver, this);
  	}
  	
  	
  	//Rule-3:Provide getters to access these variables
  	
	public WebElement getDashboardLnk() {
		return DashboardLnk;
	}

	public WebElement getManageDistributorLnk() {
		return ManageDistributorLnk;
	}

	public WebElement getManageOrderLnk() {
		return ManageOrderLnk;
	}

	public WebElement getCoinsLnk() {
		return CoinsLnk;
	}

	public WebElement getDashBoardCloseBtn() {
		return DashBoardCloseBtn;
	}

	public WebElement getNotificationsIcon() {
		return NotificationsIcon;
	}

	public WebElement getLogoutBtn() {
		return LogoutBtn;
	}

	public WebElement getGoldBtn() {
		return GoldBtn;
	}

	public WebElement getSilverBtn() {
		return SilverBtn;
	}

	public WebElement getMetalTypeDrpDwn() {
		return MetalTypeDrpDwn;
	}

	public WebElement getCoinWeightEdt() {
		return CoinWeightEdt;
	}

	public WebElement getMakingChargeEdt() {
		return MakingChargeEdt;
	}

	public WebElement getSaveChargeBtn() {
		return SaveChargeBtn;
	}

	public WebElement getEditBtn() {
		return EditBtn;
	}
    
	//Business Library
	
	
	
  	
}
