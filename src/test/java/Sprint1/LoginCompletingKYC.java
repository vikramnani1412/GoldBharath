package Sprint1;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.UserBaseClass;
import genericUtilities.WebDriverUtility;
import userObjectRepository.DashboardPage;
import userObjectRepository.KycAsLLpPage;
import userObjectRepository.KycAsLtdPage;
import userObjectRepository.KycAsProprietorPage;
import userObjectRepository.KycAsPvtLtdPage;

public class LoginCompletingKYC extends UserBaseClass {
	
	WebDriverUtility wUtil = new WebDriverUtility();
	ExcelFileUtility eUtil = new ExcelFileUtility();
	PropertyFileUtility pUtil = new PropertyFileUtility();
	JavaUtility jUtil = new JavaUtility();
	
	@Test(priority = 1)
	public void kycTest() throws Exception
	{
		// Proprietor
		
		String PropreitorName = eUtil.readDataFromExcel("Sheet1", 34, 2);
		String PropreitorPan = eUtil.readDataFromExcel("Sheet1", 35, 2);
		String GstNumber = eUtil.readDataFromExcel("Sheet1", 36, 2);
		String BankName = eUtil.readDataFromExcel("Sheet1", 37, 2);
		String AccountNumber = eUtil.readDataFromExcel("Sheet1", 38, 2);
		String IFSCCode = eUtil.readDataFromExcel("Sheet1", 39, 2);
		String AccountHolderName = eUtil.readDataFromExcel("Sheet1", 34, 2);
		
		// LLP
		String DirectorName = eUtil.readDataFromExcel("Sheet1", 34, 5);
		String DirectorMobileNumber = eUtil.readDataFromExcel("Sheet1", 35, 5);
		String DirectorAadharNumber = eUtil.readDataFromExcel("Sheet1", 36, 5);
		String CompanyPanNumber = eUtil.readDataFromExcel("Sheet1", 37, 5);
		String CompanyGstNumber = eUtil.readDataFromExcel("Sheet1", 38, 5);
		String DirectorCINNumber = eUtil.readDataFromExcel("Sheet1", 39, 5);
		String LLPBankName = eUtil.readDataFromExcel("Sheet1", 40, 5);
		String ACNumber = eUtil.readDataFromExcel("Sheet1", 41, 5);
		String LLPIFSCCode = eUtil.readDataFromExcel("Sheet1", 42, 5);
		String LLPAccountHolderName = eUtil.readDataFromExcel("Sheet1", 43, 5);

		// LTD
		String FirstDirectorName = eUtil.readDataFromExcel("Sheet1", 34, 8);
		String LTDDirectorMobileNumber = eUtil.readDataFromExcel("Sheet1", 35, 8);
		String FirstDirectorCompanyAadharNumber = eUtil.readDataFromExcel("Sheet1", 36, 8);
		String FirstDirectorCompanyPANNumber = eUtil.readDataFromExcel("Sheet1", 37, 8);
		String SecondDirectorName = eUtil.readDataFromExcel("Sheet1", 38, 8);
		String CompanyGSTNumber = eUtil.readDataFromExcel("Sheet1", 39, 8);
		String CompanyCINNumber = eUtil.readDataFromExcel("Sheet1", 40, 8);
		String LTDBankName = eUtil.readDataFromExcel("Sheet1", 41, 8);
		String LTdACNumber = eUtil.readDataFromExcel("Sheet1", 42, 8);
		String LTDIFSCCode = eUtil.readDataFromExcel("Sheet1", 43, 8); 
		String LTDAccountHolderName = eUtil.readDataFromExcel("Sheet1", 44, 8);

		

		
		
		Thread.sleep(2000);
		DashboardPage dbPage = new DashboardPage(driver);
		dbPage.clickOnUserProfileImageAndLogoutLink(driver);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[.='KYC']")).click();
		Thread.sleep(2000);
		KycAsProprietorPage kPage = new KycAsProprietorPage(driver);
		kPage.proprietorKyc(driver, PropreitorName, PropreitorPan, GstNumber, BankName, AccountNumber, IFSCCode, AccountHolderName);
		
		KycAsPvtLtdPage pvt = new KycAsPvtLtdPage(driver);
		pvt.PvtLtdKyc(FirstDirectorName, LTDDirectorMobileNumber, FirstDirectorCompanyAadharNumber, FirstDirectorCompanyPANNumber, SecondDirectorName, CompanyGSTNumber, CompanyCINNumber, LTDBankName, LTdACNumber, LTDIFSCCode, LTDAccountHolderName);
		
		KycAsLtdPage ltd = new KycAsLtdPage(driver);
		ltd.LtdKyc(FirstDirectorName, LTDDirectorMobileNumber, FirstDirectorCompanyAadharNumber, FirstDirectorCompanyPANNumber, SecondDirectorName, CompanyGSTNumber, CompanyCINNumber, LTDBankName, LTdACNumber, LTDIFSCCode, LTDAccountHolderName);
	
		KycAsLLpPage llp = new KycAsLLpPage(driver);
		llp.LLpKyc(DirectorName, DirectorMobileNumber, DirectorAadharNumber, CompanyPanNumber, "Gangavarapu V", CompanyGstNumber, DirectorCINNumber, LLPBankName, ACNumber, LLPIFSCCode, LLPAccountHolderName);
		
		
	}
	
	
	
	
	
}
