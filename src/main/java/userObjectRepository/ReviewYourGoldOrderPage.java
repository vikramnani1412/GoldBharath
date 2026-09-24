package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class ReviewYourGoldOrderPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//div[@class='review-container ng-star-inserted']")private WebElement AllDetailsInThisPageEle;
    
    @FindBy(xpath="//div[@class='summary-row grand']")private WebElement GrandTotalPriceEle;
    
    @FindBy(xpath="//button[.=' Proceed to Payment ']")private WebElement ProceedPaymentBtn;
   
    @FindBy(xpath="//p[contains(.,'Success!')]")private WebElement SuccessMsg;
    
	//Rule-2:Create a constructor to initilise these elements
    
	public ReviewYourGoldOrderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-3:Provide getters to access these variables

	public WebElement getAllDetailsInThisPageEle() {
		return AllDetailsInThisPageEle;
	}


	public WebElement getGrandTotalPriceEle() {
		return GrandTotalPriceEle;
	}


	public WebElement getProceedPaymentBtn() {
		return ProceedPaymentBtn;
	}
    
	
	public WebElement getSuccessMsg() {
		return SuccessMsg;
	}
	
	// Business Library

	public void takeAllDetailsAndClickProceedBtn(WebDriver driver) throws Exception
	{
		Thread.sleep(2000);
		String Details = AllDetailsInThisPageEle.getText();
		Thread.sleep(1000);
		ProceedPaymentBtn.click();

	    if(SuccessMsg.isDisplayed())
	    {
	    	System.out.println("User Buy Gold Successfully" +Details);
	    }
	}
}
