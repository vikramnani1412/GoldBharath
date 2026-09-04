package genericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains all Generic methods related to WebDriver Actions.
 * @author vikram
 *
 */
public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize the window
	 * @param driver
	 */
	public void minimizeTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will wait until webpage loaded
	 * @param driver
	 */
	public void waitUntilPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	/**
	 * This method will wait until Element Visible
	 * @param driver
	 */
	public void waitUntilElementVisibleUptoThirtyMin(WebDriver driver, WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(30));
	    wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait until alert is present
	 * @param driver
	 */
	public void waitUntilAlertIsPresent(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * This method will handle dropdown by its index
	 * @param element
	 * @param index
	 */
	public void handleDropdownByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	/**
	 * This method will handle dropdown by its value
	 * @param element
	 * @param value
	 */
	public void handleDropdownByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	/**
	 * This method will select two values on a multi-select dropdown element.
	 * NOTE: this only works on a true HTML multi-select (<select multiple>).
	 * On a normal single-select dropdown, the second selectByValue call will
	 * simply override the first.
	 * @param element
	 * @param value
	 * @param value2
	 */
	public void handleDropdownBy2Values(WebElement element, String value, String value2) {
		Select s = new Select(element);
		s.selectByValue(value);
		s.selectByValue(value2);
	}

	/**
	 * This method will handle dropdown by its visibleText
	 * @param text
	 * @param element
	 */
	public void handleDropdownByVisibleText(String text, WebElement element) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	/**
	 * This method will perform mouse hover actions on a web element
	 * @param driver
	 * @param element
	 */
	public void mouseHoverAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	/**
	 * This method will perform right click action anywhere on the web page
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}

	/**
	 * This method will perform right click operation on a particular element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	/**
	 * This method will perform double click operation on anywhere on the web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}

	/**
	 * This method will perform double click operation on a particular web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

	/**
	 * This method will perform drag and drop operation
	 * @param driver
	 * @param srcEle
	 * @param dstEle
	 */
	public void dragAndDrop(WebDriver driver, WebElement srcEle, WebElement dstEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(srcEle, dstEle).perform();
	}

	/**
	 * This method will press and release enter key.
	 * NOTE: java.awt.Robot operates at OS level, requires window focus, and
	 * does not work in headless browser mode. Prefer element.sendKeys(Keys.ENTER)
	 * where possible.
	 * @throws AWTException
	 */
	public void pressEnterKey() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will press and release Backspace key.
	 * NOTE: java.awt.Robot operates at OS level, requires window focus, and
	 * does not work in headless browser mode. Prefer element.sendKeys(Keys.ENTER)
	 * where possible.
	 * @throws AWTException
	 * @throws Exception 
	 */
	public void pressBackspaceKeyThreeTimes() throws Exception {
		Robot r = new Robot();
		for(int i=1;i<=3;i++)
		{
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_BACK_SPACE);
			r.keyRelease(KeyEvent.VK_BACK_SPACE);
		}
	}

	/**
	 * This method will handle frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method will handle frame based on name or id
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * This method will handle frame based on web element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method will switch control to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	/**
	 * This method will switch control to default frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method will dismiss alert popup
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method will capture the alert text
	 * @param driver
	 * @return alert text
	 */
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	/**
	 * This method will take screenshot and store it in the Screenshots folder.
	 * Creates the folder automatically if it does not already exist.
	 * @param driver
	 * @param methodName
	 * @return absolute path of the saved screenshot
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver, String methodName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sshotType = ts.getScreenshotAs(OutputType.FILE);

		File folder = new File(".\\Screenshots\\");
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File storingFolder = new File(folder, methodName + ".png");
		FileUtils.copyFile(sshotType, storingFolder);

		return storingFolder.getAbsolutePath();
	}

	/**
	 * This method will take a screenshot of the page, intended for capturing
	 * a modal popup that is already open and visible.
	 * @param driver
	 * @param modalPopupElement the already-visible modal element (used to
	 *                           confirm visibility before capturing)
	 * @return screenshot bytes
	 */
	public byte[] takeModelPopupScreenshot(WebDriver driver, WebElement modalPopupElement) {

		// Ensure the passed-in modal element is actually visible before capturing
		waitForElementToBeVisible(driver, modalPopupElement);

		TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
		byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);

		try {
			java.nio.file.Files.write(
					java.nio.file.Paths.get("modal_popup_screenshot.png"), screenshot);
		} catch (IOException e) {
			System.out.println("Error saving modal popup screenshot: " + e.getMessage());
		}

		return screenshot;
	}

	/**
	 * This method will switch to a window based on a partial window title match
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWinTitle) {
		Set<String> windIds = driver.getWindowHandles();
		for (String windowId : windIds) {
			String currentTitle = driver.switchTo().window(windowId).getTitle();
			if (currentTitle.contains(partialWinTitle)) {
				break;
			}
		}
	}

	/**
	 * This method will scroll the page horizontally by the element's
	 * horizontal (X) offset.
	 * @param driver
	 * @param element
	 */
	public void scrollActionx(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int x = element.getLocation().getX();
		jse.executeScript("window.scrollBy(" + x + ",0)");
	}

	/**
	 * This method will scroll the page vertically by the element's
	 * vertical (Y) offset.
	 * @param driver
	 * @param element
	 */
	public void scrollActiony(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		jse.executeScript("window.scrollBy(0," + y + ")");
	}

	/**
	 * This method will wait until clicking the element
	 * vertical (Y) offset.
	 * @param driver
	 * @param element
	 */
	public void waitUntilElementToBeClickableUsingJavaScriptExecutor(WebDriver driver, WebElement element) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    // Wait until element is visible
	    wait.until(ExpectedConditions.visibilityOf(element));

	    // Scroll element to center of the screen
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
	            element
	    );

	    // Wait until element is clickable
	    wait.until(ExpectedConditions.elementToBeClickable(element));

	    // Small wait for UI/Angular rendering
	    try {
	        Thread.sleep(500);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }

	    // Click the element
	    element.click();
	}
	
	/**
	 * This method will return current date formatted as dd/MM/yyyy
	 * @return formatted date string
	 */
	public String calendar() {
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(d);
	}

	/**
	 * This method will return a date offset by addDay days from today,
	 * formatted as dd/MM/yyyy
	 * @param addDay number of days to add (can be negative)
	 * @return formatted date string
	 */
	public String calendarDay(int addDay) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, addDay);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(d);
	}

	/**
	 * This method will return a date offset by addYear years from today,
	 * formatted as dd/MM/yyyy
	 * @param addYear number of years to add (can be negative)
	 * @return formatted date string
	 */
	public String calendarYear(int addYear) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, addYear);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(d);
	}

	/**
	 * NOTE: this currently only performs a mouse hover, not an actual logout.
	 * Replace the body with your app's real logout sequence (e.g. click
	 * profile icon -> click logout link -> confirm), or remove this method
	 * and use your page-object logout flow directly.
	 * @param driver
	 * @param element
	 */
	public void logOutAction(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void clickOnParticularWebElementUsingActionsClass(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}

	/**
	 * This method is used to upload a file via a file input element
	 * @param driver
	 * @param element the file input element
	 * @param filePath absolute path of the file to upload
	 */
	public void clickOnChooseFileOption(WebDriver driver, WebElement element, String filePath) {
		element.sendKeys(filePath);
	}

	/**
	 * NOTE: relies on java.awt.Robot (OS-level), requires window focus and
	 * does not work in headless mode. See scrollToParticularWebElement or
	 * scrollPageByPixels for a JS-based alternative.
	 * @param noOfTimes
	 * @throws Exception
	 */
	public void scrollPageDown(int noOfTimes) throws Exception {
		Robot r = new Robot();
		for (int i = 1; i <= noOfTimes; i++) {
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
		}
	}

	/**
	 * NOTE: relies on java.awt.Robot (OS-level), requires window focus and
	 * does not work in headless mode.
	 * @param noOfTimes
	 * @throws Exception
	 */
	public void scrollPageUp(int noOfTimes) throws Exception {
		Robot r = new Robot();
		for (int i = 1; i <= noOfTimes; i++) {
			r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);
		}
	}

	/**
	 * NOTE: relies on java.awt.Robot (OS-level), requires window focus and
	 * does not work in headless mode. Prefer Actions.sendKeys(Keys.ESCAPE)
	 * where possible.
	 * @throws Exception
	 */
	public void clickOnEscapeButton() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	/**
	 * This method will scroll the page so the given element is in view,
	 * centering it in the viewport
	 * @param driver
	 * @param element
	 */
	public void scrollExactToParticularWebElement(WebDriver driver, WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
	}
	
	/**
	 * Generates a new sample .docx file with unique content each time it's called.
	 * @param folderPath  folder where the file should be created e.g. "C:\\TestData\\"
	 * @param fileNamePrefix  prefix for the file name e.g. "MedicalCertificate"
	 * @return full path of the newly created document
	 */
	public String generateSampleDocument(String folderPath, String fileNamePrefix) throws IOException {
	    String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	    String fileName = fileNamePrefix + "_" + timestamp + ".docx";
	    String filePath = folderPath + fileName;

	    XWPFDocument document = new XWPFDocument();
	    XWPFParagraph paragraph = document.createParagraph();
	    XWPFRun run = paragraph.createRun();
	    run.setText("Sample Medical Certificate generated for automation test");
	    run.addBreak();
	    run.setText("Generated on: " + timestamp);

	    try (FileOutputStream out = new FileOutputStream(filePath)) {
	        document.write(out);
	    }
	    document.close();

	    return filePath;
	}
	
	/**
	 * This method will scroll the page so the given element is in view
	 * @param driver
	 * @param element
	 */
	public void scrollToParticularWebElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method will wait up to 120 seconds for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickableSeconds(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method will wait up to 2 minutes for an element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickableMinutes(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until element is visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait until element is clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait until element is selected
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeSelected(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * Wait until element is invisible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeInvisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Wait for alert popup
	 * @param driver
	 * @return the Alert once present
	 */
	public Alert waitForAlert(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Wait until title contains text
	 * @param driver
	 * @param partialTitle
	 */
	public void waitForTitleContains(WebDriver driver, String partialTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.titleContains(partialTitle));
	}

	/**
	 * Wait until title is exact
	 * @param driver
	 * @param title
	 */
	public void waitForTitleIs(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * Wait until URL contains text
	 * @param driver
	 * @param partialUrl
	 */
	public void waitForUrlContains(WebDriver driver, String partialUrl) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.urlContains(partialUrl));
	}

	/**
	 * Wait until URL is exact
	 * @param driver
	 * @param url
	 */
	public void waitForUrlToBe(WebDriver driver, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.urlToBe(url));
	}

	/**
	 * Wait for frame and switch to it
	 * @param driver
	 * @param frameElement
	 */
	public void waitForFrameAndSwitch(WebDriver driver, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/**
	 * Wait for text to be present in element
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void waitForTextInElement(WebDriver driver, WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * Wait for page to load completely (sets page load timeout)
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(2));
	}

	/**
	 * Implicit Wait
	 * @param driver
	 */
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));
	}

	// ============================================================
	// Additional reusable methods commonly needed in a Selenium
	// framework but missing from the original class
	// ============================================================

	/**
	 * Clicks an element only after waiting for it to be clickable.
	 * Saves having to call waitForElementToBeClickable + element.click()
	 * separately every time.
	 * @param driver
	 * @param element
	 */
	public void waitAndClick(WebDriver driver, WebElement element) {
		waitForElementToBeClickable(driver, element);
		element.click();
	}

	/**
	 * Clears an input field and types the given text, waiting for visibility first.
	 * @param driver
	 * @param element
	 * @param text
	 */
	public void waitAndType(WebDriver driver, WebElement element, String text) {
		waitForElementToBeVisible(driver, element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Returns true if the element is present and displayed, false otherwise
	 * (does not throw if the element is missing).
	 * @param driver
	 * @param locator
	 * @return true if element is displayed
	 */
	public boolean isElementDisplayed(WebDriver driver, By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns true if at least one element matching the locator exists in the DOM.
	 * @param driver
	 * @param locator
	 * @return true if element exists
	 */
	public boolean isElementPresent(WebDriver driver, By locator) {
		return !driver.findElements(locator).isEmpty();
	}

	/**
	 * Gets the visible text of an element, waiting for visibility first.
	 * @param driver
	 * @param element
	 * @return element's visible text
	 */
	public String getElementText(WebDriver driver, WebElement element) {
		waitForElementToBeVisible(driver, element);
		return element.getText();
	}

	/**
	 * Highlights an element with a red border, useful for debugging/demo runs.
	 * @param driver
	 * @param element
	 */
	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red';", element);
	}

	/**
	 * Clicks an element using JavaScript instead of native click.
	 * Useful as a fallback when a normal .click() is blocked by an
	 * overlay or fails due to the element not being "interactable"
	 * by Selenium's standards despite being visible.
	 * @param driver
	 * @param element
	 */
	public void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Scrolls the page by a specific pixel amount, JS-based (no Robot/OS
	 * dependency, works in headless mode unlike scrollPageDown/scrollPageUp).
	 * @param driver
	 * @param pixels positive scrolls down, negative scrolls up
	 */
	public void scrollPageByPixels(WebDriver driver, int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ");");
	}

	/**
	 * Scrolls directly to the bottom of the page.
	 * @param driver
	 */
	public void scrollToBottomOfPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	/**
	 * Scrolls directly to the top of the page.
	 * @param driver
	 */
	public void scrollToTopOfPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
	}

	/**
	 * Opens a new browser tab and switches to it.
	 * @param driver
	 */
	public void openNewTab(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");
		List<String> tabs = new java.util.ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size() - 1));
	}

	/**
	 * Closes the current tab/window and switches back to the original window.
	 * @param driver
	 * @param originalWindowHandle the handle to switch back to after closing
	 */
	public void closeCurrentTabAndSwitchBack(WebDriver driver, String originalWindowHandle) {
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	/**
	 * Refreshes the current page.
	 * @param driver
	 */
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Navigates back in browser history.
	 * @param driver
	 */
	public void navigateBack(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * Navigates forward in browser history.
	 * @param driver
	 */
	public void navigateForward(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * Returns true if a checkbox/radio element is currently selected.
	 * @param element
	 * @return selection state
	 */
	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * Returns the currently selected option's visible text from a dropdown.
	 * @param element the select element
	 * @return selected option text
	 */
	public String getSelectedDropdownText(WebElement element) {
		Select s = new Select(element);
		return s.getFirstSelectedOption().getText();
	}

	/**
	 * Returns all visible option texts from a dropdown.
	 * @param element the select element
	 * @return list of option texts
	 */
	public List<String> getAllDropdownOptions(WebElement element) {
		Select s = new Select(element);
		List<String> options = new java.util.ArrayList<>();
		for (WebElement option : s.getOptions()) {
			options.add(option.getText());
		}
		return options;
	}

	/**
	 * Waits for the number of open windows/tabs to reach the expected count,
	 * useful before switching to a newly opened tab/window.
	 * @param driver
	 * @param expectedNumberOfWindows
	 */
	public void waitForNumberOfWindowsToBe(WebDriver driver, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
	}

	/**
	 * Gets an attribute value from an element (e.g. "value", "class", "href").
	 * @param element
	 * @param attributeName
	 * @return attribute value
	 */
	public String getElementAttribute(WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}
}
