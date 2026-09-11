package userObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

	//Finding WebElements Using @FindBy Annotations

    @FindBy(xpath="(//li[.='Home']/ancestor::div//img[@class='goldbharathimg'])[1]")private WebElement GoldBharathImg;
    
    @FindBy(xpath="//li[.='Home']")private WebElement HomeLnk;
    
    @FindBy(xpath="//li[.='About Us']")private WebElement AboutUsLnk;
    
    @FindBy(xpath="//li[.='Products']")private WebElement ProductsLnk;
    
    @FindBy(xpath="//li[.='Privacy Policy']")private WebElement PrivacyLnk;
    
    @FindBy(xpath="//li[.='Contact Us']")private WebElement ContactUsLnk;
    
    @FindBy(xpath="//button[.='Register']")private WebElement RegisterBtn;
    
    @FindBy(xpath="//button[.='Login']")private WebElement LoginBtn;
    
    @FindBy(xpath="//div[@class='commodity-card gold-card']")private WebElement GoldPriceTotalSection;
    
    @FindBy(xpath="//div[@class='commodity-card silver-card']")private WebElement SilverPriceTotalSection;
    
    @FindBy(xpath="//p[normalize-space()='Live Price Per Gram']/following-sibling::div[1]")private WebElement GoldOnlyLivePriceTxt;
    
    @FindBy(xpath="//p[normalize-space()='Live Price Per Kg']/following-sibling::div[1]")private WebElement SilverOnlyLivePriceTxt;
    
    
	//Rule-2:Create a constructor to initilise these elements
    
	public WelcomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Rule-3:Provide getters to access these variables
	

	public WebElement getGoldBharathImg() {
		return GoldBharathImg;
	}


	public WebElement getHomeLnk() {
		return HomeLnk;
	}


	public WebElement getAboutUsLnk() {
		return AboutUsLnk;
	}


	public WebElement getProductsLnk() {
		return ProductsLnk;
	}


	public WebElement getPrivacyLnk() {
		return PrivacyLnk;
	}


	public WebElement getContactUsLnk() {
		return ContactUsLnk;
	}


	public WebElement getRegisterBtn() {
		return RegisterBtn;
	}


	public WebElement getLoginBtn() {
		return LoginBtn;
	}


	public WebElement getGoldPriceTotalSection() {
		return GoldPriceTotalSection;
	}


	public WebElement getSilverPriceTotalSection() {
		return SilverPriceTotalSection;
	}


	public WebElement getGoldOnlyLivePriceTxt() {
		return GoldOnlyLivePriceTxt;
	}


	public WebElement getSilverOnlyLivePriceTxt() {
		return SilverOnlyLivePriceTxt;
	}
	
	//Business Library
	
	public void clickOnLoginLink() throws Exception
	{
		Thread.sleep(2000);
		LoginBtn.click();
	}
	
	
}
