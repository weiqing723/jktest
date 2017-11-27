package Common;

import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

public class TestngRetryListener extends TestListenerAdapter {

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public void onTestSuccess(ITestResult tr) {
		log.info("Test Success");
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		Logger.getLogger(this.getClass()).info("In failed method");

		// 返回的tr是 LoginTest 类的

		TestBase b = (TestBase) tr.getInstance();

		log.error("Test Failure");
		System.out.println(b.getDriver().getTitle());
		ScreenShotOnFailure.takeScreentShot(b, tr.getMethod().getQualifiedName());

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		log.error("Test Skipped");
		super.onTestSkipped(tr);
	}

	@Override
	public void onStart(ITestContext testContext) {
		log.info("Test Start");
		super.onStart(testContext);
	}

	// @Override
	// public void onFinish(ITestContext testContext) {
	// log.info("Test Finish");
	// super.onFinish(testContext);
	// }
	@Override
	public void onFinish(ITestContext iTestContext) {
		super.onFinish(iTestContext);

		Iterator<ITestResult> listOfFailedTests = iTestContext.getFailedTests().getAllResults().iterator();
		while (listOfFailedTests.hasNext()) {
			ITestResult failedTest = listOfFailedTests.next();
			ITestNGMethod method = failedTest.getMethod();
			if (iTestContext.getFailedTests().getResults(method).size() > 1) {
				listOfFailedTests.remove();
			} else {
				if (iTestContext.getPassedTests().getResults(method).size() > 0) {
					listOfFailedTests.remove();
				}
			}

		}
		Iterator<ITestResult> listOfSkippedTests = iTestContext.getSkippedTests().getAllResults().iterator();
		while (listOfSkippedTests.hasNext()) {
			ITestResult skippedTest = listOfSkippedTests.next();
			ITestNGMethod method = skippedTest.getMethod();
			if (iTestContext.getSkippedTests().getResults(method).size() > 1) {
				listOfSkippedTests.remove();
			} else {
				if (iTestContext.getPassedTests().getResults(method).size() > 0) {
					listOfSkippedTests.remove();
				} else if (iTestContext.getFailedTests().getResults(method).size() > 0) {
					listOfSkippedTests.remove();
				}
			}

		}
		System.out.println("Finish all the tests");

	}

}