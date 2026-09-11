package Sprint1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import userObjectRepository.LoginPage;
import userObjectRepository.WelcomePage;

public class LiginToGoldBharath {

	@Test
	public void login() throws Exception
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://stg.goldbharat.com/");
		Thread.sleep(2000);
		WelcomePage wPage = new WelcomePage(driver);
		wPage.clickOnLoginLink();
		
		LoginPage lpage = new LoginPage(driver);
		lpage.LoginToApplication(driver, "9999999999");
	}
	
}
