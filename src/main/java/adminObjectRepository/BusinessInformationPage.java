package adminObjectRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusinessInformationPage {

	
	///////////////////////////////
	//      Business   Information
	///////////////////////////////
	
	// Rule-1:-Finding WebElements Using @FindBy Annotations

	@FindBy(xpath="//input[@formcontrolname='business_name']")private WebElement BusinessNameEdt;
	
    @FindBy(xpath="//input[@formcontrolname='account_holder_name']")private WebElement AccountHolderNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='ifsc_code']")private WebElement IfscNumberEdt;
    
    @FindBy(xpath="//input[@formcontrolname='account_number']")private WebElement AccountNumberEdt;
    
    @FindBy(xpath="//input[@formcontrolname='bank_name']")private WebElement BankNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='address_line_1']")private WebElement AddressLine1Edt;
    
    @FindBy(xpath="//input[@placeholder='Address Line 2']")private WebElement AddressLine2Edt;
    
    @FindBy(xpath="//input[@formcontrolname='register_phone_number']")private WebElement RegisteredMobileNumberEdt;
    
    @FindBy(xpath="//input[@formcontrolname='email']")private WebElement EmailEdt;
    
    @FindBy(xpath="//input[@formcontrolname='state']")private WebElement StateEdt;
	
    @FindBy(xpath="//input[@formcontrolname='city']")private WebElement CityEdt;
    
    @FindBy(xpath="//input[@formcontrolname='pin_code']")private WebElement PinCodeEdt;
    
    
    ///////////////////////////////
    //      Business   KYC
    ///////////////////////////////
    
    
    @FindBy(xpath="//input[@formcontrolname='proprietor_name']")private WebElement ProprietorNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='circle']")private WebElement CircleEdt;
    
    @FindBy(xpath="//input[@formcontrolname='proprietor_pan']")private WebElement ProprietorPanEdt;
    
    @FindBy(xpath="//label[.='Proprietor PAN Image']/following-sibling::input[@type='file']")private WebElement PanChooseFileBtn;
    
    @FindBy(xpath="//input[@formcontrolname='proprietor_gst']")private WebElement ProprietorGstEdt;
    
    @FindBy(xpath="//label[.='Proprietor GST Image']/following-sibling::input[@type='file']")private WebElement GstChooseFileBtn;
    
    @FindBy(xpath="//label[.='Proprietor GST Image']/following-sibling::input[@type='file']")private WebElement BusinessTypeEdt;
    
    ///////////////////////////////
    //      Silver Details
    ///////////////////////////////
 	
    @FindBy(xpath="//input[@formcontrolname='silver_max_limit']")private WebElement SilverMaxLimitEdt;
    
    
    ///////////////////////////////
    //      Gold   Details
    ///////////////////////////////
    
    
    @FindBy(xpath="//label[.='Gold Max Limit (In Grams)']/following-sibling::input[@formcontrolname='max_limit']")private WebElement GoldMaxLimitEdt;
    
    @FindBy(xpath="//button[@type='submit']")private WebElement SubmitBtn;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public BusinessInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	

	public WebElement getBusinessNameEdt() {
		return BusinessNameEdt;
	}


	public WebElement getAccountHolderNameEdt() {
		return AccountHolderNameEdt;
	}


	public WebElement getIfscNumberEdt() {
		return IfscNumberEdt;
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


	public WebElement getRegisteredMobileNumberEdt() {
		return RegisteredMobileNumberEdt;
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


	public WebElement getProprietorPanEdt() {
		return ProprietorPanEdt;
	}


	public WebElement getPanChooseFileBtn() {
		return PanChooseFileBtn;
	}


	public WebElement getProprietorGstEdt() {
		return ProprietorGstEdt;
	}


	public WebElement getGstChooseFileBtn() {
		return GstChooseFileBtn;
	}


	public WebElement getBusinessTypeEdt() {
		return BusinessTypeEdt;
	}


	public WebElement getSilverMaxLimitEdt() {
		return SilverMaxLimitEdt;
	}


	public WebElement getGoldMaxLimitEdt() {
		return GoldMaxLimitEdt;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}


	// Business Library
	
	// Corrected givingSilverAndGoldLimit — fixes:
    
	
	public void editingAndGivingSilverAndGoldLimit(String SilverLimit, String GoldLimit) throws Exception
	{
//	    BusinessNameEdt.clear();
//	    Thread.sleep(2000);
//	    BusinessNameEdt.sendKeys("Ai Solutions");
//	    Thread.sleep(2000);

	    AccountHolderNameEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    AccountHolderNameEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    AccountHolderNameEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    IfscNumberEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    IfscNumberEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    IfscNumberEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    AccountNumberEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    AccountNumberEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    AccountNumberEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    BankNameEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    BankNameEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    BankNameEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    AddressLine1Edt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    AddressLine1Edt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    AddressLine1Edt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    AddressLine2Edt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    AddressLine2Edt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    AddressLine2Edt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    RegisteredMobileNumberEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    RegisteredMobileNumberEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    RegisteredMobileNumberEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    EmailEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    EmailEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    EmailEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    StateEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    StateEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    StateEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    CityEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    CityEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    CityEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    PinCodeEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    PinCodeEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    PinCodeEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    ProprietorNameEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    ProprietorNameEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    ProprietorNameEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    CircleEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    CircleEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    CircleEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

	    ProprietorPanEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    ProprietorPanEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    ProprietorPanEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);
	    
	    ProprietorGstEdt.sendKeys(Keys.CONTROL, "a");
	    Thread.sleep(2000);
	    ProprietorGstEdt.sendKeys(Keys.CONTROL, "x");
	    Thread.sleep(2000);
	    ProprietorGstEdt.sendKeys(Keys.CONTROL, "v");
	    Thread.sleep(2000);

//	    BusinessTypeEdt.sendKeys(Keys.CONTROL, "a");
//	    Thread.sleep(2000);
//	    BusinessTypeEdt.sendKeys(Keys.CONTROL, "x");
//	    Thread.sleep(2000);
//	    BusinessTypeEdt.sendKeys(Keys.CONTROL, "v");
//	    Thread.sleep(2000);

	    // Silver Limit
	    Thread.sleep(2000);
	    SilverMaxLimitEdt.clear();
	    Thread.sleep(2000);
	    SilverMaxLimitEdt.sendKeys(SilverLimit);

	    // Gold Limit
	    Thread.sleep(2000);
	    GoldMaxLimitEdt.clear();
	    Thread.sleep(2000);
	    GoldMaxLimitEdt.sendKeys(GoldLimit);

	    Thread.sleep(2000);
	    SubmitBtn.click();
	}
	
	
	
	
	
	
	
//	public void givingSilverAndGoldLimit(String SilverLimit, String GoldLimit) throws Exception
//	{
//		Thread.sleep(2000);
//		BusinessNameEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		BusinessNameEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		AccountHolderNameEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		AccountHolderNameEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		IfscNumberEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		IfscNumberEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		AccountNumberEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		AccountNumberEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		BankNameEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		BankNameEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		AddressLine1Edt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		AddressLine1Edt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		AddressLine2Edt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		AddressLine2Edt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		RegisteredMobileNumberEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		RegisteredMobileNumberEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		EmailEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		EmailEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		StateEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		StateEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		CityEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		CityEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		PinCodeEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		PinCodeEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		ProprietorNameEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		ProprietorNameEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		CircleEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		CircleEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		ProprietorPanEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		ProprietorNameEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		
//		//Upload
//		
//		Thread.sleep(2000);
//		ProprietorGstEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		ProprietorGstEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		
//		//Upload
//		
//		Thread.sleep(2000);
//		BusinessTypeEdt.sendKeys(Keys.CONTROL, "x");
//		Thread.sleep(2000);
//		BusinessTypeEdt.sendKeys(Keys.CONTROL, "v");
//		Thread.sleep(2000);
//		
//		// Silver Limit
//		Thread.sleep(2000);
//		SilverMaxLimitEdt.sendKeys(SilverLimit);
//		
//		// Gold Limit
//		Thread.sleep(2000);
//		GoldMaxLimitEdt.sendKeys(GoldLimit);
//		
//		Thread.sleep(2000);
////		SubmitBtn.click();
//	}
	
}
