package org.nextory.ui_automation.mobile.base.Utils;

import java.io.*;
import java.util.Properties;

public class UpdateEnvironmentPropertiesAndroid {

    public static void updateProperties() throws IOException {
        // Use relative paths
        String applicationPropertiesPath = "src/main/resources/application.properties";
        String environmentPropertiesPath = "allure-results/environment.properties";

        // Load application.properties
        Properties appProps = loadPropertiesFile(applicationPropertiesPath);

        // Retrieve values from application.properties
        String browser = appProps.getProperty("browser", "Chrome"); // Default: Chrome
        String env = appProps.getProperty("env", "test"); // Default: test
        String locale = appProps.getProperty("locale", "UnknownLocale"); // Default: UnknownLocale
        String countryName = appProps.getProperty(locale + "_android", "UnknownCountry"); // Fetch country name

        // Load environment.properties
        Properties envProps = loadPropertiesFile(environmentPropertiesPath);

        // Update environment.properties with selected values
        envProps.setProperty("browser", browser);
        envProps.setProperty("env", env);
        envProps.setProperty("locale", locale);
        envProps.setProperty("country_name", countryName);

        // Save the updated properties back to environment.properties without timestamp
        try (FileOutputStream envOutput = new FileOutputStream(environmentPropertiesPath)) {
            envProps.store(envOutput, null);  // Removes timestamp and unnecessary comment
        }
    }

    private static Properties loadPropertiesFile(String filePath) throws IOException {
        Properties props = new Properties();
        File file = new File(filePath);

        if (file.exists()) {
            try (FileInputStream input = new FileInputStream(file)) {
                props.load(input);
            }
        } else {
            System.err.println("❌ ERROR: Properties file not found -> " + filePath);
        }
        return props;
    }
}
