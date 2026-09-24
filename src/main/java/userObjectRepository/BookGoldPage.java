package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class BookGoldPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="//h5[.='Book Gold']/following-sibling::button[@class='btn-close']")private WebElement CloseBtn;
    
    @FindBy(xpath="//h5[.='Book Gold']/../following-sibling::div//h4[contains(.,'₹')]")private WebElement CurrentLivePriceEle;
    
    @FindBy(xpath="//h5[.='Book Gold']/../following-sibling::div//p[contains(.,'Price will be locked for')]")private WebElement PriceLockedTimeEle;
    
    @FindBy(xpath="//h5[.='Book Gold']/../following-sibling::div//select[@formcontrolname='quantity_purchased']")private WebElement QuantityDrpDwn;
    
    @FindBy(xpath="//h5[normalize-space()='Book Gold']/../..//input[@placeholder='Enter your amount']")private WebElement AmountEdt;
  
    @FindBy(xpath="//h5[normalize-space()='Book Gold']/../..//button[.='Book']")private WebElement BookBtn;  
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public BookGoldPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	//Rule-3:Provide getters to access these variables
	
	public void fetchingGoldLivePriceAndBookingGold(WebDriver driver) throws Exception
	{
	    WebDriverUtility wUtil = new WebDriverUtility();

	    Thread.sleep(2000);
	    String CurrentLockedPrice = CurrentLivePriceEle.getText();
	    System.out.println(CurrentLockedPrice);
	    Thread.sleep(2000);

	    wUtil.handleDropdownByValue(QuantityDrpDwn, "500");
	    Thread.sleep(2000);
	//  wUtil.handleDropdownByIndex(AmountEdt, 5);

	    // Clean the price text: strip currency symbols, commas, spaces
	    String cleanedPrice = CurrentLockedPrice.replaceAll("[^0-9.]", "");
	    double priceValue = Double.parseDouble(cleanedPrice);
	    double amountToSend = priceValue - 10;

	    // Convert back to a plain string for sendKeys (no decimals if the field expects a whole number)
	    String Amount = String.valueOf((long) amountToSend);

	    wUtil.scrollExactToParticularWebElement(driver, AmountEdt);
	    Thread.sleep(1000);
	    AmountEdt.sendKeys(Amount);
	    
	    Thread.sleep(2000);
//	    BookBtn.click();
	}
}
