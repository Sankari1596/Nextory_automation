package org.nextory.ui_automation.mobile.base.Utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    // Method to capture screenshot and attach it directly in Allure
    public static void captureScreenshotAndAttachToAllure(WebDriver driver, String testName) {
        // Capture screenshot as file
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path to save the screenshot temporarily
        String screenshotPath = "target/screenshots/" + testName + ".png";
        File destinationFile = new File(screenshotPath);

        try {
            // Save the screenshot to the destination path
            FileUtils.copyFile(screenshotFile, destinationFile);

            // Attach the screenshot as an image to Allure
            Allure.addAttachment(testName + " Screenshot", "image/png", Files.newInputStream(Paths.get(screenshotPath)), "png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
