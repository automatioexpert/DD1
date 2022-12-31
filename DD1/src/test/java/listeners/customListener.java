package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;

public class customListener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test Passed" +result.getClass().getSimpleName());
		test.log(LogStatus.PASS, "Test case: " + result.getName() + " PASSED");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "Test case: " + result.getName() + " FAILED" + result.getThrowable());
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

}
