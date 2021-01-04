package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("ERAT Reports");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);

		ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Automation Tester", "Ivan Kuleshin");
        extent.setSystemInfo("Organization", "ERAT Sys");
        extent.setSystemInfo("Build no", "ES-1234");

        return extent;
    }
}