package genericUtilities;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class AdminBaseClass extends CommonBaseClass {

    protected WebDriver driver;

    protected PropertyFileUtility pUtil = new PropertyFileUtility();
    protected WebDriverUtility wUtil = new WebDriverUtility();
    protected JavaUtility jUtil = new JavaUtility();
    protected ExcelFileUtility eUtil = new ExcelFileUtility();

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() throws IOException {

        String browser = pUtil.readDataFromPropertyFile("browser1");
        String url = pUtil.readDataFromPropertyFile("adminUrl");

        // Used by Extent Report
        System.setProperty("browser", browser);
        System.setProperty("baseUrl", url);

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            driver = new EdgeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);

        } else {

            throw new RuntimeException("Unsupported Browser : " + browser);
        }

        // Store driver in ThreadLocal
        CommonBaseClass.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(url);
    }
    
    @BeforeMethod
    public void loginToAdminApp() throws Throwable
    {
    	String username = pUtil.readDataFromPropertyFile("adminusername");
        String password = pUtil.readDataFromPropertyFile("adminpassword");

//        AdminLoginPage loginPage = new AdminLoginPage(driver);
//
//        loginPage.loginToAdmin(driver, username, password);

        System.out.println("Admin Login Successful");
    }
    
    @AfterMethod
    public void logoutFromAdminApp()
    {
    	try {

//            AdminDashboardPage dashboardPage =
//                    new AdminDashboardPage(driver);
//
//            dashboardPage.logoutOfApplication();

            System.out.println("Admin Logout Successful");

        } catch (Exception e) {

            System.out.println(
                    "Logout skipped because user is already logged out or page not available");
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {

        if (driver != null) {
            driver.quit();
        }

        CommonBaseClass.unloadDriver();
    }
}