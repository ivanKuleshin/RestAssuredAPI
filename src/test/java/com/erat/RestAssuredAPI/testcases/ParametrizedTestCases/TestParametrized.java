package com.erat.RestAssuredAPI.testcases.ParametrizedTestCases;

import com.erat.RestAssuredAPI.testcases.TestCases.BaseTest;
import com.erat.RestAssuredAPI.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class TestParametrized extends BaseTest {
    @Test(dataProvider = "getData", groups = "parametrized")
    public void doLogin(String userName, String password) {
        System.out.println(userName + "----" + password);
    }

    @Test(dataProvider = "getExcelData", groups = "parametrized")
    public void checkLoginInfo(String userName, String password) {
        System.out.println("From Excel: " + userName + "----" + password);
    }

    @Test(dataProvider = "getExcelDataAsTable", groups = "parametrized")
    public void checkLoginInfoViaMap(Map<String, String> testDataMap) {
        System.out.println("From Excel via HashTable: " + testDataMap.get("userName") + "----" + testDataMap.get("password"));
    }

    @DataProvider
    private Object[][] getData() {
        Object[][] data = new Object[2][2];

        data[0][0] = "Fegal1337";
        data[0][1] = "1337";

        data[1][0] = "Fegal1488";
        data[1][1] = "1488";

        return data;
    }

    @DataProvider
    public Object[][] getExcelData() {
        ExcelReader excelReader = new ExcelReader("src/test/resources/testData/DataSetExample.xlsx");

        String sheetName = "LoginInfoTest";
        int rows = excelReader.getColumnCount(sheetName);
        int columns = excelReader.getColumnCount(sheetName);

        if (rows == -1 || columns == -1) {
            throw new RuntimeException(String.format("%s sheetName was NOT found!", sheetName));
        }

        Object[][] data = new Object[rows][columns];

        for (int rowNum = 1; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < columns; colNum++) {
                data[rowNum - 1][colNum] = excelReader.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;
    }

    @DataProvider(name = "getExcelDataAsTable")
    public Object[] getExcelDataAsTable() {
        ExcelReader excelReader = new ExcelReader("src/test/resources/testData/DataSetExample.xlsx");

        String sheetName = "LoginInfoTest";
        int rows = excelReader.getRowCount(sheetName);
        int columns = excelReader.getColumnCount(sheetName);

        if (rows == -1 || columns == -1) {
            throw new RuntimeException(String.format("%s sheetName was NOT found!", sheetName));
        }

        List<Object> data = new ArrayList<>();
        Map<String, String> testDataMap;

        for (int rowNum = 0; rowNum < rows; rowNum++) {
            testDataMap = new HashMap<>();
            for (int colNum = 0; colNum < columns; colNum++) {
                testDataMap.put(excelReader.getCellData(sheetName, colNum, 0), excelReader.getCellData(sheetName, colNum, rowNum + 1));
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
}