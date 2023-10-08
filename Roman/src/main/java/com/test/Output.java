package com.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Output {

    public void writeInExcel(List<String> nameList, List<String> telephoneList,List<String> linkList) throws IOException {

        File src = new File("E:\\data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xsf = new XSSFWorkbook(fis);
        XSSFSheet sheet = xsf.getSheetAt(0);

        int rowNumber = 1; // Start from the second row (index 1)
        for (int i = 0; i < nameList.size(); i++) {
            Row row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }

            // Create a cell for the name (index 0 for the first cell)
            Cell slCell = row.createCell(0);
            slCell.setCellValue("#"+rowNumber);

            Cell nameCell = row.createCell(1);
            nameCell.setCellValue(nameList.get(i));

            // Create a cell for the telephone (index 1 for the second cell)
            Cell telephoneCell = row.createCell(2);
            telephoneCell.setCellValue(telephoneList.get(i));

            Cell linkCell = row.createCell(3);
            linkCell.setCellValue(linkList.get(i));

            rowNumber++;
        }

        FileOutputStream fos = new FileOutputStream(src);
        xsf.write(fos);
        xsf.close();
    }
}
