package org.nextory.ui_automation.mobile.base;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.nextory.ui_automation.mobile.base.Utils.ExtentManager;
import org.nextory.ui_automation.mobile.base.Utils.FileUtil;
import org.nextory.ui_automation.mobile.base.Utils.ScreenshotUtil;
import org.nextory.ui_automation.mobile.base.Utils.UpdateEnvironmentPropertiesAndroid;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class BaseClassMobile {
    String profileDriver = loadTestData("profileDriverAndroid");
    String deviveName = loadTestData("android_device_name");
    String fileLocation = loadTestData("filelocationAndroid");
    String appWaitActivity = loadTestData("appWaitActivity");
    String launch_URL = loadTestData("applaunch_url");
    String platform = loadTestData("MobilePlatform");
    int adbExecTimeout = 80000;

    public static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static HashMap<String, String> testdata = new HashMap<>();
    private static ExtentTest extentTest;


    public static final int FIVE_SECONDS = 5000;
    public static final int ONE_SECOND = 1000;
    public static final int TWO_SECOND = 2000;
    public static final int THREE_SECOND = 3000;


    @BeforeMethod(alwaysRun = true)
    public void initSetup() throws Exception {
        try {
            initSession();
        } catch (Exception e) {
            initSession();
        }
    }





    public void initSession() throws Exception {
        if ("BrowserStack".equalsIgnoreCase(platform)) {
            launchOnBrowserStack();
        } else {
            launchAndroidApp();
        }
    }

    public void launchAndroidApp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(profileDriver);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName(deviveName);
        options.setUiautomator2ServerLaunchTimeout(Duration.ofDays(1));
        options.setAppWaitActivity(appWaitActivity);
        options.setAdbExecTimeout(Duration.ofSeconds(adbExecTimeout));
        options.setCapability("autoGrantPermissions", true);
        options.setNewCommandTimeout(Duration.ofSeconds(180));

        // Check if APK file exists
        if (fileLocation != null && !fileLocation.trim().isEmpty() && new File(fileLocation).exists()) {
            options.setApp(fileLocation);
            options.setFullReset(false);
            options.setNoReset(false);
            System.out.println("Launching using APK: " + fileLocation);
        } else {
            // Use installed Play Store app
            String appPackage = loadTestData("appPackage");
            String appActivity = loadTestData("appActivity");
            if (appPackage == null || appActivity == null) {
                throw new RuntimeException("For Play Store run, set appPackage and appActivity in application.properties");
            }
            options.setAppPackage(appPackage);
            options.setAppActivity(appActivity);
            options.setFullReset(false);
            options.setNoReset(true);
            options.setDisableWindowAnimation(true);
            options.setCapability("enforceAppInstall", false);
            options.setCapability("ignoreHiddenApiPolicyError", true);

            System.out.println("Launching installed app: " + appPackage + " / " + appActivity);
        }

        driver.set(new AndroidDriver(new URL(launch_URL), options));
        wait(FIVE_SECONDS);
    }




    public void launchOnBrowserStack() throws Exception {
        // Load BrowserStack credentials from properties file
        String username = loadPropertiesFile("application.properties").getProperty("BrowserStack_username");
        String accessKey = loadPropertiesFile("application.properties").getProperty("BrowserStack_AccessKey");


        // Get device details and app URL from properties file
        String deviceName = loadTestData("BrowserStack_deviceName");
        String appURL = loadTestData("BrowserStack_app_url");

        String device = loadPropertiesFile("application.properties").getProperty("BrowserStack_deviceName");
        String BSappURL = loadPropertiesFile("application.properties").getProperty("BrowserStack_app_url");

        // Create UiAutomator2Options for Android
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setDeviceName(deviceName);
        options.setApp(BSappURL);


        // Set BrowserStack capabilities
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", username);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("buildName", "NEXTORY Automation");
        bstackOptions.put("projectName", "Your Project Name");
        bstackOptions.put("sessionName", "App Test");

        // Add any additional capabilities like OS version, device orientation etc.
        options.setCapability("bstack:options", bstackOptions);

        options.setCapability("autoAcceptAlerts", true);

        // Initialize AndroidDriver with BrowserStack URL
        driver.set(new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), options));

        System.out.println("*******BrowserStack Started, App Launched on Device: " + deviceName + " *****");
        Thread.sleep(5000);
    }


    public void wait(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public static Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();
        try (InputStream resourceAsStream = BaseClassMobile.class.getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to load properties file : " + filePath);
        }
        return prop;
    }

    public static String loadTestData(String value) {
        String propertyValue = loadPropertiesFile("application.properties").getProperty(value);
        return propertyValue;
    }

    public void click(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            element.click();
        } catch (Exception e) {
            throw new NoSuchElementException("Cannot find expected element: " + xpath);
        }
    }


    public void enterText(final String xpath, final String text) {
        try {
            driver.get().findElement(By.xpath(xpath)).sendKeys(text);
        } catch (NoSuchElementException e) {
            System.out.println("Not able to input the data");
        }
    }


    public static AndroidDriver getDriver() {
        return driver.get();
    }


    public String getText(String xpath) {
            WebElement element = getDriver().findElement(AppiumBy.xpath(xpath));
            String elementText = element.getText();
            return elementText;
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        takeScreenshotAfterTest();
        try {
            if (driver != null && driver.get() != null) {
                System.out.println("Quitting driver...");
                driver.get().quit();
            }
        } catch (Exception e) {
            System.err.println("Error quitting driver: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.remove();
            }
        }
    }

    public static void cleanAllureResults() throws IOException {
        Path allureResultsPath = Paths.get("allure-results");
        if (Files.exists(allureResultsPath)) {
            Files.walk(allureResultsPath)
                    .filter(path -> !path.getFileName().toString().equals("environment.properties"))  // Exclude the environment.properties file
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void clearAllureResults() throws IOException {
        // Clean the previous Allure results
        cleanAllureResults();

        // Update the details from application.properties to environment.properties for Allure report
        System.out.println("Update the details from application.properties to environment.properties for Allure report");
        UpdateEnvironmentPropertiesAndroid.updateProperties();
    }


    public void takeScreenshotAfterTest() {
        if (driver != null && driver.get() != null) {
            WebDriver currentDriver = driver.get();  // Retrieve the actual driver from ThreadLocal
            String testName = Thread.currentThread().getStackTrace()[1].getMethodName(); // Get the test method name
            ScreenshotUtil.captureScreenshotAndAttachToAllure(currentDriver, testName); // Correct method call with actual WebDriver
        }
    }

    public void setUp() {
        System.out.println("Running Before Suite");
        String report_file_to_archive= FileUtil.getValue("reportfilename");
        String folder_to_archive=FileUtil.getValue("archival_folder");
        String env= FileUtil.getValue("env");
        String locale= returnLocales();
        testdata=FileUtil.testData(env, locale);
        System.out.println(testdata);
        extentTest = ExtentManager.getReporter().createTest(getClass().getSimpleName());
        ExtentManager.setTest(extentTest);
    }

    public String returnLocales(){
        if (FileUtil.getValue("locale").equals("en_eur"))
        {
            return FileUtil.getValue("country");
        }
        else
        {
            return FileUtil.getValue("locale");
        }
    }



    public void scrollToBottomPageOnce() {
        wait(FIVE_SECONDS);
        // Get screen size
        Dimension dimension = getDriver().manage().window().getSize();
        int screenHeightStart = (int) (dimension.getHeight() * 0.85); // Start scroll slightly higher
        int screenHeightEnd = (int) (dimension.getHeight() * 0.2); // End scroll slightly lower
        int screenWidth = dimension.getWidth() / 2;

        TouchAction action = new TouchAction(getDriver());
        action.press(PointOption.point(screenWidth, screenHeightStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(screenWidth, screenHeightEnd))
                .release()
                .perform();

        try {
            Thread.sleep(3000);  // Adjust the wait as necessary
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public boolean isDisplayed(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return element.isDisplayed();
        } catch (Exception e) {
            throw new NoSuchElementException("Cannot find expected element: " + xpath);
        }
    }


    public boolean isSelected(String xpath) {
        try {
            return driver.get().findElement(By.xpath(xpath)).isSelected();
        }
        catch (Exception e) {
            throw new NoSuchElementException("Cannot find expected element to check selection: " + xpath);
        }
    }

    public static String emailAddressGenerator(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public void scrollPageOnce() {
        driver.get().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"
        ));
    }


    public boolean isVisible(By by, int sec) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(sec))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) { return false; }
    }


    public void tapByCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getDriver().perform(Collections.singletonList(tap));
    }











}


