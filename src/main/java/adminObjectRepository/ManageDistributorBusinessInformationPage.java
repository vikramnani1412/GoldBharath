package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageDistributorBusinessInformationPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(name="business_name")private WebElement BusinessNameEdt;
    
    @FindBy(name="account_holder_name")private WebElement AccountHolderNameEdt;
    
    @FindBy(name="ifsc_code")private WebElement IfscCodeEdt;
    
    @FindBy(name="account_number")private WebElement AccountNumberEdt;
    
    @FindBy(name="bank_name")private WebElement BankNameEdt;
    
    @FindBy(xpath="//label[.='Address Line 1']/following-sibling::input[@name='address_line_1']")private WebElement AddressLine1Edt;
    
    @FindBy(xpath="//label[.='Address Line 2']/following-sibling::input[@name='address_line_1']")private WebElement AddressLine2Edt;
    
    @FindBy(xpath="//input[@placeholder='Mobile Number']")private WebElement MobileNumberEdt;
    
    @FindBy(name="email")private WebElement EmailEdt;
    
    @FindBy(xpath="//input[@placeholder='State']")private WebElement StateEdt;
    
    @FindBy(xpath="//input[@placeholder='City']")private WebElement CityEdt;
    
    @FindBy(xpath="//input[@placeholder='Pin Code']")private WebElement PinCodeEdt;
    
    // Business KYC Details
    
    
    @FindBy(name="proprietor_name")private WebElement ProprietorNameEdt;
    
    @FindBy(name="circle")private WebElement CircleEdt;
    
    @FindBy(name="proprietor_pan")private WebElement ProprietorPanNumberEdt;
    
    @FindBy(xpath="//label[.='Proprietor PAN Image']/following-sibling::input[@type='file']")private WebElement ProprietorPanImageChoosefileBtn;
    
    @FindBy(name="proprietor_gst")private WebElement ProprietorGstNumberEdt;
    
    @FindBy(xpath="//label[.='Proprietor GST Image']/following-sibling::input[@type='file']")private WebElement ProprietorGstImageChoosefileBtn;
    
    @FindBy(xpath="//input[@placeholder='business type']")private WebElement CoinsPendingStatusLnk;
    
    @FindBy(name="silver_max_limit")private WebElement SilverMaxLimitInGramsEdt;
    
    @FindBy(xpath="//input[@name='max_limit']")private WebElement GoldMaxLimitInGramsEdt;
    
    @FindBy(xpath="//span[.='Submit']")private WebElement SubmitBtn;
  
    
    
	//Rule-3:Create a constructor to initilise these elements
    
	public ManageDistributorBusinessInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-4:Provide getters to access these variables
	

	public WebElement getBusinessNameEdt() {
		return BusinessNameEdt;
	}



	public WebElement getAccountHolderNameEdt() {
		return AccountHolderNameEdt;
	}



	public WebElement getIfscCodeEdt() {
		return IfscCodeEdt;
	}



	public WebElement getAccountNumberEdt() {
		return AccountNumberEdt;
	}



	public WebElement getBankNameEdt() {
		return BankNameEdt;
	}



	public WebElement getAddressLine1Edt() {
		return AddressLine1Edt;
	}



	public WebElement getAddressLine2Edt() {
		return AddressLine2Edt;
	}



	public WebElement getMobileNumberEdt() {
		return MobileNumberEdt;
	}



	public WebElement getEmailEdt() {
		return EmailEdt;
	}



	public WebElement getStateEdt() {
		return StateEdt;
	}



	public WebElement getCityEdt() {
		return CityEdt;
	}



	public WebElement getPinCodeEdt() {
		return PinCodeEdt;
	}



	public WebElement getProprietorNameEdt() {
		return ProprietorNameEdt;
	}



	public WebElement getCircleEdt() {
		return CircleEdt;
	}



	public WebElement getProprietorPanNumberEdt() {
		return ProprietorPanNumberEdt;
	}



	public WebElement getProprietorPanImageChoosefileBtn() {
		return ProprietorPanImageChoosefileBtn;
	}



	public WebElement getProprietorGstNumberEdt() {
		return ProprietorGstNumberEdt;
	}



	public WebElement getProprietorGstImageChoosefileBtn() {
		return ProprietorGstImageChoosefileBtn;
	}



	public WebElement getCoinsPendingStatusLnk() {
		return CoinsPendingStatusLnk;
	}



	public WebElement getSilverMaxLimitInGramsEdt() {
		return SilverMaxLimitInGramsEdt;
	}



	public WebElement getGoldMaxLimitInGramsEdt() {
		return GoldMaxLimitInGramsEdt;
	}



	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}

	// Business Library
	
}
