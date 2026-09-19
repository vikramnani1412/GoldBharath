package Sprint1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import adminObjectRepository.BusinessInformationPage;
import adminObjectRepository.DistributorDetailsPage;
import adminObjectRepository.ManageDistributorPage;
import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import userObjectRepository.DashboardPage;
import userObjectRepository.KycAsProprietorPage;
import userObjectRepository.LoginPage;
import userObjectRepository.RegisterPage;
import userObjectRepository.UserProfilePage;
import userObjectRepository.WelcomePage;

public class GoldBharathFullWorkFlow {

    // ============================================================
    // UTILITIES
    // ============================================================

    WebDriverUtility wUtil = new WebDriverUtility();
    PropertyFileUtility pUtil = new PropertyFileUtility();
    ExcelFileUtility eUtil = new ExcelFileUtility();

    // ============================================================
    // STORE REGISTERED MOBILE NUMBER
    // ============================================================

    private static String registeredMobileNumber;

    // ============================================================
    // STORE DRIVER IF REQUIRED
    // ============================================================

    private WebDriver driver;

    // ============================================================
    // DATA PROVIDER
    // READ MOBILE NUMBERS FROM EXCEL
    // ============================================================

    @DataProvider(name = "mobileNumbers")
    public Object[][] mobileNumbers() throws Exception {

        List<String> mobileNumbers = new ArrayList<>();

        /*
         * Excel:
         *
         * Row 10 -> mobile
         * Row 11 -> mobile
         * ...
         * Row 29 -> mobile
         *
         * Important:
         * Apache POI row index is 0 based.
         */

        for (int row = 10; row <= 29; row++) {

            String mobileNumber =
                    eUtil.readDataFromExcel("Sheet1", row, 1);

            if (mobileNumber != null &&
                    !mobileNumber.trim().isEmpty()) {

                mobileNumbers.add(mobileNumber.trim());

                System.out.println(
                        "Excel Row " + row +
                        " : " + mobileNumber
                );
            }
        }

        if (mobileNumbers.isEmpty()) {

            throw new Exception(
                    "No mobile numbers found from Excel."
            );
        }

        return new Object[][] {
                { mobileNumbers }
        };
    }

    // ============================================================
    // TEST 1
    // REGISTER USER
    // CAPTURE SAME REGISTERED MOBILE NUMBER
    // ============================================================

    @Test(
        priority = 1,
        dataProvider = "mobileNumbers"
    )
    public void RegisteringToGoldbharathTest(
            List<String> mobileNumbers) throws Exception {

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "TEST 1 - GOLDBHARATH REGISTRATION"
        );

        System.out.println(
                "================================================"
        );

        // --------------------------------------------------------
        // READ REGISTRATION DATA
        // --------------------------------------------------------

        String businessName =
                eUtil.readDataFromExcel(
                        "Sheet1", 1, 1);

        String personName =
                eUtil.readDataFromExcel(
                        "Sheet1", 2, 1);

        String email =
                eUtil.readDataFromExcel(
                        "Sheet1", 3, 1);

        String date =
                eUtil.readDataFromExcel(
                        "Sheet1", 5, 1);

        String pincode =
                eUtil.readDataFromExcel(
                        "Sheet1", 6, 1);

        String address =
                eUtil.readDataFromExcel(
                        "Sheet1", 7, 1);

        // --------------------------------------------------------
        // READ USER URL
        // --------------------------------------------------------

        String userUrl =
                pUtil.readDataFromPropertyFile(
                        "userUrl");

        // --------------------------------------------------------
        // START CHROME
        // --------------------------------------------------------

        WebDriverManager.chromedriver().setup();

        ChromeOptions options =
                new ChromeOptions();

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();

            driver.get(userUrl);

            Thread.sleep(2000);

            // ----------------------------------------------------
            // CLICK REGISTER
            // ----------------------------------------------------

            driver.findElement(
                    By.xpath(
                        "//button[normalize-space()='Register']"
                    )
            ).click();

            Thread.sleep(1500);

            // ----------------------------------------------------
            // REGISTER PAGE
            // ----------------------------------------------------

            RegisterPage rPage =
                    new RegisterPage(driver);

            // ----------------------------------------------------
            // REGISTER
            //
            // IMPORTANT:
            // This method must RETURN the mobile number which
            // successfully registered.
            // ----------------------------------------------------

            registeredMobileNumber =
                    rPage.registeringToGoldbharathApplication(
                            driver,
                            businessName,
                            personName,
                            email,
                            mobileNumbers,
                            date,
                            pincode,
                            address
                    );

            // ----------------------------------------------------
            // VALIDATION
            // ----------------------------------------------------

            Assert.assertNotNull(
                    registeredMobileNumber,
                    "Registered mobile number is NULL"
            );

            Assert.assertFalse(
                    registeredMobileNumber.trim().isEmpty(),
                    "Registered mobile number is EMPTY"
            );

            System.out.println(
                    "------------------------------------------------"
            );

            System.out.println(
                    "REGISTERED MOBILE NUMBER : "
                    + registeredMobileNumber
            );

            System.out.println(
                    "------------------------------------------------"
            );

        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }


    // ============================================================
    // TEST 2
    // LOGIN USING SAME MOBILE
    // COMPLETE KYC
    // ============================================================

    @Test(
        priority = 2,
        dependsOnMethods = "RegisteringToGoldbharathTest"
    )
    public void loginUpdatingKYC() throws Exception {

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "TEST 2 - LOGIN + KYC"
        );

        System.out.println(
                "================================================"
        );

        // --------------------------------------------------------
        // VALIDATE MOBILE
        // --------------------------------------------------------

        if (registeredMobileNumber == null ||
                registeredMobileNumber.trim().isEmpty()) {

            throw new Exception(
                    "Registered mobile number is not available."
            );
        }

        System.out.println(
                "LOGIN MOBILE : "
                + registeredMobileNumber
        );

        // --------------------------------------------------------
        // READ KYC DATA
        // --------------------------------------------------------

        String proprietorName =
                eUtil.readDataFromExcel(
                        "Sheet1", 34, 2);

        String proprietorPan =
                eUtil.readDataFromExcel(
                        "Sheet1", 35, 2);

        String gstNumber =
                eUtil.readDataFromExcel(
                        "Sheet1", 36, 2);

        String bankName =
                eUtil.readDataFromExcel(
                        "Sheet1", 37, 2);

        String accountNumber =
                eUtil.readDataFromExcel(
                        "Sheet1", 38, 2);

        String ifscCode =
                eUtil.readDataFromExcel(
                        "Sheet1", 39, 2);

        String accountHolderName =
                eUtil.readDataFromExcel(
                        "Sheet1", 40, 2);

        // --------------------------------------------------------
        // USER URL
        // --------------------------------------------------------

        String userUrl =
                pUtil.readDataFromPropertyFile(
                        "userUrl");

        // --------------------------------------------------------
        // START CHROME
        // --------------------------------------------------------

        WebDriverManager.chromedriver().setup();

        ChromeOptions options =
                new ChromeOptions();

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();

            driver.get(userUrl);

            Thread.sleep(2000);

            // ----------------------------------------------------
            // CLICK LOGIN
            // ----------------------------------------------------

            WelcomePage welcomePage =
                    new WelcomePage(driver);

            welcomePage.clickOnLoginLink();

            Thread.sleep(1500);

            // ----------------------------------------------------
            // LOGIN USING SAME REGISTERED MOBILE
            // ----------------------------------------------------

            LoginPage loginPage =
                    new LoginPage(driver);

            loginPage.LoginToApplication(
                    driver,
                    registeredMobileNumber
            );

            Thread.sleep(2500);

            // ====================================================
            // IMPORTANT
            //
            // DO NOT LOGOUT HERE
            //
            // Your previous code was:
            //
            // dbPage.clickOnUserProfileImageAndLogoutLink(driver);
            //
            // That logs the user out before KYC.
            // ====================================================

            // ----------------------------------------------------
            // CLICK KYC
            // ----------------------------------------------------

            DashboardPage dbPage = new DashboardPage(driver);
            dbPage.clickOnUserProfileImageAndLogoutLink(driver);
            Thread.sleep(2000);
            
            UserProfilePage upPage = new UserProfilePage(driver);
            upPage.clickOnKycLink();
            Thread.sleep(2000);
            
            // ----------------------------------------------------
            // COMPLETE PROPRIETOR KYC
            // ----------------------------------------------------

            KycAsProprietorPage kycPage =
                    new KycAsProprietorPage(driver);

            kycPage.proprietorKyc(
                    driver,
                    proprietorName,
                    proprietorPan,
                    gstNumber,
                    bankName,
                    accountNumber,
                    ifscCode,
                    accountHolderName
            );

            System.out.println(
                    "------------------------------------------------"
            );

            System.out.println(
                    "KYC COMPLETED SUCCESSFULLY"
            );

            System.out.println(
                    "MOBILE : "
                    + registeredMobileNumber
            );

            System.out.println(
                    "------------------------------------------------"
            );

        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }


    // ============================================================
    // TEST 3
    //
    // ADMIN LOGIN
    // SEARCH DISTRIBUTOR
    // EDIT DETAILS
    // APPROVE KYC
    // GOLD LIMIT
    // SILVER LIMIT
    // VERIFY
    // ============================================================

    @Test(
        priority = 3,
        dependsOnMethods = "loginUpdatingKYC"
    )
    public void loginToAdminApproveKycAndGivingGoldAndSilverLimit()
            throws Exception {

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "TEST 3 - ADMIN DISTRIBUTOR FLOW"
        );

        System.out.println(
                "================================================"
        );

        // --------------------------------------------------------
        // VALIDATE REGISTERED MOBILE
        // --------------------------------------------------------

        if (registeredMobileNumber == null ||
                registeredMobileNumber.trim().isEmpty()) {

            throw new Exception(
                    "Registered mobile number is not available."
            );
        }

        // --------------------------------------------------------
        // READ ADMIN DATA
        // --------------------------------------------------------

        String adminUrl =
                pUtil.readDataFromPropertyFile(
                        "adminurl");

        String username =
                pUtil.readDataFromPropertyFile(
                        "adminusername");

        String password =
                pUtil.readDataFromPropertyFile(
                        "adminpassword");

        // --------------------------------------------------------
        // READ OWNER / KYC DATA
        // --------------------------------------------------------

        String ownerName =
                eUtil.readDataFromExcel(
                        "Sheet1", 34, 2);

        String proprietorName =
                eUtil.readDataFromExcel(
                        "Sheet1", 34, 2);

        String proprietorPan =
                eUtil.readDataFromExcel(
                        "Sheet1", 35, 2);

        String gstNumber =
                eUtil.readDataFromExcel(
                        "Sheet1", 36, 2);

        String bankName =
                eUtil.readDataFromExcel(
                        "Sheet1", 37, 2);

        String accountNumber =
                eUtil.readDataFromExcel(
                        "Sheet1", 38, 2);

        String ifscCode =
                eUtil.readDataFromExcel(
                        "Sheet1", 39, 2);

        String accountHolderName =
                eUtil.readDataFromExcel(
                        "Sheet1", 40, 2);

        // --------------------------------------------------------
        // GOLD / SILVER LIMIT
        //
        // Change these Excel rows according to your Excel file.
        //
        // Row 41 -> Gold Limit
        // Row 42 -> Silver Limit
        // --------------------------------------------------------

        String goldLimit =
                eUtil.readDataFromExcel(
                        "Sheet1", 48, 1);

        String silverLimit =
                eUtil.readDataFromExcel(
                        "Sheet1", 49, 1);

        // --------------------------------------------------------
        // VALIDATE LIMIT DATA
        // --------------------------------------------------------

        if (goldLimit == null ||
                goldLimit.trim().isEmpty()) {

            throw new Exception(
                    "Gold Limit is missing in Excel."
            );
        }

        if (silverLimit == null ||
                silverLimit.trim().isEmpty()) {

            throw new Exception(
                    "Silver Limit is missing in Excel."
            );
        }

        // --------------------------------------------------------
        // START CHROME
        // --------------------------------------------------------

        WebDriverManager.chromedriver().setup();

        ChromeOptions options =
                new ChromeOptions();

        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");

        driver = new ChromeDriver(options);

        try {

            driver.manage().window().maximize();

            // ----------------------------------------------------
            // ADMIN URL
            // ----------------------------------------------------

            driver.get(adminUrl);

            Thread.sleep(2000);

            // ----------------------------------------------------
            // ADMIN LOGIN
            // ----------------------------------------------------

            adminObjectRepository.LoginPage adminLogin =
                    new adminObjectRepository.LoginPage(driver);

            adminLogin.loginToAdmin(
                    username,
                    password
            );

            Thread.sleep(2500);

            // ----------------------------------------------------
            // CLICK ALL DISTRIBUTORS
            // ----------------------------------------------------

            adminObjectRepository.DashboardPage adminDashboard =
                    new adminObjectRepository.DashboardPage(driver);

            adminDashboard
                    .getAllDistributorLnk()
                    .click();

            Thread.sleep(2000);

            // ----------------------------------------------------
            // MANAGE DISTRIBUTOR
            // ----------------------------------------------------

            ManageDistributorPage mdPage =
                    new ManageDistributorPage(driver);

            // ====================================================
            // STEP 1
            // SEARCH SAME DISTRIBUTOR
            // ====================================================

            mdPage.clickOnRecentlyRegisteredAndKycCompletedDistributor(
                    driver,
                    registeredMobileNumber,
                    ownerName
            );

            // ====================================================
            // EDIT ALL DETAILS
            // ====================================================
            
            DistributorDetailsPage ddPage = new DistributorDetailsPage(driver);
            ddPage.acceptingDistributorKycDetails();
            
            // ====================================================
            // STEP 2
            // EDIT ALL DETAILS
            // ====================================================

            Thread.sleep(2000);
            mdPage.clickOnRecentlyRegisteredDistributorEditButton(driver, registeredMobileNumber);
            Thread.sleep(2000);
            BusinessInformationPage biPage = new BusinessInformationPage(driver);
            biPage.editingAndGivingSilverAndGoldLimit(silverLimit, goldLimit);

    
            // ----------------------------------------------------
            // FINAL SUCCESS
            // ----------------------------------------------------

            System.out.println(
                    "\n================================================"
            );

            System.out.println(
                    "GOLDBHARATH E2E FLOW COMPLETED"
            );

            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "Registered Mobile : "
                    + registeredMobileNumber
            );

            System.out.println(
                    "Owner Name        : "
                    + ownerName
            );

            System.out.println(
                    "Gold Limit        : "
                    + goldLimit
            );

            System.out.println(
                    "Silver Limit      : "
                    + silverLimit
            );

            System.out.println(
                    "================================================"
            );

        } finally {

            if (driver != null) {
                driver.quit();
            }
        }
    }
}

