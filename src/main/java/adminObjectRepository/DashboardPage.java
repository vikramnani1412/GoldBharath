package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//span[.='Dashboard']")private WebElement DashboardLnk;
    
    @FindBy(xpath="//span[.='Manage Distributor']")private WebElement ManageDistributorLnk;
    
    @FindBy(xpath="//span[.='Manage Order']")private WebElement ManageOrderLnk;
    
    @FindBy(xpath="//span[.='Coins']")private WebElement CoinsLnk;
    
    @FindBy(xpath="//i[@class='fa-solid fa-arrow-left icon-vertical ng-star-inserted']")private WebElement DashBoardCloseBtn;
    
    @FindBy(xpath="//i[@class='fa-solid fa-bell user-icon']")private WebElement NotificationsIcon;
    
    @FindBy(xpath="//button[@class='logout']")private WebElement LogoutBtn;
    
    @FindBy(xpath="//h5[.='Manage Distributor']/following-sibling::div//p[.=' All Distributor ']/following-sibling::div//p[.=' Manage']")private WebElement AllDistributorLnk;
    
    @FindBy(xpath="//h5[.='Manage Distributor']/following-sibling::div//p[.=' KYC Documents Pending ']/following-sibling::div//p[.=' Manage']")private WebElement KYCDocumentsPendingLnk;
    
    @FindBy(xpath="//h5[.='Manage Distributor']/following-sibling::div//p[.=' Accepted KYC ']/following-sibling::div//p[.=' Manage']")private WebElement AcceptedKYCLnk;
    
    @FindBy(xpath="//h5[.='Manage Distributor']/following-sibling::div//p[.=' KYC Verification Pending ']/following-sibling::div//p[.=' Manage']")private WebElement KYCVerificationPendingLnk;
    
    @FindBy(xpath="//h5[.='Manage Distributor']/following-sibling::div//p[.=' Rejected KYC ']/following-sibling::div//p[.=' Manage']")private WebElement RejectedKYCLnk;
    
    @FindBy(xpath="//h5[.='Distributor Manage Order']/following-sibling::div//p[.=' All Distributor Orders ']/following-sibling::div//p[.=' Manage']")private WebElement AllDistributorOrdersLnk;
    
    @FindBy(xpath="//h5[.='Distributor Manage Order']/following-sibling::div//p[.=' Pending Status ']/following-sibling::div//p[.=' Manage']")private WebElement PendingStatusLnk;
    
    @FindBy(xpath="//h5[.='Distributor Manage Order']/following-sibling::div//p[.=' Payment Accepted ']/following-sibling::div//p[.=' Manage']")private WebElement PaymentAcceptedLnk;
    
    @FindBy(xpath="(//h5[.='Distributor Manage Order']/../following-sibling::div//p[.=' Payment Rejected ']/following-sibling::div/p)[1]")private WebElement PaymentRejectedLnk;
    
    @FindBy(xpath="(//h5[.='Distributor Manage Order']/../following-sibling::div//p[.='Orders Rejected']/following-sibling::div/p)[1]")private WebElement OrdersRejectedLnk;
    
    @FindBy(xpath="//h5[.='Coin Orders']/following-sibling::div//p[.=' All Coins Orders ']/following-sibling::div/p[.=' Manage']")private WebElement CoinsAllCoinsOrdersLnk;
    
    @FindBy(xpath="//h5[.='Coin Orders']/following-sibling::div//p[.=' Pending Status ']/following-sibling::div/p[.=' Manage']")private WebElement CoinsPendingStatusLnk;
    
    @FindBy(xpath="//h5[.='Coin Orders']/following-sibling::div//p[.=' Payment Accepted ']/following-sibling::div/p[.=' Manage']")private WebElement CoinsPaymentAcceptedLnk;
    
    @FindBy(xpath="(//h5[.='Coin Orders']/../following-sibling::div//p[.=' Payment Rejected ']/following-sibling::div/p)[1]")private WebElement CoinsPaymentRejectedLnk;
    
    @FindBy(xpath="(//h5[.='Coin Orders']/../following-sibling::div//p[.='Orders Rejected']/following-sibling::div/p)[1]")private WebElement CoinsOrdersRejectedLnk;
  
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public DashboardPage(WebDriver driver)
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


	public WebElement getAllDistributorLnk() {
		return AllDistributorLnk;
	}

	public WebElement getKYCDocumentsPendingLnk() {
		return KYCDocumentsPendingLnk;
	}

	public WebElement getAcceptedKYCLnk() {
		return AcceptedKYCLnk;
	}

	public WebElement getKYCVerificationPendingLnk() {
		return KYCVerificationPendingLnk;
	}

	public WebElement getRejectedKYCLnk() {
		return RejectedKYCLnk;
	}

	public WebElement getAllDistributorOrdersLnk() {
		return AllDistributorOrdersLnk;
	}

	public WebElement getPendingStatusLnk() {
		return PendingStatusLnk;
	}

	public WebElement getPaymentAcceptedLnk() {
		return PaymentAcceptedLnk;
	}

	public WebElement getPaymentRejectedLnk() {
		return PaymentRejectedLnk;
	}

	public WebElement getOrdersRejectedLnk() {
		return OrdersRejectedLnk;
	}

	public WebElement getCoinsAllCoinsOrdersLnk() {
		return CoinsAllCoinsOrdersLnk;
	}

	public WebElement getCoinsPendingStatusLnk() {
		return CoinsPendingStatusLnk;
	}

	public WebElement getCoinsPaymentAcceptedLnk() {
		return CoinsPaymentAcceptedLnk;
	}

	public WebElement getCoinsOrdersRejectedLnk() {
		return CoinsOrdersRejectedLnk;
	}

	public WebElement getCoinsPaymentRejectedLnk() {
		return CoinsPaymentRejectedLnk;
	}
    
	
	
	
	//Business Libraries
	
}
