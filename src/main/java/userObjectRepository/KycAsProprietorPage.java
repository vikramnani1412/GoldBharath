package userObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import genericUtilities.WebDriverUtility;

public class KycAsProprietorPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//select[@class='my-select']")private WebElement TypeDrpDwn;
    
    @FindBy(xpath="//option[@value='Proprietor']")private WebElement SelectProprietor;
    
    @FindBy(xpath="//label[.='Proprietor PAN']/preceding-sibling::div/input[@placeholder='Enter Name']")private WebElement ProprietorNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter PAN']")private WebElement PanNumberEdt;
    
    @FindBy(xpath="//label[.='GST Number']/preceding-sibling::div/input[@type='file']")private WebElement UploadPanImageField;
    
    @FindBy(xpath="//input[@placeholder='Enter GST Number']")private WebElement GstNumberEdt;
    
    @FindBy(xpath="//label[.='Upload GST Number']/following-sibling::div/input[@type='file']")private WebElement UploadGstImageField;
    
    @FindBy(xpath="//input[@placeholder='Enter Bank Name']")private WebElement BankNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter A/C Number']")private WebElement AcNumberEdt;
    
    @FindBy(xpath="//input[@placeholder='Enter IFSC Code']")private WebElement IfscCodeEdt;
    
    @FindBy(xpath="//label[.='Account Holder Name']/following-sibling::div/input[@placeholder='Enter Name']")private WebElement AccountHolderNameEdt;
    
    @FindBy(xpath="//button[.='Submit']")private WebElement SubmitBtn;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public KycAsProprietorPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	

	public WebElement getTypeDrpDwn() {
		return TypeDrpDwn;
	}


	public WebElement getSelectProprietor() {
		return SelectProprietor;
	}


	public WebElement getProprietorNameEdt() {
		return ProprietorNameEdt;
	}


	public WebElement getPanNumberEdt() {
		return PanNumberEdt;
	}


	public WebElement getUploadPanImageField() {
		return UploadPanImageField;
	}


	public WebElement getGstNumberEdt() {
		return GstNumberEdt;
	}


	public WebElement getUploadGstImageField() {
		return UploadGstImageField;
	}


	public WebElement getBankNameEdt() {
		return BankNameEdt;
	}


	public WebElement getAcNumberEdt() {
		return AcNumberEdt;
	}


	public WebElement getIfscCodeEdt() {
		return IfscCodeEdt;
	}


	public WebElement getAccountHolderNameEdt() {
		return AccountHolderNameEdt;
	}


	public WebElement getSubmitBtn() {
		return SubmitBtn;
	}

	// Business Library
	
	public void proprietorKyc(WebDriver driver, String Name, String PanNumber, String GstNumber, String BankName, String AccountNo, String IfscCode, String AccountHolderName) throws Exception
	{
		WebDriverUtility wUtil = new WebDriverUtility();
		
		String PanimagePath = System.getProperty("user.dir")+ "/src/test/resources/assets/PAN_Card.jpeg";
		String GstimagePath = System.getProperty("user.dir")+ "/src/test/resources/assets/PAN_Card.jpeg";
		
		Thread.sleep(2000);
		TypeDrpDwn.click();
		Thread.sleep(2000);
		SelectProprietor.click();
		Thread.sleep(2000);
		ProprietorNameEdt.sendKeys(Name);
		Thread.sleep(2000);
		PanNumberEdt.sendKeys(PanNumber);
		Thread.sleep(2000);
		UploadPanImageField.sendKeys(PanimagePath);
		Thread.sleep(2000);
		GstNumberEdt.sendKeys(GstNumber);
		Thread.sleep(2000);
		UploadGstImageField.sendKeys(GstimagePath);
		Thread.sleep(2000);
		BankNameEdt.sendKeys(BankName);
		Thread.sleep(2000);
		AcNumberEdt.sendKeys(AccountNo);
		Thread.sleep(2000);
		IfscCodeEdt.sendKeys(IfscCode);
		Thread.sleep(2000);
		AccountHolderNameEdt.sendKeys(AccountHolderName);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});",SubmitBtn);
		// Wait until clickable
		wUtil.waitForElementToBeClickable(driver, SubmitBtn);
		Thread.sleep(2000);
		SubmitBtn.click();
		Thread.sleep(2000);
	}
	
	
}
