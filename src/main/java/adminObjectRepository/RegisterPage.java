package adminObjectRepository;

import java.util.List;

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
    
    @FindBy(xpath="//button[@class='register-btn']")private WebElement RegisterBtn;
    
    @FindBy(xpath="//textarea[@formcontrolname='businessAddress']")private WebElement LoginBtn;
    
    
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


	public WebElement getRegisterBtn() {
		return RegisterBtn;
	}


	public WebElement getLoginBtn() {
		return LoginBtn;
	}


	public WebElement getBusinessAddressEdt() {
		return BusinessAddressEdt;
	}
	
	// Business Library
	
//	public void registeringToGoldbharathApplication(String BusinessName, String PersonFullName, String Email, String PhoneNumber, String Date, String Pincode, String Address) throws Exception
//	{
//		BusinessNameEdt.sendKeys(BusinessName);
//		Thread.sleep(2000);
//		PersonFullNameEdt.sendKeys(PersonFullName);
//		Thread.sleep(2000);
//		EmailEdt.sendKeys(Email);
//		Thread.sleep(2000);
//		PhoneNumberEdt.sendKeys(PhoneNumber);
//		Thread.sleep(2000);
//		EstablishedDateEdt.sendKeys(Date);
//		Thread.sleep(2000);
//		PincodeEdt.sendKeys(Pincode);
//		Thread.sleep(2000);
//		RadioBtn.click();
//		Thread.sleep(2000);
//		BusinessAddressEdt.sendKeys(Address);
//		Thread.sleep(2000);
//		RegisterBtn.click();
//	}
	
	public String registeringToGoldbharathApplication(
	        WebDriver driver,
	        String BusinessName,
	        String PersonFullName,
	        String Email,
	        List<String> phoneNumbers,
	        String Date,
	        String Pincode,
	        String Address) throws Exception {

		Thread.sleep(2000);
	    BusinessNameEdt.sendKeys(BusinessName);
	    Thread.sleep(2000);
	    PersonFullNameEdt.sendKeys(PersonFullName);
	    Thread.sleep(2000);
	    EmailEdt.sendKeys(Email);
	    Thread.sleep(2000);
	    EstablishedDateEdt.sendKeys(Date);
	    Thread.sleep(2000);
	    PincodeEdt.sendKeys(Pincode);
	    Thread.sleep(2000);
	    RadioBtn.click();
	    Thread.sleep(2000);
	    BusinessAddressEdt.sendKeys(Address);
	    Thread.sleep(2000);

	    for (String phoneNumber : phoneNumbers) {

	        System.out.println("Trying Mobile Number: " + phoneNumber);
	        Thread.sleep(2000);	        
	        PhoneNumberEdt.clear();
	        Thread.sleep(2000);
	        PhoneNumberEdt.sendKeys(phoneNumber);
	        Thread.sleep(2000);
	        RegisterBtn.click();
	        Thread.sleep(2000);
	        List<WebElement> alreadyRegisteredMsg = driver.findElements(By.xpath("//p[contains(.,'number already exists')]"));
	        Thread.sleep(2000);
	        if (!alreadyRegisteredMsg.isEmpty()
	                && alreadyRegisteredMsg.get(0).isDisplayed()) {

	            System.out.println(
	                    "Already Registered: " + phoneNumber
	            );

	            // Try next mobile number
	            continue;
	        }

	        // Unique mobile number found
	        System.out.println(
	                "Registration successful with: " + phoneNumber
	        );

	        return phoneNumber;
	    }


	    // All numbers are already registered
	    throw new Exception(
	            "All mobile numbers are already registered."
	    );
	}
	
	
	
	
}

