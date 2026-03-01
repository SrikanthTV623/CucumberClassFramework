package com.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;
    static ExcelUtils excelUtils = null;


    private ExcelUtils(Workbook workbook){
        ExcelUtils.workbook = workbook;
    }

    public static ExcelUtils selectFile(String domain) throws IOException {
        String filePath = "src/test/java/com/automation/data/"+domain+"Data.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        if(excelUtils == null){
            excelUtils = new ExcelUtils(workbook);
        }
        return excelUtils;
    }

    // Get Entire Row Data
    public static Map<String, String> getDataBySheetAndScenario(String sheetName , String scenarioName) {

        Map<String, String> dataMap = new HashMap<>();

        sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getLastRowNum();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= totalRows; i++) {

            Row currentRow = sheet.getRow(i);
            String scenarioCellValue = currentRow.getCell(0).getStringCellValue();

            if (scenarioCellValue.equalsIgnoreCase(scenarioName)) {

                for (int j = 0; j < currentRow.getLastCellNum(); j++) {

                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = new DataFormatter()
                            .formatCellValue(currentRow.getCell(j));

                    dataMap.put(key, value);
                }
                break;
            }
        }
        return dataMap;
    }

    public void closeWorkbook() throws IOException {
        workbook.close();
    }
}
