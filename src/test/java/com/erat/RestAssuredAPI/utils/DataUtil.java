package com.erat.RestAssuredAPI.utils;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

public class DataUtil extends BaseTest {
    private static final int TWO_ROWS = 2;
    private static final int ONE_ROW = 1;
    private static final int ZERO = 0;

    @DataProvider(name = "getExcelDataAsTable")
    public Object[] getExcelDataAsTable(Method method, Class<?> aClass) {
        ExcelReader excelReader = new ExcelReader(properties.getProperty(excelBaseDir) + aClass.getSimpleName() + "Data.xlsx");

        String sheetName = method.getName();
        int rows = excelReader.getRowCount(sheetName);
        int columns = excelReader.getColumnCount(sheetName);

        if (rows == -1 || columns == -1) {
            throw new RuntimeException(String.format("%s sheetName was NOT found!", sheetName));
        }

        List<Object> data = new ArrayList<>();
        Map<String, String> testDataMap;

        for (int rowNum = ZERO; rowNum < rows; rowNum++) {
            testDataMap = new HashMap<>();
            for (int colNum = ZERO; colNum < columns; colNum++) {
                testDataMap.put(excelReader.getCellData(sheetName, colNum, ZERO), excelReader.getCellData(sheetName, colNum, rowNum + ONE_ROW));
            }
            System.out.println(String.format("Map before when rowNumber is %s = ", rowNum) + testDataMap);
            System.out.println(String.format("Data array before assignment when rowNumber is %s = ", rowNum) + data.toString() + "\n");
            data.add(testDataMap);
            System.out.println(String.format("Map after when rowNumber is %s = ", rowNum) + testDataMap);
            System.out.println(String.format("Data array after assignment when rowNumber is %s = ", rowNum) + data.toString());

            System.out.println("---------------------------------------------------------------------------------------------------------------" + "\n");
        }
        return data.toArray();
    }

    @DataProvider(name = "getExcelDataAsTableWithOneSheet")
    public Object[] getExcelDataAsTableWithOneSheet(Method method, Class<?> aClass) {
        String sheetName = properties.getProperty("testDataSheetName");

        ExcelReader excelReader = new ExcelReader(properties.getProperty(excelBaseDir) + aClass.getSimpleName() + "DataExtended.xlsx");

        int totalRows = excelReader.getRowCount(sheetName);
        System.out.println("Total rows are : " + totalRows);

        String testName = method.getName();
        System.out.println("Test name is : " + testName);

        // Find the test case start row
        int testCaseNameRowNum;
        for (testCaseNameRowNum = ZERO; testCaseNameRowNum < totalRows; testCaseNameRowNum++) {
            if(excelReader.isNextRowExists(sheetName, testCaseNameRowNum)){
                String testCaseName = excelReader.getCellData(sheetName, ZERO, testCaseNameRowNum);
                if (testCaseName.equalsIgnoreCase(testName))
                    break;
            }
        }
        System.out.println("Test case starts from row number: " + testCaseNameRowNum);

        // Checking total rows in test case
        int dataStartRowNum = testCaseNameRowNum + TWO_ROWS;
        int testRows = ZERO;
        while (excelReader.isNextRowExists(sheetName, dataStartRowNum + testRows)) {
            testRows++;
        }
        System.out.println("Total rows of data are: " + testRows);

        // Checking total cols in test case
        int headersStartColNum = testCaseNameRowNum + ONE_ROW;
        int testCols = ZERO;
        while (excelReader.isCellHasBlankValue(sheetName, testCols, headersStartColNum)) {
            testCols++;
        }
        System.out.println("Total columns are: " + testCols);

        // Printing data
        List<Object> data = new ArrayList<>();
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
            Map<String, String> map = new HashMap<>();
            for (int cNum = ZERO; cNum < testCols; cNum++) {
                String testData = excelReader.getCellData(sheetName, cNum, rNum);
                String headersName = excelReader.getCellData(sheetName, cNum, headersStartColNum);

                map.put(headersName, testData);
            }
            data.add(map);
        }
        return data.toArray();
    }
}