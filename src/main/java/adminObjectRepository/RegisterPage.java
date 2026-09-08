package adminObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//input[@placeholder='Business Name']")private WebElement BusinessNameEdt;
    
    @FindBy(xpath="//input[@formcontrolname='personName']")private WebElement PersonFullNameEdt;
    
    @FindBy(xpath="//input[@placeholder='Email Address']")private WebElement EmailEdt;
    
    @FindBy(xpath="//input[@placeholder='Phone Number']")private WebElement PhoneNumberEdt;
    
    @FindBy(xpath="//input[@formcontrolname='establishedDate']")private WebElement EstablishedDateEdt;
    
    @FindBy(xpath="//input[@placeholder='Pincode']")private WebElement PincodeEdt;
    
    @FindBy(xpath="//span[.='Bharat Nagar Colony']/preceding-sibling::input[@type='radio']")private WebElement RadioBtn;
    
    @FindBy(xpath="//textarea[@formcontrolname='businessAddress']")private WebElement BusinessAddressEdt;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public RegisterPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getBusinessNameEdt() {
		return BusinessNameEdt;
	}


	public WebElement getPersonFullNameEdt() {
		return PersonFullNameEdt;
	}


	public WebElement getEmailEdt() {
		return EmailEdt;
	}


	public WebElement getPhoneNumberEdt() {
		return PhoneNumberEdt;
	}


	public WebElement getEstablishedDateEdt() {
		return EstablishedDateEdt;
	}


	public WebElement getPincodeEdt() {
		return PincodeEdt;
	}


	public WebElement getRadioBtn() {
		return RadioBtn;
	}


	public WebElement getBusinessAddressEdt() {
		return BusinessAddressEdt;
	}
	
	// Business Library
	
	public void registeringToGoldbharathApplication(String BusinessName, String PersonFullName, String Email, String PhoneNumber, String Date, String Pincode, String Address) throws Exception
	{
		BusinessNameEdt.sendKeys(BusinessName);
		Thread.sleep(2000);
		PersonFullNameEdt.sendKeys(PersonFullName);
		Thread.sleep(2000);
		EmailEdt.sendKeys(Email);
		Thread.sleep(2000);
		PhoneNumberEdt.sendKeys(PhoneNumber);
		Thread.sleep(2000);
		EstablishedDateEdt.sendKeys(Date);
		Thread.sleep(2000);
		PincodeEdt.sendKeys(Pincode);
		Thread.sleep(2000);
		RadioBtn.click();
		Thread.sleep(2000);
		BusinessAddressEdt.sendKeys(Address);
		Thread.sleep(2000);
	}
	
	
}

