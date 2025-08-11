package org.nextory.ui_automation.mobile.base.Utils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EventListener implements WebDriverListener {
    private final ExtentTest test;

    public EventListener(ExtentTest test) {
        this.test = test;
    }

    @Override
    public void beforeClick(WebElement element) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Log the PASSrmation
        test.log(Status.PASS, "Clicking element located at: " + elementLocator);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        test.log(Status.PASS, "Navigated to " + url);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        test.log(Status.PASS, "Browser Loading URL : " + url);
    }

    @Override
    public void afterClose(WebDriver driver) {
        test.log(Status.PASS, "Browser Closed ");
    }

    @Override
    public void afterQuit(WebDriver driver) {
        test.log(Status.PASS, "Quit Browser ");
    }

    @Override
    public void beforeClear(WebElement element) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Log the PASSrmation
        test.log(Status.PASS, "Clearing element locatled at: " + elementLocator);
    }

    @Override
    public void beforeGetText(WebElement element) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Log the PASSrmation
        test.log(Status.PASS, "Getting Text From : " + elementLocator);
    }

    @Override
    public void afterGetText(WebElement element, String result) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Log the PASSrmation
        test.log(Status.PASS, "Checking " + elementLocator + " is displayed ..");
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        // Log the action of navigating to a URL
        test.log(Status.PASS, "Navigating to URL: " + url);

        // Call the superclass method if needed
        WebDriverListener.super.beforeTo(navigation, url);
    }

    @Override
    public void beforeBack(WebDriver.Navigation navigation) {
        // Log the action of navigating back
        test.log(Status.PASS, "Navigating back in the browser.");

        // Call the superclass method if needed
        WebDriverListener.super.beforeBack(navigation);
    }

    @Override
    public void beforeForward(WebDriver.Navigation navigation) {
        // Log the action of navigating forward
        test.log(Status.PASS, "Navigating forward in the browser.");

        // Call the superclass method if needed
        WebDriverListener.super.beforeForward(navigation);
    }

    @Override
    public void beforeIsDisplayed(WebElement element) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Log the PASSrmation
        test.log(Status.PASS, "Checking " + elementLocator + " is displayed.. ");
    }


    @Override
    public void beforeRefresh(WebDriver.Navigation navigation) {
        // Log the action of refreshing the page
        test.log(Status.PASS, "Refreshing the page.");

        // Call the superclass method if needed
        WebDriverListener.super.beforeRefresh(navigation);
    }

    @Override
    public void beforeAnyAlertCall(Alert alert, Method method, Object[] args) {
        // Log the action of interacting with an alert
        test.log(Status.PASS, "Interacting with alert. Method: " + method.getName());

        // Call the superclass method if needed
        WebDriverListener.super.beforeAnyAlertCall(alert, method, args);
    }

    @Override
    public void beforeImplicitlyWait(WebDriver.Timeouts timeouts, Duration duration) {
        // Log the action of setting an implicit wait
        test.log(Status.PASS, "Setting implicit wait to: " + duration.toMillis() + " milliseconds.");

        // Call the superclass method if needed
        WebDriverListener.super.beforeImplicitlyWait(timeouts, duration);
    }

    @Override
    public void beforeParentFrame(WebDriver.TargetLocator targetLocator) {
        test.log(Status.PASS, "Switching to Parent Frame..");
    }

    @Override
    public void beforeDefaultContent(WebDriver.TargetLocator targetLocator) {
        test.log(Status.PASS, "Switching to Default Content..");
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Format the keys sent
        String keysSent = String.join(", ", keysToSend);

        // Log the PASSrmation
        test.log(Status.PASS, "Sent keys [" + keysSent + "] to element located at: " + elementLocator);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend)  {
        // Get the element's locator
        String elementLocator = element.toString().split("->")[1].trim(); // Extracts the locator part from toString()

        // Format the keys sent
        String keysSent = String.join(", ", keysToSend);

        // Log the PASSrmation
        test.log(Status.PASS, "Sending [" + keysSent + "] to element located at: " + elementLocator);

    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        // Get the name of the method where the error occurred
        String methodName = method.getName();

        // Get a formatted string of the method arguments
        StringBuilder argsString = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                argsString.append(arg.toString()).append(", ");
            } else {
                argsString.append("null, ");
            }
        }
        // Remove trailing comma and space if any
        if (argsString.length() > 0) {
            argsString.setLength(argsString.length() - 2);
        }

        // Get the exception message and stack trace
        String errorMessage = e.getMessage();
        String stackTrace = Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        // Format the error log
        String errorLog = String.format(
                "Error occurred in method: %s\nArguments: [%s]\nMessage: %s\nStack Trace:\n%s",
                methodName,
                argsString.toString(),
                errorMessage,
                stackTrace
        );

        // Log the error (you can use any logging framework you prefer, e.g., Log4j, SLF4J, etc.)
        System.err.println(errorLog);

        // Optionally, you can log the error to ExtentReports
        ExtentTest extentTest = ExtentManager.getTest();
        if (extentTest != null) {
            extentTest.log(Status.ERROR, errorLog);
        }

        // Call the super implementation if necessary
        WebDriverListener.super.onError(target, method, args, e);
    }
}
