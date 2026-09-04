package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageDistributorPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//span[.='Dashboard']")private WebElement DashboardLnk;
    
    @FindBy(xpath="//span[.='Manage Distributor']")private WebElement ManageDistributorLnk;
    
    @FindBy(xpath="//span[.='Manage Order']")private WebElement ManageOrderLnk;
    
    @FindBy(xpath="//span[.='Coins']")private WebElement CoinsLnk;
    
    @FindBy(xpath="//i[@class='fa-solid fa-arrow-left icon-vertical ng-star-inserted']")private WebElement DashBoardCloseBtn;
    
    @FindBy(xpath="//i[@class='fa-solid fa-bell user-icon']")private WebElement NotificationsIcon;
    
    @FindBy(xpath="//button[@class='logout']")private WebElement LogoutBtn;
    
    @FindBy(xpath="//input[@placeholder='Search by Name, Mobile Number, State....']")private WebElement SearchEdt;
    
    @FindBy(xpath="//button[.='Export to Excel']")private WebElement ExportToExcelBtn;
    
    // Mobile number has to make dynamic
    @FindBy(xpath="//td[.='8712631764']/following-sibling::td//input[@role='switch']")private WebElement ToggleBtnBasedOnMobileNumber;
    
    // Mobile number has to make dynamic
    @FindBy(xpath="//td[.='8712631764']/following-sibling::td/button[@routerlink='/Edit-Manage-Distributor']")private WebElement EditBtnBasedOnMobileNumber;
    
    @FindBy(xpath="//a[text()=' Previous ']")private WebElement PreviousPageLnk;
    
    @FindBy(xpath="//a[text()=' Next ']")private WebElement NextPageLnk;
    
    // Page number has to make dynamic
    @FindBy(xpath="//span[.='1']")private WebElement BasedOnNumberPageShouldDisplay;
    
    
	//Rule-3:Create a constructor to initilise these elements
    
	public ManageDistributorPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-4:Provide getters to access these variables
	
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


	public WebElement getSearchEdt() {
		return SearchEdt;
	}


	public WebElement getExportToExcelBtn() {
		return ExportToExcelBtn;
	}


	public WebElement getToggleBtnBasedOnMobileNumber() {
		return ToggleBtnBasedOnMobileNumber;
	}


	public WebElement getEditBtnBasedOnMobileNumber() {
		return EditBtnBasedOnMobileNumber;
	}


	public WebElement getPreviousPageLnk() {
		return PreviousPageLnk;
	}


	public WebElement getNextPageLnk() {
		return NextPageLnk;
	}


	public WebElement getBasedOnNumberPageShouldDisplay() {
		return BasedOnNumberPageShouldDisplay;
	}

	// Business Library

	public void checkToodleIsSelected()
	{
		WebElement toggle =  ToggleBtnBasedOnMobileNumber;

			if (toggle.isSelected()) {
			    System.out.println("8712631764 Toodle is ENABLED");
			} else {
			    System.out.println("8712631764 Toodle is DISABLED");
			}
	}
	
	public void enableOnlyIfItIsDisable()
	{
		WebElement toggle =  ToggleBtnBasedOnMobileNumber;
		
		if (!toggle.isSelected()) {
		    toggle.click();
		    System.out.println("8712631764 was disabled. Now enabled.");
		} else {
		    System.out.println("8712631764 is already enabled.");
		}
	}
	
	public void disableOnlyIfItIsEnable()
	{
		WebElement toggle =  ToggleBtnBasedOnMobileNumber;
		
		if (toggle.isSelected()) {
		    toggle.click();
		    System.out.println("8712631764 was enabled. Now disabled.");
		} else {
		    System.out.println("8712631764 is already disabled.");
		}
	}
	
}
