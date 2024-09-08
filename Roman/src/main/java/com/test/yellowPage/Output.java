package com.test.yellowPage;

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

public class Output {
    public void writeInExcel(Map<String, List<String>> dataMap) throws IOException {

        File src = new File("E:\\data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xsf = new XSSFWorkbook(fis);
        XSSFSheet sheet = xsf.getSheetAt(0);

        try {
            // Get the last row number to avoid overwriting
            int rowNumber = sheet.getLastRowNum() + 1;

            for (int i = 0; i < YellowUtility.totalCompany; i++) {
                Row row = sheet.getRow(rowNumber);
                if (row == null) {
                    row = sheet.createRow(rowNumber);
                }

                // Create a cell for the serial number (index 0 for the first cell)
                Cell slCell = row.createCell(0);
                slCell.setCellValue("#" + rowNumber);

                // Fetch and write name
                List<String> nameListFromMap = dataMap.get("nameList");
                if (nameListFromMap != null && i < nameListFromMap.size()) {
                    Cell nameCell = row.createCell(1);
                    nameCell.setCellValue(nameListFromMap.get(i));
                } else {
                    Cell nameCell = row.createCell(1);
                    nameCell.setCellValue(" ");
                    System.out.println("Invalid data or index out of bounds for nameList.");
                }

                // Fetch and write telephone
                List<String> telephoneListMap = dataMap.get("telephoneList");
                if (telephoneListMap != null && i < telephoneListMap.size()) {
                    Cell telephoneCell = row.createCell(2);
                    telephoneCell.setCellValue(telephoneListMap.get(i));
                } else {
                    Cell telephoneCell = row.createCell(2);
                    telephoneCell.setCellValue(" ");
                    System.out.println("Invalid data or index out of bounds for telephoneList.");
                }

                // Fetch and write email
                List<String> emailListMap = dataMap.get("emailList");
                if (emailListMap != null && i < emailListMap.size()) {
                    Cell emailCell = row.createCell(3);
                    emailCell.setCellValue(emailListMap.get(i));
                } else {
                    Cell emailCell = row.createCell(3);
                    emailCell.setCellValue(" ");
                    System.out.println("Invalid data or index out of bounds for emailList.");
                }

                // Fetch and write address
                List<String> addressListMap = dataMap.get("addressList");
                if (addressListMap != null && i < addressListMap.size()) {
                    Cell addressCell = row.createCell(4);
                    addressCell.setCellValue(addressListMap.get(i));
                } else {
                    Cell addressCell = row.createCell(4);
                    addressCell.setCellValue(" ");
                    System.out.println("Invalid data or index out of bounds for addressList.");
                }

                rowNumber++;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!!");
        } finally {
            FileOutputStream fos = new FileOutputStream(src);
            xsf.write(fos);
            xsf.close();
            fis.close(); // Close FileInputStream
        }
    }

}
