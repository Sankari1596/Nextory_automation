package org.nextory.ui_automation.mobile.base.Utils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyIOSUtil {
    public static Properties loadUTF8Props(String path) throws IOException {
        Properties props = new Properties();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            props.load(reader);
        }
        return props;
    }
}


