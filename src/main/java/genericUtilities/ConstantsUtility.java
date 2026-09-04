package genericUtilities;

public interface ConstantsUtility {

	String propertyfilepath=".\\src\\test\\resources\\CommonData.properties";
	String excelfilepath=".\\src\\test\\resources\\OPHC Automation Excel.xlsx";
	String Reports = ".\\src\\test\\resources\\Reports";
	
	
	public static final String SCREENSHOT_PATH =
            System.getProperty("user.dir") + "\\screenshots\\";

    public static final String REPORTS_PATH =
            System.getProperty("user.dir") + "\\reports\\";


    public static final int IMPLICIT_WAIT = 10;

    public static final int PAGE_LOAD_TIMEOUT = 30;
	
}
