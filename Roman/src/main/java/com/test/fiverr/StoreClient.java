package com.test.fiverr;

import com.test.yellowPage.YellowUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StoreClient {
    public void writeInExcel(Map<String, List<String>> dataMap) throws IOException {

        File src = new File("E:\\fiverr_client.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xsf = new XSSFWorkbook(fis);
        XSSFSheet sheet = xsf.getSheetAt(0);
        try {
            // Get the last row number to avoid overwriting
            int rowNumber = sheet.getLastRowNum() + 1;
            for (int i = 0; i < FiverrUtility.totalClient; i++) {
                Row row = sheet.getRow(rowNumber);
                if (row == null) {
                    row = sheet.createRow(rowNumber);
                }

                // Create a cell for the name (index 0 for the first cell)
                Cell slCell = row.createCell(0);
                slCell.setCellValue("#" + rowNumber);


                List<String> nameListFromMap = dataMap.get("nameList");

                if (nameListFromMap != null && i < nameListFromMap.size()) {
                    Cell nameCell = row.createCell(1);
                    nameCell.setCellValue(nameListFromMap.get(i));
                } else {
                    Cell nameCell = row.createCell(1);
                    nameCell.setCellValue(" ");
                }

                List<String> countryList = dataMap.get("countryList");

                if (countryList != null && i < countryList.size()) {
                    Cell countryCell = row.createCell(2);
                    countryCell.setCellValue(countryList.get(i));
                } else {
                    Cell countryCell = row.createCell(2);
                    countryCell.setCellValue(" ");
                }

                List<String> ratingList = dataMap.get("ratingList");

                if (ratingList != null && i < ratingList.size()) {
                    Cell ratingCell = row.createCell(3);
                    ratingCell.setCellValue(ratingList.get(i));
                } else {
                    Cell ratingCell = row.createCell(3);
                    ratingCell.setCellValue(" ");
                }

                List<String> deliveryDateList = dataMap.get("deliveryDateList");

                if (deliveryDateList != null && i < deliveryDateList.size()) {
                    Cell ratingCell = row.createCell(4);
                    ratingCell.setCellValue(deliveryDateList.get(i));
                } else {
                    Cell ratingCell = row.createCell(4);
                    ratingCell.setCellValue(" ");
                }

                rowNumber++;
            }
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound!!");
        }
        finally {

            FileOutputStream fos = new FileOutputStream(src);
            xsf.write(fos);
            xsf.close();
        }
    }
}
