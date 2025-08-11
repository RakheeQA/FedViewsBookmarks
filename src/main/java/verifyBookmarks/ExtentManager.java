package verifyBookmarks;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        System.out.println("************In Extent Manager Class");
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReportFocusAreas.html");
            reporter.config()
                    .setReportName("Selenium Test Report : FedViews - Agency , Current Survey , Focus Areas Bookmarks");
            reporter.config().setDocumentTitle("Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            System.out.println("**************Created Report");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        System.out.println("*************In CReate Test");
        test = getExtentReports().createTest(testName);
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}