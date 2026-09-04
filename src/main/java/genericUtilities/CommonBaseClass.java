package genericUtilities;

import org.openqa.selenium.WebDriver;

/**
 * Common Base Class for all modules.
 * Provides ThreadLocal WebDriver support for parallel execution.
 *
 * @author Vikram
 */
public class CommonBaseClass {

    // ThreadLocal WebDriver (Parallel Execution Support)
    protected static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();

    /**
     * Sets the driver for the current thread.
     *
     * @param driver WebDriver instance
     */
    public static void setDriver(WebDriver driver) {
        tDriver.set(driver);
    }

    /**
     * Returns the WebDriver of the current thread.
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return tDriver.get();
    }

    /**
     * Removes the driver from ThreadLocal memory.
     * Call this after quitting the browser.
     */
    public static void unloadDriver() {
        tDriver.remove();
    }
}