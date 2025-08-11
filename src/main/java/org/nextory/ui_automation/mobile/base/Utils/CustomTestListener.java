package org.nextory.ui_automation.mobile.base.Utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {
    private ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        if (ExtentManager.getTest() == null) {  // Only create if it doesn't already exist
            extentTest = ExtentManager.getReporter().createTest(result.getMethod().getMethodName());
            ExtentManager.setTest(extentTest);
        } else {
            extentTest = ExtentManager.getTest();
        }
        extentTest.log(Status.INFO, "<b><h5>Test started: " + result.getMethod().getMethodName() + "</b></h5>");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest = ExtentManager.getTest();
        extentTest.log(Status.PASS, "<b>Test passed: " + result.getMethod().getMethodName() + "<b>");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest = ExtentManager.getTest();
        extentTest.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
        // Capture screenshot or perform other actions as needed
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest = ExtentManager.getTest();
        extentTest.log(Status.SKIP, "<b>Test skipped: " + result.getMethod().getMethodName() + "</b>");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flushReports();
    }
}
