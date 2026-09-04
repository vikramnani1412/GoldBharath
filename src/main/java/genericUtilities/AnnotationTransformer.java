package genericUtilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Automatically applies RetryAnalyzer to every @Test in the suite,
 * so individual test methods don't need to declare
 * @Test(retryAnalyzer = RetryAnalyzer.class) by hand.
 *
 * To activate: register this class as a <listener> in testng.xml:
 * <listeners>
 *     <listener class-name="genericUtilities.AnnotationTransformer"/>
 * </listeners>
 *
 * @author vikram
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass,
			Constructor testConstructor, Method testMethod) {

		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
