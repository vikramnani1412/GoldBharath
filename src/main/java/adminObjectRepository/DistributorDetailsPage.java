package adminObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DistributorDetailsPage {

	// Rule-1:-Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//button[.='Reject']")private WebElement RejectBtn;
    
    @FindBy(xpath="//button[.='Accept']")private WebElement AcceptBtn;
    
    @FindBy(xpath="//strong[.='Proprietor PAN:']/../preceding-sibling::div//button[.='Download']")private WebElement PanDownloadBtn;
    
    @FindBy(xpath="//strong[.='Proprietor GST:']/../preceding-sibling::div//button[.='Download']")private WebElement GstDownloadBtn;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public DistributorDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getRejectBtn() {
		return RejectBtn;
	}


	public WebElement getAcceptBtn() {
		return AcceptBtn;
	}


	public WebElement getPanDownloadBtn() {
		return PanDownloadBtn;
	}


	public WebElement getGstDownloadBtn() {
		return GstDownloadBtn;
	}
	
	// Business Library
	public void acceptingDistributorKycDetails() throws Exception
	{
		Thread.sleep(2000);
		AcceptBtn.click();
	}
	
}
