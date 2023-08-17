package reporting.Listeners;

import java.util.Arrays;
import java.util.Date;

import reporting.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener {

  /**
   * To generate a unique thread whenever the report creation
   */
  public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();
  private static final String FILE_NAME = "Extent_" + new Date().toString().replace(":", "_").replace(" ", "_") + ".html";
  private static final ExtentReports EXTENT = ExtentManager.createInstance(System.getProperty("user.dir") + "\\reports\\" + FILE_NAME);

  static {
    System.setProperty("logback.configurationFile", "src/test/resources/logback/logback.xml");
  }

  public void onTestStart(ITestResult result) {
    ExtentTest test = EXTENT.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
    testReport.set(test);
  }

  public void onTestSuccess(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
    Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
    testReport.get().pass(m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestFailure(ITestResult result) {
    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
    testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
            + "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

    String failureLogg = "TEST CASE FAILED";
    Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
    testReport.get().log(Status.FAIL, m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestSkipped(ITestResult result) {
    String methodName = result.getMethod().getMethodName();
    String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
    Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
    testReport.get().skip(m);
    testReport.get().info("Test parameters: " + Arrays.toString(result.getParameters()));
  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  public void onStart(ITestContext context) {
  }

  public void onFinish(ITestContext context) {
    EXTENT.flush();
  }
}