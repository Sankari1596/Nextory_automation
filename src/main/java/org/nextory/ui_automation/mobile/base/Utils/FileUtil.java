package org.nextory.ui_automation.mobile.base.Utils;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileUtil {
    static Properties prop;

    public static String getValue(String key) {
        try {
            prop = new Properties();

            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application.properties");
            prop.load(ip);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);

    }

    public static HashMap testData(String env, String locale) {
        String fileName = "testdata/" + env + ".xlsx"; // replace with your Excel file path
        String sheetName = locale; // replace with your sheet name
        System.out.println("****FileName found as per test exeuction*****" + fileName);
        HashMap<String, String> map = new HashMap();
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            fis = new FileInputStream(new File(fileName));
            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            // skip header row
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String key = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                            .getStringCellValue().trim();
                    String value = "";
                    if (row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                            .getCellType() == CellType.NUMERIC) {
                        value = String.valueOf(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                                .getNumericCellValue());
                    } else {
                        value = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
                                .getStringCellValue().trim();
                    }
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // print the HashMap
        return map;
    }


    public static void archiveOldReport(String filename, String archivalFolder) {


        File reportFile = new File(filename);
        if (reportFile.exists()) {
            // Try to create the archive folder if it doesn't exist
            try {
                File archiveDir = new File(archivalFolder);
                if (!archiveDir.exists() && !archiveDir.mkdirs()) {
                    throw new IOException("Failed to create archive directory");
                }

                // Create a timestamp for the archive name
                String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                String archivedReportName = "extent_" + timestamp + ".html";

                // Move the old report to the archive folder
                Files.move(Paths.get(filename), Paths.get(archivalFolder + archivedReportName));
                System.out.println("Archived report as: " + archivedReportName);

            } catch (IOException e) {
                System.err.println("Archival process failed: " + e.getMessage());
                // Continue execution even if the archival or folder creation fails
            }
        }
    }
}





