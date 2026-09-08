package Sprint1;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import adminObjectRepository.RegisterPage;
import genericUtilities.AdminBaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Check {

	WebDriverUtility wUtil = new WebDriverUtility();
	PropertyFileUtility pUtil = new PropertyFileUtility();
	JavaUtility jUtil = new JavaUtility();
	ExcelFileUtility eUtil = new ExcelFileUtility();
	
	@Test
	public void loginTest() throws Throwable
	{
		String URL = pUtil.readDataFromPropertyFile("userUrl");
		String BusinessName = eUtil.readDataFromExcel("Sheet1", 1, 1);
		String PersonName = eUtil.readDataFromExcel("Sheet1", 2, 1);;
		String Email = eUtil.readDataFromExcel("Sheet1", 3, 1);;
		String PhoneNumber = eUtil.readDataFromExcel("Sheet1", 4, 1);;
		String Date = eUtil.readDataFromExcel("Sheet1", 5, 1);;
		String Pincode = eUtil.readDataFromExcel("Sheet1", 6, 1);;
		String Address = eUtil.readDataFromExcel("Sheet1", 7, 1);;
		
		
		// ✅ Auto-manage driver version
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");   // run without GUI
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(URL);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[.='Register']")).click();
        
        RegisterPage rPage = new RegisterPage(driver);
        rPage.registeringToGoldbharathApplication(BusinessName, PersonName, Email, PhoneNumber, Date, Pincode, Address);
        

      
      
        
	}
}