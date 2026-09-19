package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KycAsPvtLtdPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//select[@class='my-select']")private WebElement TypeDrpDwn;
    
    @FindBy(xpath="//option[@value='Pvt LTD']")private WebElement SelectPvtLtd;
    
    @FindBy(xpath="//input[@formcontrolname='director_nameone']")private WebElement DirectorNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='director_mobile']")private WebElement DirectorMobileNumberEdt;
    
    @FindBy(xpath="//input[@formcontrolname='director_aadharone']")private WebElement DirectorAadharNumberEdt;
    
    @FindBy(xpath="//label[.='Upload Aadhar Back Side(First Director)']/preceding-sibling::div/input[@type='file']")private WebElement UploadAadharFrontSideImage;
    
    @FindBy(xpath="//label[normalize-space()='Compony PAN Number(First Director)']/preceding-sibling::div[1]/input[@type='file']")private WebElement UploadAadharBackSideImage;
    
    @FindBy(xpath="//input[@placeholder='Enter PAN Number']")private WebElement PanNumberEdt;
    
    @FindBy(xpath="//label[normalize-space()='Upload PAN Card(First Director)']/following-sibling::div[1]//input[@type='file']")private WebElement UploadPanImage;
    
    @FindBy(xpath="//input[@formcontrolname='director_nametwo']")private WebElement SecondDirectorNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter GST Number']")private WebElement EnterGSTNumberEdt;
    
    @FindBy(xpath="//label[normalize-space()='Upload GST']/following-sibling::div[1]/input[@type='file']")private WebElement UploadGstImage;
    
    @FindBy(xpath="//input[@placeholder='Enter CIN Number']")private WebElement CompanyCinEdt;
    
    @FindBy(xpath="//label[normalize-space()='Upload CIN']/following-sibling::div[1]//input[@type='file']")private WebElement UploadCINimage;
    
    @FindBy(xpath="//label[normalize-space()='Upload Your Image']/following-sibling::div[1]//input[@type='file']")private WebElement UploadYourImage;
    
    @FindBy(xpath="//input[@placeholder='Enter Bank Name']")private WebElement EnterBankNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter A/C Number']")private WebElement EnterAcNumberEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter IFSC Code']")private WebElement EnterIFSCCodeEdt;
    
    @FindBy(xpath="//label[.='Account Holder Name']/following-sibling::div/input[@placeholder='Enter Name']")private WebElement AccountHolderNameEdt;
    
    @FindBy(xpath="//button[.='Submit']")private WebElement SubmitBtn;
    
	//Rule-2:Create a constructor to initilise these elements
    
	public KycAsPvtLtdPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	
	public WebElement getTypeDrpDwn() {
		return TypeDrpDwn;
	}

	public WebElement getSelectPvtLtd() {
		return SelectPvtLtd;
	}

	public WebElement getDirectorNameEdt() {
		return DirectorNameEdt;
	}

	public WebElement getDirectorMobileNumberEdt() {
		return DirectorMobileNumberEdt;
	}

	public WebElement getDirectorAadharNumberEdt() {
		return DirectorAadharNumberEdt;
	}

	public WebElement getUploadAadharFrontSideImage() {
		return UploadAadharFrontSideImage;
	}

	public WebElement getUploadAadharBackSideImage() {
		return UploadAadharBackSideImage;
	}

	public WebElement getPanNumberEdt() {
		return PanNumberEdt;
	}

	public WebElement getUploadPanImage() {
		return UploadPanImage;
	}

	public WebElement getSecondDirectorNameEdt() {
		return SecondDirectorNameEdt;
	}

	public WebElement getEnterGSTNumberEdt() {
		return EnterGSTNumberEdt;
	}

	public WebElement getUploadGstImage() {
		return UploadGstImage;
	}

	public WebElement getCompanyCinEdt() {
		return CompanyCinEdt;
	}

	public WebElement getUploadCINimage() {
		return UploadCINimage;
	}

	public WebElement getUploadYourImage() {
		return UploadYourImage;
	}

	public WebElement getEnterBankNameEdt() {
		return EnterBankNameEdt;
	}

	public WebElement getEnterAcNumberEdt() {
		return EnterAcNumberEdt;
	}

	public WebElement getEnterIFSCCodeEdt() {
		return EnterIFSCCodeEdt;
	}

	public WebElement getAccountHolderNameEdt() {
		return AccountHolderNameEdt;
	}

	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}

	// Business Library
	
	public void PvtLtdKyc(String Name, String MobileNo, String AadharNo, String PanNo, String SecondDirectorName, String GstNo, String CIN, String BankName, String AccountNo, String IfscCode, String AcHolderName) throws Exception
	{
		String PanimagePath = System.getProperty("user.dir")+ "/src/test/resources/assets/PAN_Card.jpeg";
		String AadharImagePath = System.getProperty("user.dir")+ "/src/test/resources/assets/Aadhaar_Card.jpeg";
		
		
		Thread.sleep(2000);
		TypeDrpDwn.click();
		Thread.sleep(2000);
		SelectPvtLtd.click();
		Thread.sleep(2000);
		DirectorNameEdt.sendKeys(Name);
		Thread.sleep(2000);
		DirectorMobileNumberEdt.sendKeys(MobileNo);
		Thread.sleep(2000);
		DirectorAadharNumberEdt.sendKeys(AadharNo);
		Thread.sleep(2000);
		UploadAadharFrontSideImage.sendKeys(AadharImagePath);
		Thread.sleep(2000);
		UploadAadharBackSideImage.sendKeys(AadharImagePath);
		Thread.sleep(2000);
		PanNumberEdt.sendKeys(PanNo);
		Thread.sleep(2000);
		UploadPanImage.sendKeys(PanimagePath);
		Thread.sleep(2000);
		SecondDirectorNameEdt.sendKeys(SecondDirectorName);
		Thread.sleep(2000);
		EnterGSTNumberEdt.sendKeys(GstNo);
		Thread.sleep(2000);
		UploadGstImage.sendKeys(PanimagePath);
		Thread.sleep(2000);
		CompanyCinEdt.sendKeys(CIN);
		Thread.sleep(2000);
		UploadCINimage.sendKeys(PanimagePath);
		Thread.sleep(2000);
		UploadYourImage.sendKeys(AadharImagePath);
		Thread.sleep(2000);
		EnterBankNameEdt.sendKeys(BankName);
		Thread.sleep(2000);
		EnterAcNumberEdt.sendKeys(AccountNo);
		Thread.sleep(2000);
		EnterIFSCCodeEdt.sendKeys(IfscCode);
		Thread.sleep(2000);
	    AccountHolderNameEdt.sendKeys(AcHolderName);
	    Thread.sleep(2000);
	    SubmitBtn.click();
	}
}
