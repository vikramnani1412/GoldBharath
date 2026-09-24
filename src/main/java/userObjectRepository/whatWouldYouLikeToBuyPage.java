package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class whatWouldYouLikeToBuyPage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="(//h5[.='What would you like to buy?']/following-sibling::button[@aria-label='Close'])[1]")private WebElement CloseBtn;
    
    @FindBy(xpath="//h6[.='Gold Coins']")private WebElement GoldCoinsEle;
    
    @FindBy(xpath="//h6[.='Gold Bars']")private WebElement GoldBarsEle;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public whatWouldYouLikeToBuyPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//Rule-3:Provide getters to access these variables
	

	public WebElement getCloseBtn() {
		return CloseBtn;
	}


	public WebElement getGoldCoinsEle() {
		return GoldCoinsEle;
	}


	public WebElement getGoldBarsEle() {
		return GoldBarsEle;
	}
	
	
}
