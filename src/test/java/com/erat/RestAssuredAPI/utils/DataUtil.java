package com.erat.RestAssuredAPI.utils;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import com.erat.RestAssuredAPI.setUp.StripeBaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class DataUtil extends StripeBaseTest {
    private static final int TWO_ROWS = 2;
    private static final int ONE_ROW = 1;
    private static final int ZERO = 0;

    /**
     * This DataProvider is using to get test parameters from Excel file by different sheets
     * @param method - is using to get test method name (method name should be the same as sheet name in the Excel file)
     * @param aClass - is using to get part of the Excel file name with testing data
     * @return - this DataProvider returns a List of Maps with testing data
     */
    @DataProvider(name = "getExcelDataAsTable")
    public static Object[] getExcelDataAsTable(Method method, Class<?> aClass) {
        ExcelReader excelReader = new ExcelReader(excelBaseDir + aClass.getSimpleName() + "Data.xlsx");

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
            data.add(testDataMap);
        }
        return data.toArray();
    }

    /**
     * This DataProvider is using to get test parameters from the Excel file by only one sheet.
     * Test data should be puttied in the test tables, which should be separated by a blank line.
     * @param method - is using to get test method name (method name should correspond to the first row of the test data table in the Excel file)
     * @param aClass - is using to get part of the Excel file name with testing data
     * @return - this DataProvider returns a List of Maps with testing data(one list element - one test launch)
     */
    @DataProvider(name = "getExcelDataAsTableWithOneSheet", parallel = true)
    public Object[] getExcelDataAsTableWithOneSheet(Method method, Class<?> aClass) {
        String sheetName = properties.getProperty("testDataSheetName");

        ExcelReader excelReader = new ExcelReader(excelBaseDir + aClass.getSimpleName() + "DataExtended.xlsx");

        int totalRows = excelReader.getRowCount(sheetName);
        log.info("Total rows are : {}", totalRows);

        String testName = method.getName();
        log.info("Test name is : {}", testName);

        // Find the test case start row
        int testCaseNameRowNum;
        for (testCaseNameRowNum = ZERO; testCaseNameRowNum < totalRows; testCaseNameRowNum++) {
            if(excelReader.isNextRowExists(sheetName, testCaseNameRowNum)){
                String testCaseName = excelReader.getCellData(sheetName, ZERO, testCaseNameRowNum);
                if (testCaseName.equalsIgnoreCase(testName))
                    break;
            }
        }
        log.info("Test case starts from row number: {}", testCaseNameRowNum);

        // Checking total rows in test case
        int dataStartRowNum = testCaseNameRowNum + TWO_ROWS;
        int testRows = ZERO;
        while (excelReader.isNextRowExists(sheetName, dataStartRowNum + testRows)) {
            testRows++;
        }
        log.info("Total rows of data are: {}", testRows);

        // Checking total cols in test case
        int headersStartColNum = testCaseNameRowNum + ONE_ROW;
        int testCols = ZERO;
        while (excelReader.isCellHasBlankValue(sheetName, testCols, headersStartColNum)) {
            testCols++;
        }
        log.info("Total columns are: {}", testCols);

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