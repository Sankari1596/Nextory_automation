package org.nextory.ui_automation.mobile.base.Utils;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.IResultListener;

public class RetryListener extends TestListenerAdapter implements IResultListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        Allure.step("Retrying failed test case: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
       Allure.step("All tests finished. Generating reports...");
    }
}
