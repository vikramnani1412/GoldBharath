package userObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuildYourGoldCoinCombinationPage {

	//Finding WebElements Using @FindBy Annotations

	@FindBy(xpath="//span[.=' Current weight ']/following-sibling::p[contains(text(),' g')]")private WebElement CurrentWeightTxt;
	
    @FindBy(xpath="//img[@alt='Gold Coin']/following-sibling::span[contains(.,'1 g')]")private WebElement GramsText;
    
    @FindBy(xpath="//img[@alt='Gold Coin']/following-sibling::span[contains(.,'1 g')]/../following-sibling::div/button[.=' - ']")private WebElement GramsDecreaseBtn;
    
    @FindBy(xpath="//img[@alt='Gold Coin']/following-sibling::span[contains(.,'1 g')]/../following-sibling::div//button[.=' + ']")private WebElement GramsIncreaseBtn;
    
    @FindBy(xpath="//div[@class='selection-card']")private WebElement TotalSelectedWeightsTxt;
  
    @FindBy(xpath="//button[.=' Continue to review ']")private WebElement ContinueBtn;  
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public BuildYourGoldCoinCombinationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule-3:Provide getters to access these variables
	
	public WebElement getCurrentWeightTxt() {
		return CurrentWeightTxt;
	}

	public WebElement getGramsText() {
		return GramsText;
	}

	public WebElement getGramsDecreaseBtn() {
		return GramsDecreaseBtn;
	}

	public WebElement getGramsIncreaseBtn() {
		return GramsIncreaseBtn;
	}

	public WebElement getTotalSelectedWeightsTxt() {
		return TotalSelectedWeightsTxt;
	}

	public WebElement getContinueBtn() {
		return ContinueBtn;
	}

	// Business Library
	
	public void increaseGoldGramsAccToWeight(WebDriver driver, String gram, int quantity) throws Exception 
	{
		Thread.sleep(2000);
	    for (int i = 0; i < quantity; i++) 
	    {
	        driver.findElement(By.xpath("//img[@alt='Gold Coin']/following-sibling::span[contains(.,'" + gram + " g')]/../following-sibling::div//button[normalize-space()='+']")).click();
	        Thread.sleep(2000);
	    }
	}
	
	public void decreaseGoldGramsAccToWeight(WebDriver driver, String gram, int quantity) throws Exception 
	{
		Thread.sleep(2000);
	    for (int i = 0; i < quantity; i++) 
	    {
	        driver.findElement(By.xpath("//img[@alt='Gold Coin']/following-sibling::span[contains(.,'" + gram + " g')]/../following-sibling::div//button[normalize-space()='-']")).click();
	        Thread.sleep(2000);
	    }
	}
	
	
	
}
