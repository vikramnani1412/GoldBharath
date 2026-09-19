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

            // ====================================================
            // STEP 3
            // APPROVE KYC
            // ====================================================

//            mdPage.clickOnRecentlyRegisteredAndKycCompletedDistributor(driver, registeredMobileNumber, ownerName);
            
//            mdPage.approveKyc(driver);

            // ====================================================
            // STEP 4
            // GIVE GOLD LIMIT
            // ====================================================

            
            
//            mdPage.giveGoldLimit(
//                    driver,
//                    goldLimit
//            );

            // ====================================================
            // STEP 5
            // GIVE SILVER LIMIT
            // ====================================================

//            mdPage.giveSilverLimit(
//                    driver,
//                    silverLimit
//            );

            // ====================================================
            // STEP 6
            // VERIFY
            // ====================================================

//            mdPage.verifyDistributorDetails(
//                    driver,
//                    registeredMobileNumber,
//                    goldLimit,
//                    silverLimit
//            );

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

//            if (driver != null) {
//                driver.quit();
//            }
        }
    }
}






























//package Sprint1;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import adminObjectRepository.ManageDistributorPage;
//import genericUtilities.ExcelFileUtility;
//import genericUtilities.JavaUtility;
//import genericUtilities.PropertyFileUtility;
//import genericUtilities.UserBaseClass;
//import genericUtilities.WebDriverUtility;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import userObjectRepository.DashboardPage;
//import userObjectRepository.KycAsProprietorPage;
//import userObjectRepository.LoginPage;
//import userObjectRepository.RegisterPage;
//import userObjectRepository.WelcomePage;
//
//public class GoldBharathFullWorkFlow  {
//
//    // ============================================================
//    // OBJECT CREATION
//    // ============================================================
//
//    WebDriverUtility wUtil = new WebDriverUtility();
//
//    PropertyFileUtility pUtil = new PropertyFileUtility();
//
//    JavaUtility jUtil = new JavaUtility();
//
//    ExcelFileUtility eUtil = new ExcelFileUtility();
//
//
//    // ============================================================
//    // STORE REGISTERED MOBILE NUMBER
//    // ============================================================
//
//    private static String registeredMobileNumber;
//
//
//    // ============================================================
//    // DATA PROVIDER
//    // ============================================================
//
//    @DataProvider(name = "mobileNumbers")
//    public Object[][] mobileNumbers() throws Exception {
//
//        List<String> mobileNumbers = new ArrayList<>();
//
//        for (int row = 10; row <= 29; row++) {
//
//            String mobileNumber =
//                    eUtil.readDataFromExcel("Sheet1", row, 1);
//
//            if (mobileNumber != null &&
//                    !mobileNumber.trim().isEmpty()) {
//
//                mobileNumbers.add(mobileNumber.trim());
//
//                System.out.println(
//                        "Excel Row " + row + " : " + mobileNumber
//                );
//            }
//        }
//
//        if (mobileNumbers.isEmpty()) {
//
//            throw new Exception(
//                    "No mobile numbers found from Excel rows 10 to 30."
//            );
//        }
//
//        return new Object[][] {
//                { mobileNumbers }
//        };
//    }
//
//
//    // ============================================================
//    // TEST 1 - REGISTER
//    // ============================================================
//
//    @Test(priority = 1,dataProvider = "mobileNumbers")
//    public void RegisteringToGoldbharathTest(List<String> mobileNumbers) throws Throwable {
//
//        // --------------------------------------------------------
//        // Read Registration Details
//        // --------------------------------------------------------
//
//        String BusinessName =
//                eUtil.readDataFromExcel("Sheet1", 1, 1);
//
//        String PersonName =
//                eUtil.readDataFromExcel("Sheet1", 2, 1);
//
//        String Email =
//                eUtil.readDataFromExcel("Sheet1", 3, 1);
//
//        String Date =
//                eUtil.readDataFromExcel("Sheet1", 5, 1);
//
//        String Pincode =
//                eUtil.readDataFromExcel("Sheet1", 6, 1);
//
//        String Address =
//                eUtil.readDataFromExcel("Sheet1", 7, 1);
//
//
//        // --------------------------------------------------------
//        // Read URL
//        // --------------------------------------------------------
//
//        String URL = pUtil.readDataFromPropertyFile("userUrl");
//
//
//        // --------------------------------------------------------
//        // Chrome Configuration
//        // --------------------------------------------------------
//
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-infobars");
//
//
//        WebDriver driver = new ChromeDriver(options);
//
//        try {
//
//            driver.manage().window().maximize();
//
//            driver.get(URL);
//
//            Thread.sleep(3000);
//
//
//            // ----------------------------------------------------
//            // Click Register
//            // ----------------------------------------------------
//
//            driver.findElement(
//                    By.xpath("//button[normalize-space()='Register']")
//            ).click();
//
//
//            // ----------------------------------------------------
//            // Register Page
//            // ----------------------------------------------------
//
//            RegisterPage rPage =
//                    new RegisterPage(driver);
//
//
//            // ----------------------------------------------------
//            // Register using Excel mobile numbers
//            // ----------------------------------------------------
//
//            registeredMobileNumber =
//                    rPage.registeringToGoldbharathApplication(
//                            driver,
//                            BusinessName,
//                            PersonName,
//                            Email,
//                            mobileNumbers,
//                            Date,
//                            Pincode,
//                            Address
//                    );
//
//
//            // ----------------------------------------------------
//            // Validate Registered Mobile Number
//            // ----------------------------------------------------
//
//            if (registeredMobileNumber == null ||
//                    registeredMobileNumber.trim().isEmpty()) {
//
//                throw new Exception(
//                        "Registration completed but registered mobile number was not returned."
//                );
//            }
//
//
//            // ----------------------------------------------------
//            // Print registered number
//            // ----------------------------------------------------
//
//            System.out.println(
//                    "=============================================="
//            );
//
//            System.out.println("Final Unique Mobile Number : "+ registeredMobileNumber);
//
//            System.out.println(
//                    "=============================================="
//            );
//
//
//            // ----------------------------------------------------
//            // Login using same registered number
//            // ----------------------------------------------------
//
//            Thread.sleep(2000);
//
//            LoginPage lPage =
//                    new LoginPage(driver);
//
//            lPage.LoginToApplication(
//                    driver,
//                    registeredMobileNumber
//            );
//
//            Thread.sleep(2000);
//
//        }
//
//        finally {
//
//            if (driver != null) {
//
//                driver.quit();
//            }
//        }
//    }
//
//
//    // ============================================================
//    // TEST 2 - LOGIN AND UPDATE KYC
//    // ============================================================
//
//    @Test(dependsOnMethods = "RegisteringToGoldbharathTest")
//    public void loginUpdatingKYC() throws Exception {
//
//
//        // --------------------------------------------------------
//        // Make sure registration completed
//        // --------------------------------------------------------
//
//        if (registeredMobileNumber == null ||
//                registeredMobileNumber.trim().isEmpty()) {
//
//            throw new Exception(
//                    "Registered mobile number is not available. "
//                    + "Registration test may have failed."
//            );
//        }
//
//
//        System.out.println(
//                "=============================================="
//        );
//
//        System.out.println(
//                "Login using Registered Mobile Number : "
//                + registeredMobileNumber
//        );
//
//        System.out.println(
//                "=============================================="
//        );
//
//
//        // --------------------------------------------------------
//        // Read KYC details
//        // --------------------------------------------------------
//
//        String PropreitorName =
//                eUtil.readDataFromExcel("Sheet1", 34, 2);
//
//        String PropreitorPan =
//                eUtil.readDataFromExcel("Sheet1", 35, 2);
//
//        String GstNumber =
//                eUtil.readDataFromExcel("Sheet1", 36, 2);
//
//        String BankName =
//                eUtil.readDataFromExcel("Sheet1", 37, 2);
//
//        String AccountNumber =
//                eUtil.readDataFromExcel("Sheet1", 38, 2);
//
//        String IFSCCode =
//                eUtil.readDataFromExcel("Sheet1", 39, 2);
//
//        String AccountHolderName =
//                eUtil.readDataFromExcel("Sheet1", 40, 2);
//
//
//        // --------------------------------------------------------
//        // Read URL
//        // --------------------------------------------------------
//
//        String URL =
//                pUtil.readDataFromPropertyFile("userUrl");
//
//
//        // --------------------------------------------------------
//        // Chrome Configuration
//        // --------------------------------------------------------
//
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-infobars");
//
//
//        WebDriver driver =
//                new ChromeDriver(options);
//
//
//        try {
//
//            driver.manage().window().maximize();
//
//            driver.get(URL);
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Click Login
//            // ----------------------------------------------------
//
//            WelcomePage wPage =
//                    new WelcomePage(driver);
//
//            wPage.clickOnLoginLink();
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // LOGIN USING REGISTERED NUMBER
//            // ----------------------------------------------------
//
//            LoginPage lPage =
//                    new LoginPage(driver);
//
//            lPage.LoginToApplication(
//                    driver,
//                    registeredMobileNumber
//            );
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Logout / User Profile
//            // ----------------------------------------------------
//
//            DashboardPage dbPage =
//                    new DashboardPage(driver);
//
//            dbPage.clickOnUserProfileImageAndLogoutLink(driver);
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Click KYC
//            // ----------------------------------------------------
//
//            driver.findElement(
//                    By.xpath("//div[normalize-space()='KYC']")
//            ).click();
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // KYC Page
//            // ----------------------------------------------------
//
//            KycAsProprietorPage kPage =
//                    new KycAsProprietorPage(driver);
//
//
//            kPage.proprietorKyc(driver, PropreitorName, PropreitorPan, GstNumber, BankName, AccountNumber, IFSCCode, AccountHolderName);
//
//
//            System.out.println(
//                    "=============================================="
//            );
//
//            System.out.println(
//                    "KYC Updated Successfully"
//            );
//
//            System.out.println(
//                    "Mobile Number : "
//                    + registeredMobileNumber
//            );
//
//            System.out.println(
//                    "=============================================="
//            );
//
//        }
//
//        finally {
//
////            if (driver != null) {
////
////                driver.quit();
////            }
//        }
//    }
//
//
//    // ============================================================
//    // TEST 3 - ADMIN LOGIN
//    // APPROVE KYC
//    // GIVE GOLD AND SILVER LIMIT
//    // ============================================================
//
//    @Test(
//        dependsOnMethods = "loginUpdatingKYC"
//    )
//    public void loginToAdminApproveKycAndGivingGoldAndSilverLimit()
//            throws Exception {
//
//
//        // --------------------------------------------------------
//        // Make sure registration completed
//        // --------------------------------------------------------
//
//        if (registeredMobileNumber == null ||
//                registeredMobileNumber.trim().isEmpty()) {
//
//            throw new Exception(
//                    "Registered mobile number is not available. "
//                    + "Registration test may have failed."
//            );
//        }
//
//
//        // --------------------------------------------------------
//        // Read Owner Name
//        // --------------------------------------------------------
//
//        String ownerName =
//                eUtil.readDataFromExcel("Sheet1", 35, 2);
//
//
//        if (ownerName == null ||
//                ownerName.trim().isEmpty()) {
//
//            throw new Exception(
//                    "Owner Name is not available in Excel."
//            );
//        }
//
//
//        // --------------------------------------------------------
//        // Print details
//        // --------------------------------------------------------
//
//        System.out.println(
//                "=============================================="
//        );
//
//        System.out.println(
//                "ADMIN FLOW"
//        );
//
//        System.out.println(
//                "Registered Mobile Number : "
//                + registeredMobileNumber
//        );
//
//        System.out.println(
//                "Owner Name : "
//                + ownerName
//        );
//
//        System.out.println(
//                "=============================================="
//        );
//
//
//        // --------------------------------------------------------
//        // Read Admin Login Details
//        // --------------------------------------------------------
//
//        String URL =
//                pUtil.readDataFromPropertyFile("adminurl");
//
//        String USERNAME =
//                pUtil.readDataFromPropertyFile("adminusername");
//
//        String PASSWORD =
//                pUtil.readDataFromPropertyFile("adminpassword");
//
//
//        // --------------------------------------------------------
//        // Chrome Configuration
//        // --------------------------------------------------------
//
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--disable-notifications");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-infobars");
//
//
//        WebDriver driver =
//                new ChromeDriver(options);
//
//
//        try {
//
//            // ----------------------------------------------------
//            // Browser Configuration
//            // ----------------------------------------------------
//
//            driver.manage()
//                  .timeouts()
//                  .implicitlyWait(Duration.ofSeconds(10));
//
//            driver.manage().window().maximize();
//
//
//            // ----------------------------------------------------
//            // Open Admin URL
//            // ----------------------------------------------------
//
//            driver.get(URL);
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Admin Login
//            // ----------------------------------------------------
//
//            adminObjectRepository.LoginPage lPage =
//                    new adminObjectRepository.LoginPage(driver);
//
//            lPage.loginToAdmin(
//                    USERNAME,
//                    PASSWORD
//            );
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Admin Dashboard
//            // ----------------------------------------------------
//
//            adminObjectRepository.DashboardPage dbPage =
//                    new adminObjectRepository.DashboardPage(driver);
//
//
//            dbPage.getAllDistributorLnk().click();
//
//            Thread.sleep(2000);
//
//
//            // ----------------------------------------------------
//            // Manage Distributor
//            // ----------------------------------------------------
//
//            ManageDistributorPage mdPage =
//                    new ManageDistributorPage(driver);
//
//
//            // ----------------------------------------------------
//            // Find Distributor using:
//            //
//            // 1. Registered Mobile Number
//            // 2. Owner Name
//            // ----------------------------------------------------
//
//            mdPage.clickOnRecentlyRegisteredAndKycCompletedDistributorAcceptingKyc(driver, registeredMobileNumber, ownerName);
//            
////            mdPage.clickOnRecentlyRegisteredAndKycCompletedDistributor(
////                    driver,
////                    registeredMobileNumber,
////                    ownerName
////            );
//
//
//            Thread.sleep(2000);
//
//
//            System.out.println(
//                    "=============================================="
//            );
//
//            System.out.println(
//                    "Admin Distributor Flow Completed"
//            );
//
//            System.out.println(
//                    "Registered Mobile Number : "
//                    + registeredMobileNumber
//            );
//
//            System.out.println(
//                    "Owner Name : "
//                    + ownerName
//            );
//
//            System.out.println(
//                    "=============================================="
//            );
//
//        }
//
//        finally {
//
////            if (driver != null) {
////
////                driver.quit();
////            }
//        }
//    }
//}
//
//
//
//
//
////package Sprint1;
////
////import java.io.IOException;
////import java.time.Duration;
////import java.util.ArrayList;
////import java.util.List;
////
////import org.openqa.selenium.By;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.chrome.ChromeDriver;
////import org.openqa.selenium.chrome.ChromeOptions;
////import org.testng.annotations.DataProvider;
////import org.testng.annotations.Test;
////
////import adminObjectRepository.ManageDistributorPage;
////import genericUtilities.ExcelFileUtility;
////import genericUtilities.JavaUtility;
////import genericUtilities.PropertyFileUtility;
////import genericUtilities.WebDriverUtility;
////import io.github.bonigarcia.wdm.WebDriverManager;
////
////import userObjectRepository.DashboardPage;
////import userObjectRepository.KycAsProprietorPage;
////import userObjectRepository.LoginPage;
////import userObjectRepository.RegisterPage;
////import userObjectRepository.WelcomePage;
////
////public class GoldBharathFullWorkFlow extends UserBaseClass {
////
////    WebDriverUtility wUtil = new WebDriverUtility();
////    PropertyFileUtility pUtil = new PropertyFileUtility();
////    JavaUtility jUtil = new JavaUtility();
////    ExcelFileUtility eUtil = new ExcelFileUtility();
////
////    // ============================================================
////    // STORE REGISTERED MOBILE NUMBER
////    // ============================================================
////
////    private static String registeredMobileNumber;
////
////
////    // ============================================================
////    // DATA PROVIDER
////    // ============================================================
////
////    @DataProvider(name = "mobileNumbers")
////    public Object[][] mobileNumbers() throws Exception {
////
////        List<String> mobileNumbers = new ArrayList<>();
////
////        for (int row = 10; row <= 29; row++) {
////
////            String mobileNumber =
////                    eUtil.readDataFromExcel("Sheet1", row, 1);
////
////            if (mobileNumber != null &&
////                    !mobileNumber.trim().isEmpty()) {
////
////                mobileNumbers.add(mobileNumber.trim());
////
////                System.out.println(
////                        "Excel Row " + row + " : " + mobileNumber
////                );
////            }
////        }
////
////        if (mobileNumbers.isEmpty()) {
////
////            throw new Exception(
////                    "No mobile numbers found from Excel rows 10 to 30."
////            );
////        }
////
////        return new Object[][] {
////                { mobileNumbers }
////        };
////    }
////
////
////    // ============================================================
////    // TEST 1 - REGISTER
////    // ============================================================
////
////    @Test(
////        priority = 1,
////        dataProvider = "mobileNumbers"
////    )
////    public void RegisteringToGoldbharathTest(
////            List<String> mobileNumbers) throws Throwable {
////
////        // --------------------------------------------------------
////        // Read Registration Details
////        // --------------------------------------------------------
////
////        String BusinessName =
////                eUtil.readDataFromExcel("Sheet1", 1, 1);
////
////        String PersonName =
////                eUtil.readDataFromExcel("Sheet1", 2, 1);
////
////        String Email =
////                eUtil.readDataFromExcel("Sheet1", 3, 1);
////
////        String Date =
////                eUtil.readDataFromExcel("Sheet1", 5, 1);
////
////        String Pincode =
////                eUtil.readDataFromExcel("Sheet1", 6, 1);
////
////        String Address =
////                eUtil.readDataFromExcel("Sheet1", 7, 1);
////
////
////        // --------------------------------------------------------
////        // Read URL
////        // --------------------------------------------------------
////
////        String URL =
////                pUtil.readDataFromPropertyFile("userUrl");
////
////
////        // --------------------------------------------------------
////        // Chrome Configuration
////        // --------------------------------------------------------
////
////        WebDriverManager.chromedriver().setup();
////
////        ChromeOptions options = new ChromeOptions();
////
////        options.addArguments("--disable-gpu");
////        options.addArguments("--window-size=1920,1080");
////        options.addArguments("--disable-notifications");
////        options.addArguments("--disable-popup-blocking");
////        options.addArguments("--disable-infobars");
////
////
////        WebDriver driver = new ChromeDriver(options);
////
////        try {
////
////            driver.manage().window().maximize();
////
////            driver.get(URL);
////
////            Thread.sleep(3000);
////
////
////            // ----------------------------------------------------
////            // Click Register
////            // ----------------------------------------------------
////
////            driver.findElement(
////                    By.xpath("//button[normalize-space()='Register']")
////            ).click();
////
////
////            // ----------------------------------------------------
////            // Register Page
////            // ----------------------------------------------------
////
////            RegisterPage rPage =
////                    new RegisterPage(driver);
////
////
////            // ----------------------------------------------------
////            // Register using Excel mobile numbers
////            // ----------------------------------------------------
////
////            registeredMobileNumber =
////                    rPage.registeringToGoldbharathApplication(
////                            driver,
////                            BusinessName,
////                            PersonName,
////                            Email,
////                            mobileNumbers,
////                            Date,
////                            Pincode,
////                            Address
////                    );
////
////
////            // ----------------------------------------------------
////            // Print registered number
////            // ----------------------------------------------------
////
////            System.out.println(
////                    "=============================================="
////            );
////
////            System.out.println(
////                    "Final Unique Mobile Number : "
////                    + registeredMobileNumber
////            );
////
////            System.out.println(
////                    "=============================================="
////            );
////
////
////            // ----------------------------------------------------
////            // Login using same registered number
////            // ----------------------------------------------------
////
////            Thread.sleep(2000);
////
////            LoginPage lPage =
////                    new LoginPage(driver);
////
////            lPage.LoginToApplication(
////                    driver,
////                    registeredMobileNumber
////            );
////
////            Thread.sleep(2000);
////
////        }
////        finally {
////
////            if (driver != null) {
////                driver.quit();
////            }
////        }
////    }
////
////
////    // ============================================================
////    // TEST 2 - LOGIN AND UPDATE KYC
////    // ============================================================
////
////    @Test(
////        dependsOnMethods = "RegisteringToGoldbharathTest"
////    )
////    public void loginUpdatingKYC() throws Exception {
////
////        // --------------------------------------------------------
////        // Make sure registration completed
////        // --------------------------------------------------------
////
////        if (registeredMobileNumber == null ||
////                registeredMobileNumber.trim().isEmpty()) {
////
////            throw new Exception(
////                    "Registered mobile number is not available. "
////                    + "Registration test may have failed."
////            );
////        }
////
////
////        System.out.println(
////                "Login using Registered Mobile Number : "
////                + registeredMobileNumber
////        );
////
////
////        // --------------------------------------------------------
////        // Read KYC details
////        // --------------------------------------------------------
////
////        String PropreitorName =
////                eUtil.readDataFromExcel("Sheet1", 34, 2);
////
////        String PropreitorPan =
////                eUtil.readDataFromExcel("Sheet1", 35, 2);
////
////        String GstNumber =
////                eUtil.readDataFromExcel("Sheet1", 36, 2);
////
////        String BankName =
////                eUtil.readDataFromExcel("Sheet1", 37, 2);
////
////        String AccountNumber =
////                eUtil.readDataFromExcel("Sheet1", 38, 2);
////
////        String IFSCCode =
////                eUtil.readDataFromExcel("Sheet1", 39, 2);
////
////        String AccountHolderName =
////                eUtil.readDataFromExcel("Sheet1", 34, 2);
////
////
////        // --------------------------------------------------------
////        // Read URL
////        // --------------------------------------------------------
////
////        String URL =
////                pUtil.readDataFromPropertyFile("userUrl");
////
////
////        // --------------------------------------------------------
////        // Chrome Configuration
////        // --------------------------------------------------------
////
////        WebDriverManager.chromedriver().setup();
////
////        ChromeOptions options = new ChromeOptions();
////
////        options.addArguments("--disable-gpu");
////        options.addArguments("--window-size=1920,1080");
////        options.addArguments("--disable-notifications");
////        options.addArguments("--disable-popup-blocking");
////        options.addArguments("--disable-infobars");
////
////
////        WebDriver driver =
////                new ChromeDriver(options);
////
////        try {
////
////            driver.manage().window().maximize();
////
////            driver.get(URL);
////
////            Thread.sleep(2000);
////
////
////            // ----------------------------------------------------
////            // Click Login
////            // ----------------------------------------------------
////
////            WelcomePage wPage =
////                    new WelcomePage(driver);
////
////            wPage.clickOnLoginLink();
////
////            Thread.sleep(2000);
////
////
////            // ----------------------------------------------------
////            // LOGIN USING REGISTERED NUMBER
////            // ----------------------------------------------------
////
////            LoginPage lPage =
////                    new LoginPage(driver);
////
////            lPage.LoginToApplication(
////                    driver,
////                    registeredMobileNumber
////            );
////
////            Thread.sleep(2000);
////
////
////            // ----------------------------------------------------
////            // Logout / User Profile
////            // ----------------------------------------------------
////
////            DashboardPage dbPage =
////                    new DashboardPage(driver);
////
////            dbPage.clickOnUserProfileImageAndLogoutLink();
////
////            Thread.sleep(2000);
////
////
////            // ----------------------------------------------------
////            // KYC
////            // ----------------------------------------------------
////
////            driver.findElement(
////                    By.xpath("//div[normalize-space()='KYC']")
////            ).click();
////
////            Thread.sleep(2000);
////
////
////            KycAsProprietorPage kPage =
////                    new KycAsProprietorPage(driver);
////
////            kPage.proprietorKyc(
////                    PropreitorName,
////                    PropreitorPan,
////                    GstNumber,
////                    BankName,
////                    AccountNumber,
////                    IFSCCode,
////                    AccountHolderName
////            );
////
////        }
////        finally {
////
//////            if (driver != null) {
//////                driver.quit();
//////            }
////        }
////    }
////    
////    @Test
////	public void loginToAdminApproveKycAndGivingGoldAndSilverLimit() throws Exception
////	{
////    	// --------------------------------------------------------
////        // Read URL
////        // --------------------------------------------------------
////        String URL = pUtil.readDataFromPropertyFile("adminurl");
////        String USERNAME = pUtil.readDataFromPropertyFile("adminusername");
////        String PASSWORD = pUtil.readDataFromPropertyFile("adminpassword");
////    	
////		WebDriverManager.chromedriver().setup();
////		WebDriver driver = new ChromeDriver();
////		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////		driver.manage().window().maximize();
////		driver.get("http://stg-admin.goldbharat.com/login");
////		
////		adminObjectRepository.LoginPage lPage = new adminObjectRepository.LoginPage(driver);
////		lPage.loginToAdmin(USERNAME, PASSWORD);
////		
////		Thread.sleep(2000);
////		adminObjectRepository.DashboardPage dbPage = new adminObjectRepository.DashboardPage(driver);
////		dbPage.getAllDistributorLnk().click();
////		
////		ManageDistributorPage mdPage = new ManageDistributorPage(driver);
////		// Here have to pass registered mobile and OwnerName
////		mdPage.clickOnRecentlyRegisteredAndKycCompletedDistributor(driver, PASSWORD, USERNAME)
////	}
////    
////}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
