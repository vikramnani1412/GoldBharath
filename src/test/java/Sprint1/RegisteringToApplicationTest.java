package Sprint1;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import userObjectRepository.LoginPage;
import userObjectRepository.RegisterPage;

public class RegisteringToApplicationTest {

    WebDriverUtility wUtil = new WebDriverUtility();
    PropertyFileUtility pUtil = new PropertyFileUtility();
    JavaUtility jUtil = new JavaUtility();
    ExcelFileUtility eUtil = new ExcelFileUtility();

    
    // ============================================================
    // DATA PROVIDER - READ MULTIPLE MOBILE NUMBERS FROM EXCEL
    // ============================================================

    @DataProvider(name = "mobileNumbers")
    public Object[][] mobileNumbers() throws Exception {

        List<String> mobileNumbers = new ArrayList<>();

        // Reading mobile numbers from Doctor sheet
        // Column 1 = Mobile Number
        for (int row = 10; row <= 29; row++) {

        	String mobileNumber = eUtil.readDataFromExcel("Sheet1", row, 1);

            if (mobileNumber != null && !mobileNumber.trim().isEmpty()) {

                mobileNumbers.add(mobileNumber.trim());

                System.out.println(
                        "Excel Row " + row + " : " + mobileNumber
                );
            }
        }

        if (mobileNumbers.isEmpty()) {
            throw new Exception(
                    "No mobile numbers found from Excel rows 10 to 30."
            );
        }

        // Sending complete List to Test method
        return new Object[][]{
                {mobileNumbers}
        };
    }


    // ============================================================
    // TEST CASE
    // ============================================================

    @Test(priority = 1, dataProvider = "mobileNumbers")
    public void RegisteringToGoldbharathTest(List<String> mobileNumbers) throws Throwable {

        // --------------------------------------------------------
        // Read Registration Details
        // --------------------------------------------------------

        String BusinessName =
                eUtil.readDataFromExcel("Sheet1", 1, 1);

        String PersonName =
                eUtil.readDataFromExcel("Sheet1", 2, 1);

        String Email =
                eUtil.readDataFromExcel("Sheet1", 3, 1);

        String Date =
                eUtil.readDataFromExcel("Sheet1", 5, 1);

        String Pincode =
                eUtil.readDataFromExcel("Sheet1", 6, 1);

        String Address =
                eUtil.readDataFromExcel("Sheet1", 7, 1);

        // --------------------------------------------------------
        // Read URL
        // --------------------------------------------------------
        String URL = pUtil.readDataFromPropertyFile("userUrl");
        
        
        // --------------------------------------------------------
        // Chrome Configuration
        // --------------------------------------------------------

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // options.addArguments("--headless=new");

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");


        // --------------------------------------------------------
        // Launch Browser
        // --------------------------------------------------------

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();

            driver.get(URL);

            Thread.sleep(3000);


            // ----------------------------------------------------
            // Click Register Button
            // ----------------------------------------------------

            driver.findElement(
                    By.xpath("//button[.='Register']")
            ).click();


            // ----------------------------------------------------
            // Create Register Page Object
            // ----------------------------------------------------

            RegisterPage rPage = new RegisterPage(driver);


            // ----------------------------------------------------
            // Pass List of Mobile Numbers
            //
            // The Page Object should:
            //
            // 1. Enter first mobile number
            // 2. Click Register
            // 3. Check whether number already exists
            // 4. If exists -> try next number
            // 5. Continue until unique number is found
            // ----------------------------------------------------

            String uniqueMobileNumber =
                    rPage.registeringToGoldbharathApplication(
                            driver,
                            BusinessName,
                            PersonName,
                            Email,
                            mobileNumbers,
                            Date,
                            Pincode,
                            Address
                    );


            // ----------------------------------------------------
            // Print the mobile number accepted by application
            // ----------------------------------------------------

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "Final Unique Mobile Number : "
                            + uniqueMobileNumber
            );

            System.out.println(
                    "=============================================="
            );

            Thread.sleep(2000);
            
            LoginPage lPage = new LoginPage(driver);
            lPage.LoginToApplication(driver, uniqueMobileNumber);
            
            Thread.sleep(2000);
            
            

        } finally {

            // ----------------------------------------------------
            // Always close browser
            // ----------------------------------------------------

            if (driver != null) {
                driver.quit();
            }
        }
        
    }
        
    @Test
    public void loginUpdatingKYC() throws Exception
    {
        // --------------------------------------------------------
        // Read URL
        // --------------------------------------------------------
        String URL = pUtil.readDataFromPropertyFile("userUrl");
    	
    	WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // options.addArguments("--headless=new");

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");


        // --------------------------------------------------------
        // Launch Browser
        // --------------------------------------------------------

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();
            driver.get(URL);
            Thread.sleep(2000);
            
            
            
            
            
        }
        catch (Exception e) {
			
		}
    }
        
    
    
}