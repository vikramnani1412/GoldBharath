package genericUtilities;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry analyzer for TestNG.
 *
 * When a @Test fails, TestNG calls retry() to ask whether it should be
 * re-run. Returning true re-runs the test; returning false marks it as
 * permanently failed.
 *
 * With retryCount = 3, a failing test will run up to 4 times total:
 * 1 original attempt + up to 3 retries.
 *
 * @author vikram
 */
public class RetryAnalyzer implements IRetryAnalyzer {

	private final AtomicInteger count = new AtomicInteger(0);
	private static final int RETRY_COUNT = 3;

	@Override
	public boolean retry(ITestResult result) {

		if (count.get() < RETRY_COUNT) {

			int attempt = count.incrementAndGet();

			System.out.println(
					"Retrying test '" + result.getName()
							+ "' - attempt " + attempt + " of " + RETRY_COUNT);

			return true;
		}

		return false;
	}
}
