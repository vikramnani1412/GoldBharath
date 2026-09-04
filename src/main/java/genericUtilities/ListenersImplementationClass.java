package genericUtilities;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Common TestNG Listener for Admin and User Pannels.
 *
 * @author Vikram
 */
public class ListenersImplementationClass implements ITestListener {

    private static ExtentReports report;

    private static final Map<Long, ExtentTest> testMap =
            new ConcurrentHashMap<>();

    @Override
    public void onStart(ITestContext context) {

        System.out.println("========== Suite Started : "
                + context.getName() + " ==========");

        if (report == null) {

            synchronized (ListenersImplementationClass.class) {

                if (report == null) {

                    String reportPath = ".\\Extent Reports\\Report-"
                            + new JavaUtility().getSystemDateInFormat()
                            + ".html";

                    ExtentSparkReporter spark =
                            new ExtentSparkReporter(reportPath);

                    spark.config().setDocumentTitle("Gold Bharat Execution Report");
                    spark.config().setReportName("Gold Bharat Automation Report");
                    spark.config().setTheme(Theme.STANDARD);
                    spark.config().setEncoding("utf-8");

                    report = new ExtentReports();
                    report.attachReporter(spark);

                    report.setSystemInfo("Browser",
                            System.getProperty("browser", "Chrome"));

                    report.setSystemInfo("Base URL",
                            System.getProperty("baseUrl", "Admin and User pannels are Involved"));

                    report.setSystemInfo("Environment",
                            System.getProperty("env", "STAGING"));

                    report.setSystemInfo("Platform",
                            System.getProperty("os.name"));

                    report.setSystemInfo("Executed By",
                            "Vikram Gangavarapu");
                }
            }
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        String methodName = result.getMethod().getMethodName();

        ExtentTest test =
                report.createTest(methodName,
                        result.getMethod().getDescription());

        for (String group : result.getMethod().getGroups()) {
            test.assignCategory(group);
        }

        test.assignAuthor(result.getTestClass().getRealClass().getSimpleName());

        test.log(Status.INFO, "Test Execution Started");

        testMap.put(getId(result), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = getTest(result);

        long duration =
                result.getEndMillis() - result.getStartMillis();

        test.log(Status.PASS,
                "Test Passed (" + duration + " ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = getTest(result);

        test.log(Status.FAIL, "Test Failed");

        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        }

        try {

            String screenshotName =
                    result.getMethod().getMethodName()
                            + "_"
                            + new JavaUtility().getSystemDateInFormat();

            String path =
                    new WebDriverUtility().takeScreenShot(
                            CommonBaseClass.getDriver(),
                            screenshotName);

            test.fail("Failure Screenshot",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(path)
                            .build());

        } catch (IOException e) {

            test.log(Status.WARNING,
                    "Unable to capture screenshot : "
                            + e.getMessage());

        } catch (Exception e) {

            test.log(Status.WARNING,
                    e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = getTest(result);

        test.log(Status.SKIP, "Test Skipped");

        if (result.getThrowable() != null) {
            test.skip(result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {

        getTest(result).log(Status.WARNING,
                "Failed but within success percentage.");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("========== Suite Finished : "
                + context.getName() + " ==========");

        if (report != null) {
            report.flush();
        }

        testMap.clear();
    }

    /**
     * Unique ID for parallel execution.
     */
    private long getId(ITestResult result) {

        return result.hashCode()
                + Thread.currentThread().getId() * 31L;
    }

    /**
     * Returns ExtentTest object.
     */
    private ExtentTest getTest(ITestResult result) {

        ExtentTest test = testMap.get(getId(result));

        if (test == null) {

            test =
                    report.createTest(
                            result.getMethod().getMethodName());

            testMap.put(getId(result), test);
        }

        return test;
    }
}