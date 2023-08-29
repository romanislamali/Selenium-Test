package com.test;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output {

    public void writeInExel(String nameReceived) throws IOException {
        File src = new File("E:\\data.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook xsf = new XSSFWorkbook(fis);
        XSSFSheet sheet = xsf.getSheetAt(0);
        //write data
        sheet.getRow(1).getCell(1).setCellValue(nameReceived);
        FileOutputStream fos = new FileOutputStream(src);
        System.out.println("before write");
        xsf.write(fos);
        System.out.println("after write");
        xsf.close();
    }
}
