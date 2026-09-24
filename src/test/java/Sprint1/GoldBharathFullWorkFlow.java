package Sprint1;

// ============================================================
// JAVA IMPORTS
// ============================================================

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// SELENIUM IMPORTS
// ============================================================

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// ============================================================
// TESTNG IMPORTS
// ============================================================

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// ============================================================
// ADMIN PAGE OBJECT IMPORTS
// ============================================================

import adminObjectRepository.BusinessInformationPage;
import adminObjectRepository.DistributorDetailsPage;
import adminObjectRepository.LogoutPage;
import adminObjectRepository.ManageDistributorPage;

// ============================================================
// GENERIC UTILITY IMPORTS
// ============================================================

import genericUtilities.ExcelFileUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import userObjectRepository.BookGoldPage;
import userObjectRepository.BuildYourGoldCoinCombinationPage;

// ============================================================
// USER PAGE OBJECT IMPORTS
// ============================================================

import userObjectRepository.DashboardPage;
import userObjectRepository.GoldCoinsHistoryPage;
import userObjectRepository.KycAsProprietorPage;
import userObjectRepository.LoginPage;
import userObjectRepository.RegisterPage;
import userObjectRepository.ReviewYourGoldOrderPage;
import userObjectRepository.UserProfilePage;
import userObjectRepository.WelcomePage;
import userObjectRepository.whatWouldYouLikeToBuyPage;

// ============================================================
// WEBDRIVER MANAGER
// ============================================================

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * ============================================================
 * CLASS NAME : GoldBharathFullWorkFlow
 * ============================================================
 *
 * COMPLETE GOLDBHARATH END-TO-END AUTOMATION FLOW
 *
 * FLOW:
 *
 * 1. Register User
 *          ↓
 * 2. Capture Registered Mobile Number
 *          ↓
 * 3. Login using Same Mobile Number
 *          ↓
 * 4. Complete Proprietor KYC
 *          ↓
 * 5. Login to Admin Panel
 *          ↓
 * 6. Navigate to All Distributors
 *          ↓
 * 7. Search Recently Registered/KYC Completed Distributor
 *          ↓
 * 8. Accept Distributor KYC
 *          ↓
 * 9. Edit Distributor Details
 *          ↓
 * 10. Give Gold Limit
 *          ↓
 * 11. Give Silver Limit
 *          ↓
 * 12. Complete E2E Flow
 *
 * ============================================================
 */
public class GoldBharathFullWorkFlow {

    // ============================================================
    // OBJECT CREATION FOR GENERIC UTILITIES
    // ============================================================

    /**
     * WebDriverUtility
     *
     * Used for reusable Selenium operations such as:
     * - Explicit waits
     * - Element handling
     * - Common WebDriver operations
     */
    WebDriverUtility wUtil = new WebDriverUtility();

    /**
     * PropertyFileUtility
     *
     * Used to read:
     * - User URL
     * - Admin URL
     * - Admin username
     * - Admin password
     */
    PropertyFileUtility pUtil = new PropertyFileUtility();

    /**
     * ExcelFileUtility
     *
     * Used to read:
     * - Registration data
     * - KYC data
     * - Gold limit
     * - Silver limit
     * - Mobile numbers
     */
    ExcelFileUtility eUtil = new ExcelFileUtility();


    // ============================================================
    // WEBDRIVER OBJECT
    // ============================================================

    /**
     * WebDriver instance used by the current test.
     */
    private WebDriver driver;


    // ============================================================
    // REGISTERED MOBILE NUMBER
    // ============================================================

    /**
     * This variable stores the mobile number successfully
     * registered in Test 1.
     *
     * The SAME mobile number is then used in:
     *
     * Test 1 → Registration
     * Test 2 → User Login
     * Test 3 → Admin Distributor Search
     *
     * static is used because TestNG executes the test methods
     * separately while we need to retain the value.
     */
    private static String registeredMobileNumber;


    // ============================================================
    // CONSTANTS
    // ============================================================

    /**
     * Excel sheet name.
     */
    private static final String SHEET_NAME = "Sheet1";


    /**
     * Default explicit wait time.
     */
    private static final int WAIT_TIME = 20;


    // ============================================================
    // COMMON METHOD
    // CREATE CHROME DRIVER
    // ============================================================

    /**
     * This method creates and returns a ChromeDriver.
     *
     * Keeping browser creation in one reusable method avoids
     * duplicate Chrome configuration in every test.
     */
    private WebDriver createChromeDriver() {

        // --------------------------------------------------------
        // Setup ChromeDriver automatically using WebDriverManager
        // --------------------------------------------------------

        WebDriverManager.chromedriver().setup();


        // --------------------------------------------------------
        // Create ChromeOptions object
        // --------------------------------------------------------

        ChromeOptions options = new ChromeOptions();


        // --------------------------------------------------------
        // Chrome browser configuration
        // --------------------------------------------------------

        // Disable GPU related issues.
        options.addArguments("--disable-gpu");

        // Set browser window size.
        options.addArguments("--window-size=1920,1080");

        // Disable browser notifications.
        options.addArguments("--disable-notifications");

        // Disable popup blocking.
        options.addArguments("--disable-popup-blocking");

        // Disable Chrome information bars.
        options.addArguments("--disable-infobars");

        /*
         * NOTE:
         *
         * --headless=new is intentionally NOT added because
         * your GoldBharath application was working correctly
         * without headless mode.
         */


        // --------------------------------------------------------
        // Create ChromeDriver
        // --------------------------------------------------------

        WebDriver webDriver = new ChromeDriver(options);


        // --------------------------------------------------------
        // Maximize browser
        // --------------------------------------------------------

        webDriver.manage().window().maximize();


        // --------------------------------------------------------
        // Set implicit wait
        // --------------------------------------------------------

        webDriver.manage()
                  .timeouts()
                  .implicitlyWait(Duration.ofSeconds(5));


        // --------------------------------------------------------
        // Return driver
        // --------------------------------------------------------

        return webDriver;
    }


    // ============================================================
    // COMMON METHOD
    // WAIT FOR PAGE TO LOAD
    // ============================================================

    /**
     * Waits until the browser document is completely loaded.
     *
     * This is better than blindly using Thread.sleep().
     */
    private void waitForPageLoad() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));

        wait.until(webDriver ->
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }


    // ============================================================
    // COMMON METHOD
    // CLICK REGISTER BUTTON
    // ============================================================

    /**
     * Waits for Register button and clicks it.
     */
    private void clickRegisterButton() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));

        By registerButton =
                By.xpath("//button[normalize-space()='Register']");

        wait.until(
                ExpectedConditions.elementToBeClickable(registerButton)
        ).click();
    }


    // ============================================================
    // DATA PROVIDER
    // READ MOBILE NUMBERS FROM EXCEL
    // ============================================================

    /**
     * Reads mobile numbers from Excel.
     *
     * Excel row index:
     *
     * 10 → mobile number
     * 11 → mobile number
     * ...
     * 29 → mobile number
     *
     * Column:
     *
     * 1 → Mobile Number
     *
     * IMPORTANT:
     *
     * Apache POI row/column indexes are normally zero based.
     */
    @DataProvider(name = "mobileNumbers")
    public Object[][] mobileNumbers() throws Exception {

        // --------------------------------------------------------
        // Create list to store mobile numbers
        // --------------------------------------------------------

        List<String> mobileNumbers = new ArrayList<>();


        // --------------------------------------------------------
        // Read mobile numbers from Excel
        // --------------------------------------------------------

        for (int row = 10; row <= 29; row++) {

            // Read mobile number from Excel.
            String mobileNumber =
                    eUtil.readDataFromExcel(
                            SHEET_NAME,
                            row,
                            1
                    );


            // ----------------------------------------------------
            // Validate mobile number
            // ----------------------------------------------------

            if (mobileNumber != null &&
                    !mobileNumber.trim().isEmpty()) {

                // Remove unwanted spaces.
                mobileNumber = mobileNumber.trim();

                // Add mobile number to list.
                mobileNumbers.add(mobileNumber);

                // Print Excel data in console.
                System.out.println(
                        "Excel Row " + row +
                        " : " + mobileNumber
                );
            }
        }


        // --------------------------------------------------------
        // Validate Excel data
        // --------------------------------------------------------

        if (mobileNumbers.isEmpty()) {

            throw new Exception(
                    "No mobile numbers found in Excel rows 10 to 29."
            );
        }


        // --------------------------------------------------------
        // Return DataProvider data
        // --------------------------------------------------------

        return new Object[][]{
                {mobileNumbers}
        };
    }


    // ============================================================
    // TEST 1
    // USER REGISTRATION
    // ============================================================

    /**
     * TEST 1:
     *
     * Register a new GoldBharath user.
     *
     * After successful registration, the mobile number returned
     * by RegisterPage is stored in registeredMobileNumber.
     *
     * This SAME number will be used in the next tests.
     */
    @Test(
            priority = 1,
            dataProvider = "mobileNumbers"
    )
    public void RegisteringToGoldbharathTest(
            List<String> mobileNumbers) throws Exception {


        System.out.println(
                "\n=================================================="
        );

        System.out.println(
                "TEST 1 : GOLDBHARATH USER REGISTRATION"
        );

        System.out.println(
                "=================================================="
        );


        // ========================================================
        // STEP 1 : READ REGISTRATION DATA FROM EXCEL
        // ========================================================

        String businessName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        1,
                        1
                );


        String personName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        2,
                        1
                );


        String email =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        3,
                        1
                );


        String date =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        5,
                        1
                );


        String pincode =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        6,
                        1
                );


        String address =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        7,
                        1
                );


        // ========================================================
        // STEP 2 : READ USER URL
        // ========================================================

        String userUrl =
                pUtil.readDataFromPropertyFile("userUrl");


        // ========================================================
        // STEP 3 : START CHROME BROWSER
        // ========================================================

        driver = createChromeDriver();


        try {

            // ====================================================
            // STEP 4 : OPEN USER APPLICATION
            // ====================================================

            driver.get(userUrl);

            // Wait until page is loaded.
            waitForPageLoad();


            // ====================================================
            // STEP 5 : CLICK REGISTER
            // ====================================================

            clickRegisterButton();


            // ====================================================
            // STEP 6 : CREATE REGISTER PAGE OBJECT
            // ====================================================

            RegisterPage registerPage = new RegisterPage(driver);


            // ====================================================
            // STEP 7 : REGISTER USER
            // ====================================================

            /*
             * registerPage method should:
             *
             * 1. Select a mobile number
             * 2. Enter registration details
             * 3. Submit registration
             * 4. Handle duplicate number if required
             * 5. Return successfully registered mobile number
             */

            registeredMobileNumber =
                    registerPage.registeringToGoldbharathApplication(
                            driver,
                            businessName,
                            personName,
                            email,
                            mobileNumbers,
                            date,
                            pincode,
                            address
                    );


            // ====================================================
            // STEP 8 : VALIDATE REGISTERED MOBILE NUMBER
            // ====================================================

            Assert.assertNotNull(
                    registeredMobileNumber,
                    "Registration failed - mobile number is NULL."
            );


            Assert.assertFalse(
                    registeredMobileNumber.trim().isEmpty(),
                    "Registration failed - mobile number is EMPTY."
            );


            // ====================================================
            // STEP 9 : PRINT REGISTERED MOBILE
            // ====================================================

            System.out.println(
                    "--------------------------------------------------"
            );

            System.out.println(
                    "REGISTRATION SUCCESSFUL"
            );

            System.out.println(
                    "REGISTERED MOBILE : "
                            + registeredMobileNumber
            );

            System.out.println(
                    "--------------------------------------------------"
            );


        } finally {

            // ====================================================
            // STEP 10 : CLOSE BROWSER
            // ====================================================

            if (driver != null) {
                driver.quit();
            }
        }
    }


    // ============================================================
    // TEST 2
    // LOGIN + KYC
    // ============================================================

    /**
     * TEST 2:
     *
     * Login using the mobile number generated in Test 1.
     *
     * Then complete Proprietor KYC.
     *
     * This test depends on successful registration.
     */
    @Test(
            priority = 2,
            dependsOnMethods = "RegisteringToGoldbharathTest"
    )
    public void loginUpdatingKYC() throws Exception {


        System.out.println(
                "\n=================================================="
        );

        System.out.println(
                "TEST 2 : LOGIN + PROPRIETOR KYC"
        );

        System.out.println(
                "=================================================="
        );


        // ========================================================
        // STEP 1 : VALIDATE REGISTERED MOBILE
        // ========================================================

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


        // ========================================================
        // STEP 2 : READ KYC DATA FROM EXCEL
        // ========================================================

        String proprietorName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        34,
                        2
                );


        String proprietorPan =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        35,
                        2
                );


        String gstNumber =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        36,
                        2
                );


        String bankName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        37,
                        2
                );


        String accountNumber =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        38,
                        2
                );


        String ifscCode =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        39,
                        2
                );


        String accountHolderName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        40,
                        2
                );


        // ========================================================
        // STEP 3 : READ USER URL
        // ========================================================

        String userUrl =
                pUtil.readDataFromPropertyFile("userUrl");


        // ========================================================
        // STEP 4 : START CHROME
        // ========================================================

        driver = createChromeDriver();


        try {

            // ====================================================
            // STEP 5 : OPEN USER APPLICATION
            // ====================================================

            driver.get(userUrl);

            waitForPageLoad();


            // ====================================================
            // STEP 6 : CLICK LOGIN LINK
            // ====================================================

            WelcomePage welcomePage =
                    new WelcomePage(driver);

            welcomePage.clickOnLoginLink();


            // ====================================================
            // STEP 7 : LOGIN USING REGISTERED MOBILE
            // ====================================================

            LoginPage loginPage =
                    new LoginPage(driver);

            loginPage.LoginToApplication(
                    driver,
                    registeredMobileNumber
            );


            // ====================================================
            // STEP 8 : WAIT FOR DASHBOARD
            // ====================================================

            waitForPageLoad();


            // ====================================================
            // STEP 9 : OPEN USER PROFILE
            // ====================================================

            DashboardPage dashboardPage =
                    new DashboardPage(driver);

            /*
             * IMPORTANT:
             *
             * Keep the existing method according to your
             * DashboardPage implementation.
             *
             * If this method only opens the profile menu,
             * the method name should ideally be renamed to:
             *
             * clickOnUserProfileImage()
             *
             * because "Logout" is misleading here.
             */

            dashboardPage
                    .clickOnUserProfileImageAndLogoutLink(driver);


            // ====================================================
            // STEP 10 : OPEN KYC PAGE
            // ====================================================

            UserProfilePage userProfilePage =
                    new UserProfilePage(driver);

            userProfilePage.clickOnKycLink();


            // ====================================================
            // STEP 11 : CREATE KYC PAGE OBJECT
            // ====================================================

            KycAsProprietorPage kycPage =
                    new KycAsProprietorPage(driver);


            // ====================================================
            // STEP 12 : COMPLETE PROPRIETOR KYC
            // ====================================================

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


            // ====================================================
            // STEP 13 : SUCCESS MESSAGE
            // ====================================================

            System.out.println(
                    "--------------------------------------------------"
            );

            System.out.println(
                    "KYC COMPLETED SUCCESSFULLY"
            );

            System.out.println(
                    "MOBILE : "
                            + registeredMobileNumber
            );

            System.out.println(
                    "--------------------------------------------------"
            );


        } finally {

            // ====================================================
            // STEP 14 : CLOSE USER BROWSER
            // ====================================================

            if (driver != null) {
                driver.quit();
            }
        }
    }


    // ============================================================
    // TEST 3
    // ADMIN DISTRIBUTOR FLOW
    // ============================================================

    /**
     * TEST 3:
     *
     * 1. Login to Admin
     * 2. Open All Distributors
     * 3. Search registered distributor
     * 4. Accept KYC
     * 5. Edit distributor
     * 6. Give Gold Limit
     * 7. Give Silver Limit
     *
     * This test depends on successful KYC completion.
     */
    @Test(
            priority = 3,
            dependsOnMethods = "loginUpdatingKYC"
    )
    public void loginToAdminApproveKycAndGivingGoldAndSilverLimit()
            throws Exception {


        System.out.println(
                "\n=================================================="
        );

        System.out.println(
                "TEST 3 : ADMIN DISTRIBUTOR FLOW"
        );

        System.out.println(
                "=================================================="
        );


        // ========================================================
        // STEP 1 : VALIDATE REGISTERED MOBILE
        // ========================================================

        if (registeredMobileNumber == null ||
                registeredMobileNumber.trim().isEmpty()) {

            throw new Exception(
                    "Registered mobile number is not available."
            );
        }


        // ========================================================
        // STEP 2 : READ ADMIN LOGIN DATA
        // ========================================================

        String adminUrl =
                pUtil.readDataFromPropertyFile("adminurl");


        String username =
                pUtil.readDataFromPropertyFile("adminusername");


        String password =
                pUtil.readDataFromPropertyFile("adminpassword");


        // ========================================================
        // STEP 3 : READ OWNER / KYC DETAILS
        // ========================================================

        String ownerName =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        34,
                        2
                );


//        String proprietorName =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        34,
//                        2
//                );
//
//
//        String proprietorPan =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        35,
//                        2
//                );
//
//
//        String gstNumber =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        36,
//                        2
//                );
//
//
//        String bankName =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        37,
//                        2
//                );
//
//
//        String accountNumber =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        38,
//                        2
//                );
//
//
//        String ifscCode =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        39,
//                        2
//                );
//
//
//        String accountHolderName =
//                eUtil.readDataFromExcel(
//                        SHEET_NAME,
//                        40,
//                        2
//                );


        // ========================================================
        // STEP 4 : READ GOLD AND SILVER LIMIT
        // ========================================================

        /*
         * Current Excel mapping from your uploaded code:
         *
         * Row 48 -> Gold Limit
         * Row 49 -> Silver Limit
         *
         * Column 1 is used for these values.
         */

        String goldLimit =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        48,
                        1
                );


        String silverLimit =
                eUtil.readDataFromExcel(
                        SHEET_NAME,
                        49,
                        1
                );


        // ========================================================
        // STEP 5 : VALIDATE GOLD LIMIT
        // ========================================================

        Assert.assertNotNull(
                goldLimit,
                "Gold Limit is NULL in Excel."
        );


        Assert.assertFalse(
                goldLimit.trim().isEmpty(),
                "Gold Limit is EMPTY in Excel."
        );


        // ========================================================
        // STEP 6 : VALIDATE SILVER LIMIT
        // ========================================================

        Assert.assertNotNull(
                silverLimit,
                "Silver Limit is NULL in Excel."
        );


        Assert.assertFalse(
                silverLimit.trim().isEmpty(),
                "Silver Limit is EMPTY in Excel."
        );


        // ========================================================
        // STEP 7 : PRINT TEST DATA
        // ========================================================

        System.out.println(
                "REGISTERED MOBILE : "
                        + registeredMobileNumber
        );

        System.out.println(
                "OWNER NAME        : "
                        + ownerName
        );

        System.out.println(
                "GOLD LIMIT        : "
                        + goldLimit
        );

        System.out.println(
                "SILVER LIMIT      : "
                        + silverLimit
        );


        // ========================================================
        // STEP 8 : START ADMIN CHROME
        // ========================================================

        driver = createChromeDriver();


        try {

            // ====================================================
            // STEP 9 : OPEN ADMIN APPLICATION
            // ====================================================

            driver.get(adminUrl);

            waitForPageLoad();


            // ====================================================
            // STEP 10 : ADMIN LOGIN
            // ====================================================

            adminObjectRepository.LoginPage adminLoginPage =
                    new adminObjectRepository.LoginPage(driver);


            adminLoginPage.loginToAdmin(
                    username,
                    password
            );


            // ====================================================
            // STEP 11 : WAIT FOR ADMIN DASHBOARD
            // ====================================================

            waitForPageLoad();


            // ====================================================
            // STEP 12 : OPEN ALL DISTRIBUTORS
            // ====================================================

            adminObjectRepository.DashboardPage
                    adminDashboardPage =
                    new adminObjectRepository.DashboardPage(driver);


            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(WAIT_TIME)
                    );


            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            adminDashboardPage.getAllDistributorLnk()
                    )
            ).click();


            // ====================================================
            // STEP 13 : CREATE MANAGE DISTRIBUTOR PAGE
            // ====================================================

            ManageDistributorPage manageDistributorPage =
                    new ManageDistributorPage(driver);


            // ====================================================
            // STEP 14 : FIND RECENTLY REGISTERED DISTRIBUTOR
            // ====================================================

            /*
             * Search using:
             *
             * Registered Mobile Number
             * Owner Name
             */

            manageDistributorPage
                    .clickOnRecentlyRegisteredAndKycCompletedDistributor(
                            driver,
                            registeredMobileNumber,
                            ownerName
                    );


            // ====================================================
            // STEP 15 : ACCEPT DISTRIBUTOR KYC
            // ====================================================

            DistributorDetailsPage distributorDetailsPage =
                    new DistributorDetailsPage(driver);


            distributorDetailsPage
                    .acceptingDistributorKycDetails();


            // ====================================================
            // STEP 16 : OPEN EDIT DISTRIBUTOR
            // ====================================================

            manageDistributorPage
                    .clickOnRecentlyRegisteredDistributorEditButton(
                            driver,
                            registeredMobileNumber
                    );


            // ====================================================
            // STEP 17 : CREATE BUSINESS INFORMATION PAGE
            // ====================================================

            BusinessInformationPage
                    businessInformationPage =
                    new BusinessInformationPage(driver);


            // ====================================================
            // STEP 18 : EDIT DETAILS
            // AND GIVE GOLD/SILVER LIMIT
            // ====================================================

            businessInformationPage
                    .editingAndGivingSilverAndGoldLimit(
                            silverLimit,
                            goldLimit
                    );


            // ====================================================
            // STEP 19 : FINAL SUCCESS
            // ====================================================

            System.out.println(
                    "\n=================================================="
            );

            System.out.println(
                    "GOLDBHARATH E2E FLOW COMPLETED SUCCESSFULLY"
            );

            System.out.println(
                    "=================================================="
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
                    "=================================================="
            );

            // ====================================================
            // STEP 20 : Logout Of Application
            // ====================================================
            
            Thread.sleep(2000);
            LogoutPage loPage = new LogoutPage(driver);
            loPage.logoutOfApplication();
            

        } finally {

            // ====================================================
            // STEP 20 : CLOSE ADMIN BROWSER
            // ====================================================

            if (driver != null) {
                driver.quit();
            }
        }
    }
    
        	
        	// ============================================================
        	// TEST 4
        	// DISTRIBUTOR GOLD BOOKING
        	// ============================================================

        	/*
        	 * FIXED: This test no longer hardcodes registeredMobileNumber.
        	 * It now depends on the full chain (Test 1 -> Test 2 -> Test 3),
        	 * so it reuses the SAME mobile number that Test 1 actually
        	 * registered and that Tests 2/3 already used for login/KYC/admin
        	 * approval. This keeps the whole flow consistent end-to-end
        	 * instead of booking gold for a distributor that was never
        	 * created/approved in this run.
        	 */
        	@Test(priority = 4, dependsOnMethods = "loginToAdminApproveKycAndGivingGoldAndSilverLimit")
        	public void distributorBookingGoldTest() throws Exception {

        	    // ========================================================
        	    // STEP 1 : PRINT TEST NAME
        	    // ========================================================

        	    System.out.println("\n==================================================");
        	    System.out.println("TEST 4 : DISTRIBUTOR GOLD BOOKING");
        	    System.out.println("==================================================");

        	    // ========================================================
        	    // STEP 2 : VALIDATE REGISTERED MOBILE NUMBER
        	    // ========================================================

        	    /*
        	     * registeredMobileNumber is already set by
        	     * RegisteringToGoldbharathTest (Test 1) and carried
        	     * through Tests 2 and 3 via the static field.
        	     * No need to reassign it here.
        	     */
        	    if (registeredMobileNumber == null || registeredMobileNumber.trim().isEmpty()) {
        	        throw new Exception("Registered mobile number is not available. Earlier tests in the chain may have failed.");
        	    }

        	    // ========================================================
        	    // STEP 3 : PRINT REGISTERED MOBILE NUMBER
        	    // ========================================================

        	    System.out.println("REGISTERED MOBILE NUMBER : " + registeredMobileNumber);

        	    // ========================================================
        	    // STEP 4 : READ USER URL
        	    // ========================================================

        	    String userUrl = pUtil.readDataFromPropertyFile("userUrl");

        	    // ========================================================
        	    // STEP 5 : START CHROME BROWSER
        	    // ========================================================

        	    driver = createChromeDriver();

        	    try {

        	        // ====================================================
        	        // STEP 6 : OPEN GOLDBHARATH APPLICATION
        	        // ====================================================

        	        driver.get(userUrl);
        	        waitForPageLoad();

        	        // ====================================================
        	        // STEP 7 : OPEN LOGIN PAGE
        	        // ====================================================

        	        WelcomePage welcomePage = new WelcomePage(driver);
        	        welcomePage.clickOnLoginLink();

        	        // ====================================================
        	        // STEP 8 : LOGIN USING THE REGISTERED MOBILE NUMBER
        	        // ====================================================

        	        LoginPage loginPage = new LoginPage(driver);
        	        loginPage.LoginToApplication(driver, registeredMobileNumber);

        	        // ====================================================
        	        // STEP 9 : WAIT FOR DASHBOARD
        	        // ====================================================

        	        waitForPageLoad();

        	        // ====================================================
        	        // STEP 10 : PRINT LOGIN SUCCESS
        	        // ====================================================

        	        System.out.println("Distributor Login Successful");
        	        System.out.println("Mobile : " + registeredMobileNumber);

        	        // ====================================================
        	        // STEP 11 : GOLD BOOKING FLOW
        	        // ====================================================

        	        DashboardPage dbPage = new DashboardPage(driver);
        	        dbPage.clickOnBookGoldBtn(driver);

        	        Thread.sleep(2000);

        	        BookGoldPage bgPage = new BookGoldPage(driver);
        	        bgPage.fetchingGoldLivePriceAndBookingGold(driver);

        	        // ====================================================
        	        // STEP 12 : SUCCESS MESSAGE
        	        // ====================================================

        	        System.out.println("\n==================================================");
        	        System.out.println("GOLD BOOKING COMPLETED SUCCESSFULLY");
        	        System.out.println("REGISTERED MOBILE : " + registeredMobileNumber);
        	        System.out.println("==================================================");

        	    } finally {

        	        // ====================================================
        	        // STEP 13 : CLOSE BROWSER
        	        // ====================================================

        	      if (driver != null) {
        	          driver.quit();
        	      }
        	 
        	    }}
   
    
        	
        	@Test(priority = 5, dependsOnMethods = "distributorBookingGoldTest")
        	public void distributorBuyingGoldTest() throws Exception 
        	{
        	    // ========================================================
        	    // STEP 1 : PRINT TEST NAME
        	    // ========================================================

        	    System.out.println("\n==================================================");
        	    System.out.println("TEST 4 : DISTRIBUTOR GOLD BOOKING");
        	    System.out.println("==================================================");

        	    // ========================================================
        	    // STEP 2 : VALIDATE REGISTERED MOBILE NUMBER
        	    // ========================================================

        	    /*
        	     * registeredMobileNumber is already set by
        	     * RegisteringToGoldbharathTest (Test 1) and carried
        	     * through Tests 2 and 3 via the static field.
        	     * No need to reassign it here.
        	     */
        	    if (registeredMobileNumber == null || registeredMobileNumber.trim().isEmpty()) {
        	        throw new Exception("Registered mobile number is not available. Earlier tests in the chain may have failed.");
        	    }

        	    // ========================================================
        	    // STEP 3 : PRINT REGISTERED MOBILE NUMBER
        	    // ========================================================

        	    System.out.println("REGISTERED MOBILE NUMBER : " + registeredMobileNumber);

        	    // ========================================================
        	    // STEP 4 : READ USER URL
        	    // ========================================================

        	    String userUrl = pUtil.readDataFromPropertyFile("userUrl");

        	    // ========================================================
        	    // STEP 5 : START CHROME BROWSER
        	    // ========================================================

        	    driver = createChromeDriver();

        	    try {

        	        // ====================================================
        	        // STEP 6 : OPEN GOLDBHARATH APPLICATION
        	        // ====================================================

        	        driver.get(userUrl);
        	        waitForPageLoad();

        	        // ====================================================
        	        // STEP 7 : OPEN LOGIN PAGE
        	        // ====================================================

        	        WelcomePage welcomePage = new WelcomePage(driver);
        	        welcomePage.clickOnLoginLink();

        	        // ====================================================
        	        // STEP 8 : LOGIN USING THE REGISTERED MOBILE NUMBER
        	        // ====================================================

        	        LoginPage loginPage = new LoginPage(driver);
        	        loginPage.LoginToApplication(driver, registeredMobileNumber);

        	        // ====================================================
        	        // STEP 9 : WAIT FOR DASHBOARD
        	        // ====================================================

        	        waitForPageLoad();

        	        // ====================================================
        	        // STEP 10 : PRINT LOGIN SUCCESS
        	        // ====================================================

        	        System.out.println("Distributor Login Successful");
        	        System.out.println("Mobile : " + registeredMobileNumber);

        	        // ====================================================
        	        // STEP 11 : GOLD BOOKING FLOW
        	        // ====================================================

        	        DashboardPage dbPage = new DashboardPage(driver);
        	        dbPage.clickOnBuyGoldBtn(driver);
        	        Thread.sleep(2000);

        	        whatWouldYouLikeToBuyPage wPage = new whatWouldYouLikeToBuyPage(driver);
        	        wPage.getGoldCoinsEle().click();
        	        Thread.sleep(2000);
        	        
        	        BuildYourGoldCoinCombinationPage bPage = new BuildYourGoldCoinCombinationPage(driver);
        	        bPage.increaseGoldGramsAccToWeight(driver, "1", 1);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "2", 2);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "5", 1);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "10", 2);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "20", 1);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "50", 1);
        	        Thread.sleep(2000);
        	        bPage.increaseGoldGramsAccToWeight(driver, "100", 1);
        	        Thread.sleep(2000);
                    bPage.getContinueBtn().click();
                    Thread.sleep(2000);
                    
                    ReviewYourGoldOrderPage rPage = new ReviewYourGoldOrderPage(driver);
                    rPage.takeAllDetailsAndClickProceedBtn(driver);
                    Thread.sleep(2000);
        	        
                    GoldCoinsHistoryPage gPage = new GoldCoinsHistoryPage(driver);
                    gPage.getTotalGoldHistoryWhatWeBuyed();
                    
        	        // ====================================================
        	        // STEP 12 : SUCCESS MESSAGE
        	        // ====================================================

        	        System.out.println("\n==================================================");
        	        System.out.println("GOLD BUYING COMPLETED SUCCESSFULLY");
        	        System.out.println("REGISTERED MOBILE : " + registeredMobileNumber);
        	        System.out.println("==================================================");

        	    } finally {

        	        // ====================================================
        	        // STEP 13 : CLOSE BROWSER
        	        // ====================================================

//        	      if (driver != null) {
//        	          driver.quit();
//        	      }
    

        	    }
        	}
    
   
        	
}