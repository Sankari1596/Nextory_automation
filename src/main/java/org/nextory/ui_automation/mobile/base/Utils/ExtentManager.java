package org.nextory.ui_automation.mobile.base.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            String reportname = FileUtil.getValue("reportfilename");
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportname);

            String Report_title = FileUtil.getValue("Report_title");
            String Report_name = FileUtil.getValue("Report_name");
            String env = FileUtil.getValue("env");
            String locale = FileUtil.getValue("locale");

            htmlReporter.config().setDocumentTitle(Report_title);
            htmlReporter.config().setReportName(Report_name);
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Environment", env);
            extent.setSystemInfo("Locale", locale);
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
