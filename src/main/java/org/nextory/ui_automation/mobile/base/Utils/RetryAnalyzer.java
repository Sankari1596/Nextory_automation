package org.nextory.ui_automation.mobile.base.Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount;

    public RetryAnalyzer() {
        maxRetryCount = getRetryCountFromProperties();
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

    private int getRetryCountFromProperties() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
            properties.load(fis);
            return Integer.parseInt(properties.getProperty("retry.count", "2")); // Default retry count is 2
        } catch (IOException e) {
            e.printStackTrace();
            return 2; // Default value if file read fails
        }
    }
}
